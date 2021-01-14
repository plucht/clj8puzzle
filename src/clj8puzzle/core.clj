(ns clj8puzzle.core
  (:require [clj8puzzle.solver :refer [shortest-path solve]])
  (:gen-class))

(defn state->str
  [s]
  (->> (map #(subs (clojure.string/join " " s) %1 (+ %1 5)) '(0 6 12))
       (clojure.string/join "\n")))

(defn print-path
  [path]
  (loop [p path]
    (if (seq p)
      (do
        (println "Moving to:")
        (println (state->str (peek p)))
        (println "")
        (recur (pop p))))))

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
  (println "Enter initial state as sequence of digits 0..8 (i.e. \"1 2 3 4 5 6 0 7 8\"):")
  (as-> (read-line) input
        (parse-input input)
        (if (valid-input? input)
          (if-let [[_, solution-space] (solve input)]
            (print-path (shortest-path solution-space input))
            (println "No solution found."))
          (println "Invalid input."))))