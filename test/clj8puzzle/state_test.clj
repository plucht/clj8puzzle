(ns clj8puzzle.state-test
    (:require [clojure.test :refer :all]
              [clj8puzzle.state :refer :all]))

(deftest move-left-test
  (testing "Impossible moves should result in nil."
    (is (nil? (move left [0 1 2, 3 4 5, 6 7 8])))
    (is (nil? (move left [1 2 3, 0 4 5, 6 7 8])))
    (is (nil? (move left [1 2 3, 4 5 6, 0 7 8]))))

  (testing "Possible moves should result in a new vector."
    (is (= [1 2 3, 4 5 6, 7 0 8] (move left [1 2 3, 4 5 6, 7 8 0])))
    (is (= [0 1 2, 3 4 5, 6 7 8] (move left [1 0 2, 3 4 5, 6 7 8])))))

(deftest move-right-test
  (testing "Impossible moves should result in nil."
    (is (nil? (move right [1 2 3, 4 5 6, 7 8 0])))
    (is (nil? (move right [1 2 3, 4 5 0, 6 7 8])))
    (is (nil? (move right [1 2 0, 3 4 5, 6 7 8]))))

  (testing "Possible moves should result in a new vector."
    (is (= [1 2 3, 4 5 6, 7 8 0] (move right [1 2 3, 4 5 6, 7 0 8])))
    (is (= [1 0 2, 3 4 5, 6 7 8] (move right [0 1 2, 3 4 5, 6 7 8])))))

(deftest move-up-test
  (testing "Impossible moves should result in nil."
    (is (nil? (move up [0 1 2, 3 4 5, 6 7 8])))
    (is (nil? (move up [1 0 2, 3 4 5, 6 7 8])))
    (is (nil? (move up [1 2 0, 3 4 5, 6 7 8]))))

  (testing "Possible moves should result in a new vector."
    (is (= [0 2 3, 1 4 5, 6 7 8] (move up [1 2 3, 0 4 5, 6 7 8])))
    (is (= [1 2 3, 4 5 0, 7 8 6] (move up [1 2 3, 4 5 6, 7 8 0])))))

(deftest move-down-test
  (testing "Impossible moves should result in nil."
    (is (nil? (move down [1 2 3, 4 5 6, 7 8 0])))
    (is (nil? (move down [1 2 3, 4 5 6, 7 0 8])))
    (is (nil? (move down [1 2 3, 4 5 6, 0 7 8]))))

  (testing "Possible moves should result in a new vector."
    (is (= [1 2 3, 0 4 5, 6 7 8] (move down [0 2 3, 1 4 5, 6 7 8])))
    (is (= [1 2 3, 4 5 6, 7 8 0] (move down [1 2 3, 4 5 0, 7 8 6])))))

(defn- generated?
  "Checks if haystack contains all needles."
  [haystack needles]
  (every? #(.contains haystack %) needles))

(deftest generate-states-test
  (testing "When moving from any corner, exactly two states can be generated."
    (is (generated? (generate-states [0 1 2, 3 4 5, 6 7 8]) [[3 1 2, 0 4 5, 6 7 8]
                                                             [1 0 2, 3 4 5, 6 7 8]]))

    (is (generated? (generate-states [1 2 0, 3 4 5, 6 7 8]) [[1 2 5, 3 4 0, 6 7 8]
                                                             [1 0 2, 3 4 5, 6 7 8]]))

    (is (generated? (generate-states [1 2 3, 4 5 6, 7 8 0]) [[1 2 3, 4 5 0, 7 8 6]
                                                             [1 2 3, 4 5 6, 7 0 8]]))

    (is (generated? (generate-states [1 2 3, 4 5 6, 0 7 8]) [[1 2 3, 0 5 6, 4 7 8]
                                                             [1 2 3, 4 5 6, 7 0 8]]))))