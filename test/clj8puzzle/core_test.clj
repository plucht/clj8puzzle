(ns clj8puzzle.core-test
  (:require [clojure.test :refer :all]
            [clj8puzzle.core :refer :all]))

(deftest pretty-print-test
  (testing "It transforms the state to a pretty string."
    (is (= (state->str [1 2 3 4 5 6 7 8 0]) "1 2 3\n4 5 6\n7 8 0\n"))))