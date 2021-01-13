(ns clj8puzzle.solver
    (:require [clojure.set :refer [difference]])
    (:gen-class))
  
(defn goal? [state] 
    (let [goal [1 2 3 4 5 6 7 8 0]] 
        (= goal state)))

(defn filter-unexplored [candidates cl ol] 
    (seq (difference (set candidates) (set [cl ol]))))

(defn solve [state]
    (loop [open-list (conj clojure.lang.PersistentQueue/EMPTY state)
           closed-list []]
            (if (goal? (peek open-list)) (peek open-list) 
                (if (empty? open-list) nil ; no solution
                (let [node 
                      (peek open-list)
                      
                      generated-nodes 
                      [[1 2 3 4 5 6 7 8 0] [1 2 3 4 5 6 0 7 8] [1 2 3 4 0 6 7 5 8]] ; stub values
                      
                      unexplored-nodes 
                      (filter-unexplored generated-nodes closed-list open-list)
                      
                      updated-open-list 
                      (apply conj (pop open-list) unexplored-nodes)
                      
                      updated-closed-list 
                      (conj closed-list node (seq (difference (set generated-nodes) (set unexplored-nodes))))
                     ]
                    (recur updated-open-list updated-closed-list)
                ))
            )       
    ))