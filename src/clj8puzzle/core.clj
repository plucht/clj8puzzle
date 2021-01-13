(ns clj8puzzle.core
  (:require [clj8puzzle.solver :refer [solve]])
  (:gen-class))

(defn state->str
  [s]
  (reduce #(str %1 %2 "\n") ""
    (map #(subs (clojure.string/join " " s) %1 (+ %1 5)) '(0 6 12))))

(defn parse-input
  "Construct vector of digits from string."
  [input]
  (mapv #(Integer/parseInt % 10) (re-seq #"\d" input)))

(defn valid-input?
  "Check wether all digits from 0 to 8 are present in collection."
  [collection]
  (= 9 (count (set (filter #(and (<= 0 %) (>= 8 %)) collection)))))

(defn -main
  [& args]
  (println "Enter game state ([1 2 3 4 5 6 0 7 8])")
  (as-> (read-line) input
        (parse-input input)
        (if (valid-input? input)
          (do
            (println "Initial state:")
            (println (state->str input))
            (println "Solution:" (solve input))
          )
          (println "Invalid input."))))