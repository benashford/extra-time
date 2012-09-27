(ns extra-time.test.core
  (:use extra-time.core)
  (:use midje.sweet))

(fact (binding [*timings* (atom {})]
        (cap-time :a (+ 1 1))
        (timing
         (cap-time :b (+ 2 2))
         (keys @*timings*))) => [:b])

(fact (with-time (+ 1 1)) => [0.0015 2]
      (provided (current-time) =streams=> [1000 2500]))

(fact (cap-time :key (+ 1 1)) => 2)

(fact (timing
        (cap-time :key (+ 1 1))
        (@*timings* :key))
      => [0.0015]
      (provided (current-time) =streams=> [1000 2500]))

(fact (inspect 1.0) => 1.0)
(fact (inspect (list 1.0)) => [1.0])
(fact (inspect (list 1.0 2.0)) => {:count 2 :average 1.5 :sum 3.0})

(fact (with-times (+ 1 1)) => [{:total [0.0015]} 2]
      (provided (current-time) =streams=> [1000 2500]))

(fact (timing
       (cap-time :a (+ 1 1))
       (->>
        (with-times (+ 1 1))
        first
        keys)) => [:total])

(fact (->>
       (with-times
         (cap-time :a (+ 1 1))
         (with-times
           (cap-time :b (+ 1 1))))
       first
       keys) => [:total :a])