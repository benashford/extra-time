(ns extra-time.core)

(def ^:dynamic *timings* (atom {}))

(def current-time #(System/nanoTime))

(defmacro timing [& exprs]
  `(binding [*timings* (atom {})]
     (do ~@exprs)))

(defmacro with-time [expr]
  `(let [start-time# (current-time)
         result# ~expr]
     [
      (->
       (current-time)
       (- start-time#)
       (/ 1000000.0))
      result#]))

(defmacro cap-time [tkey expr]
  `(let [[elapsed-time# result#] (with-time ~expr)]
     (swap! *timings* update-in [~tkey] conj elapsed-time#)
     result#))

(defmulti inspect (fn [v] (and (seq? v) (> (count v) 1))))
(defmethod inspect false [v] v)
(defmethod inspect true [v]
  (let [c (count v)
        t (reduce + v)]
    {:count c
     :average (/ t c)
     :sum t}))

(defn print-times [timings]
  (doseq [[k v] (sort-by first timings)]
    (println k " => " (inspect v))))

(defmacro with-times [& exprs]
  `(timing
   (let [result# (cap-time :total (do ~@exprs))]
     [@*timings* result#])))

(defmacro report-times [& exprs]
  `(let [[timings# result#] (with-times ~@exprs)]
     (print-times timings#)
     result#))
