(ns clj8puzzle.solver
  (:require [clojure.set :refer [difference]]
            [clj8puzzle.state :refer [generate-states]])
  (:gen-class))

(defn goal?
  "Check if state is the desired final state of the game."
  [state]
  (= state [1 2 3 4 5 6 7 8 0]))

(defn without
  "Get collection without values present in any other given collection."
  [collection & others]
  (->> (map set others)
       (reduce #(into %1 %2) #{})
       (set)
       (difference (set collection))
       (seq)))

(defn solve [state]
  (loop [open-list (conj clojure.lang.PersistentQueue/EMPTY state)
         closed-list #{}
         solution-space {}
         ]
        (if (goal? (peek open-list)) (list (peek open-list) solution-space)
          (if (empty? open-list) nil ; no solution
            (let [node
                  (peek open-list)

                  generated-nodes
                  (generate-states node)

                  unexplored-nodes
                  (without generated-nodes closed-list open-list)

                  updated-solution-space
                  (apply assoc solution-space (interleave unexplored-nodes (repeat node)))

                  updated-open-list
                  (apply conj (pop open-list) unexplored-nodes)

                  updated-closed-list
                  (apply conj closed-list node (without generated-nodes unexplored-nodes))
                  ]
                  (recur updated-open-list updated-closed-list updated-solution-space))))))