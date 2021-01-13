(ns clj8puzzle.solver-test
    (:require [clojure.test :refer :all]
              [clj8puzzle.solver :refer :all]))

(deftest goal-test
    (testing "Goal test happy case."
        (is (true? (goal? [1 2 3 4 5 6 7 8 0])))))

(deftest goal-test
    (testing "Goal test unhappy case."
        (is (false? (goal? [1 2 3 4 5 6 7 0 8])))))

(deftest filter-unexplored-test
    (testing "Only keep nodes which are neither in open list nor closed list."
        (is (= '([3 4]) (without [[1 2] [3 4] [5 6]] [1 2] [5 6]))))) ; todo: probably update test data

(deftest initial-state-is-goal-test
    (testing "Initial state is already the goal."
        (is (= [1 2 3 4 5 6 7 8 0] (solve [1 2 3 4 5 6 7 8 0])))))

(deftest one-move-away-test
    (testing "Goal is one move away."
        (is (= [1 2 3 4 5 6 7 8 0] (solve [1 2 3 4 5 6 7 0 8])))))