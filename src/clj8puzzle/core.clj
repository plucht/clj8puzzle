(ns clj8puzzle.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  ; probably something like: 
  ;   read input
  ;   update state or find shortest path to final state
  ;   output result
  [& args]
  (println "Hello, World!"))

; only for REPL interaction - remove before release
(def initialState [1 2 3
                   4 5 6
                   7 0 8])
