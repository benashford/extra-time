# extra-time

A Clojure library designed to capture execution time in a more granular way than ```clojure.core```'s ```time```, but less so than a proper profiling tool.

## Usage

Obtainable from Clojars, add: ```[extra-time "0.1.0"]``` to your dependencies.

To capture timing information for a block, wrap with ```with-times```.  This returns a tuple containing first the timing information, as a map, secondly the result of the block:

```clojure
(let [[timings result] (with-times (do-stuff 1 2))]
  (println "Total time: " (:total timings))
  (do-something result))
```

To capture timing information, and print out a summary, wrap with ```report-times```:

```clojure
(report-times (do-stuff 1 2))
```

To capture more granular timings, in addition to the total time, use ```cap-time```:

```clojure
(report-times
  (doseq [x (range 1000)]
    (cap-time :inner (reduce + (range x)))))
```

This prints a summary that looks a bit like:

```
:inner  =>  {:count 1000, :average 0.07283200000000019, :sum 72.83200000000019}
:total  =>  (86.418)
```

The same block with ```with-times``` returns a Map that contains all the interim values, rather than the summary.

