(ns clojure-ttt.game.status-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.game.status :refer :all]))

(def empty-moves [])
(def drawing-moves [2 1 4 3 5 6 7 8 9])
(def x-winning-row [1 4 2 5 3])
(def o-winning-row [9 1 4 2 5 3])

(describe "winner?"
  (context "empty board"
    (it "is falsy"
      (should-not (winner? empty-moves))))

  (context "drawn board"
    (it "is falsy"
      (should-not (winner? drawing-moves))))

  (context "x winning board"
    (it "is :x"
      (should= :x
               (winner? x-winning-row))))

  (context "o winning board"
    (it "is :o"
      (should= :o
               (winner? o-winning-row)))))

(describe "board-full?"
  (it "is false for empty board"
    (should-not (board-full? empty-moves)))

  (it "is true for full board"
    (should (board-full? drawing-moves))))

(describe "over?"
  (it "is false for empty board"
    (should-not (over? empty-moves)))

  (it "is true for x-winning board"
    (should (over? x-winning-row)))

  (it "is true for o-winning-row board"
    (should (over? o-winning-row)))

  (it "is true for drawn board"
    (should (over? drawing-moves))))
