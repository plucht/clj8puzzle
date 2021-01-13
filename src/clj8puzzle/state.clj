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

(defn move
  "Returns a vector with '0' moved one field in direction or nil if '0' can't be moved."
  [direction state]
  (direction state (index-of state 0)))

(defn left
  [state i]
  (if (#{0 3 6} i) nil (swapv state i (dec i))))

(defn right
  [state i]
  (if (#{2 5 8} i) nil (swapv state i (inc i))))

(defn up
  [state i]
  (if (#{0 1 2} i) nil (swapv state i (- i 3))))

(defn down
  [state i]
  (if (#{6 7 8} i) nil (swapv state i (+ i 3))))

(defn generate-states
  "Returns a list of all possible next states for the current (given) state."
  [state]
  (filter some? [(move up state) (move right state) (move down state) (move left state)]))