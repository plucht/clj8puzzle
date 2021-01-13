(ns clj8puzzle.solver-test
  (:require [clojure.test :refer :all]
            [clj8puzzle.solver :refer :all]))

(deftest goal-test
  (testing "Goal test happy case."
    (is (true? (goal? [1 2 3 4 5 6 7 8 0])))))

(deftest goal-test
  (testing "Goal test unhappy case."
    (is (false? (goal? [1 2 3 4 5 6 7 0 8])))))

(deftest filter-unexplored-one-arg-test
  (testing "Only keep nodes which are not queue."
    (let [nodes
          [[1 2] [3 4] [5 6]]

          queue '([1 2] [5 6])]
          (is (= '([3 4]) (without nodes queue))))))

(deftest filter-unexplored-two-args-test
  (testing "Only keep nodes which are neither in open list nor closed list."
    (let [nodes
          [[1 2] [3 4] [5 6]]

          open-list
          (conj clojure.lang.PersistentQueue/EMPTY [1 2] [7 8])

          closed-list
          (conj #{} [5 6])]
        (is (= '([3 4]) (without nodes open-list closed-list))))))

(deftest initial-state-is-goal-test
  (testing "Initial state is already the goal."
    (is (= [1 2 3 4 5 6 7 8 0] (solve [1 2 3 4 5 6 7 8 0])))))

(deftest one-move-away-test
  (testing "Goal is one move away."
    (is (= [1 2 3 4 5 6 7 8 0] (solve [1 2 3 4 5 6 7 0 8])))
    (is (= [1 2 3 4 5 6 7 8 0] (solve [1 2 3 4 5 0 7 8 6])))))

(deftest two-moves-away-test
  (testing "Goal is two moves away."
    (is (= [1 2 3 4 5 6 7 8 0] (solve [1 2 3 4 5 6 0 7 8])))
    (is (= [1 2 3 4 5 6 7 8 0] (solve [1 2 3 4 0 5 7 8 6])))))

(deftest three-moves-away-test
  (testing "Goal is three moves away."
    (is (= [1 2 3 4 5 6 7 8 0] (solve [1 2 3 0 4 5 7 8 6])))))
