(ns clj8puzzle.state
  (:gen-class))

(defn index-of
  "Returns zero-based index of a number in the given state."
  [state n]
  (get (zipmap state (iterate inc 0)) n))

(defn swapv
  "Returns a vector with values at indices i and j swapped."
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

(defn generate-states
  "Returns a list of all possible next states for the current (given) state."
  [state]
  (filter some? [(move-up state) (move-right state) (move-down state) (move-left state)]))