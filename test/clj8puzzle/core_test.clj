(ns clj8puzzle.core-test
  (:require [clojure.test :refer :all]
            [clj8puzzle.core :refer :all]))

(deftest pretty-print-test
  (testing "It transforms the state to a pretty string."
    (is (= (state->str [1 2 3 4 5 6 7 8 0]) "1 2 3\n4 5 6\n7 8 0"))))

(deftest valid-input?-test
  (testing "Input is valid if it contains all digits from 0..8 (inclusive)."
    (is (true? (valid-input? [1 2 3 4 5 6 7 8 0]))))
  (testing "Input is invalid if something is missing."
    (is (false? (valid-input? [1 2 3]))))
  (testing "Input is invalid if any number is negative."
    (is (false? (valid-input? [-1 2 3 4 5 6 7 8 0]))))
  (testing "Input is invalid if any number is greater than 8."
    (is (false? (valid-input? [1 2 3 4 5 6 7 8 9])))))