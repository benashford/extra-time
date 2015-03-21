(defproject extra-time "0.1.2"
  :description "A library designed to capture execution time in a more granular way than clojure.core's time, but less so than a proper profiling tool."
  :url "https://github.com/benashford/extra-time"
  :license {:name         "Apache Licence 2.0"
            :url          "http://www.apache.org/licenses/LICENSE-2.0"
            :distribution :repo}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :profiles {:dev {:plugins [[lein-midje "2.0.0-SNAPSHOT"]]}
             :test {:dependencies [[midje "1.4.0"]]}}
  :main extra-time.core)
