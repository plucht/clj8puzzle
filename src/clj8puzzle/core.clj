(ns clj8puzzle.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def initialState [1 2 3
                   4 5 6
                   7 0 8])

(defn index-of 
  "Returns zero-based index of a number in the given state."
  [state n] 
  (get (zipmap state (iterate inc 0)) n))

(defn coordinates-of 
  "Returns a vector of zero-indexed x,y coordinates."
  [state n] 
  (let [i (index-of state n)] 
    [(int (/ i 3)) (rem i 3)]))

(defn swapv 
  "Returns a vector with values of indices i and j swapped."
  [v i j] 
  (assoc v i (v j) j (v i)))

(defn move-left 
  "Returns a vector with '0' moved left one field or nil if '0' can't be moved."
  [state] 
  (let [i (index-of state 0)] 
    (if (#{0 3 6} i) nil (swapv state i (dec i)))))

(defn move-right
  "Returns a vector with '0' moved right one field or nil if '0' can't be moved."
  [state] 
  (let [i (index-of state 0)] 
    (if (#{2 5 8} i) nil (swapv state i (inc i)))))

(defn move-up
  "Returns a vector with '0' moved up one field or nil if '0' can't be moved."
  [state] 
  (let [i (index-of state 0)] 
    (if (#{0 1 2} i) nil (swapv state i (- i 3)))))

(defn move-down
  "Returns a vector with '0' moved down one field or nil if '0' can't be moved."
  [state] 
  (let [i (index-of state 0)] 
    (if (#{6 7 8} i) nil (swapv state i (+ i 3)))))