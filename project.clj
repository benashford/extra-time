(defproject extra-time "0.1.1"
  :description "A library designed to capture execution time in a more granular way than clojure.core's time, but less so than a proper profiling tool."
  :url "https://github.com/benashford/extra-time"
  :dependencies [[org.clojure/clojure "1.4.0"]]
  :profiles {:dev {:plugins [[lein-midje "2.0.0-SNAPSHOT"]]}
             :test {:dependencies [[midje "1.4.0"]]}}
  :main extra-time.core)
