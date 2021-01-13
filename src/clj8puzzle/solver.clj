(ns clj8puzzle.solver
  (:require [clojure.set :refer [difference]]
            [clj8puzzle.state :refer [generate-states]])
  (:gen-class))

(defn goal?
  [state]
  "Check if state is the desired final state of the game."
  (= state [1 2 3 4 5 6 7 8 0]))

(defn without
  [collection & others]
  "Get collection without values present in any other given collection."
  (->> (map set others)
       (reduce #(into %1 %2) #{})
       (set)
       (difference (set collection))
       (seq)))

(defn solve [state]
  (loop [open-list (conj clojure.lang.PersistentQueue/EMPTY state)
         closed-list #{}]
        (if (goal? (peek open-list)) (peek open-list)
          (if (empty? open-list) nil ; no solution
            (let [node
                  (peek open-list)

                  generated-nodes
                  (generate-states node)

                  unexplored-nodes
                  (without generated-nodes closed-list open-list)

                  updated-open-list
                  (apply conj (pop open-list) unexplored-nodes)

                  updated-closed-list
                  (apply conj closed-list node (without generated-nodes unexplored-nodes))
                  ]
                  (recur updated-open-list updated-closed-list))))))