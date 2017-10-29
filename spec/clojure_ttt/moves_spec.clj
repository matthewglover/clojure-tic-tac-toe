(ns clojure-ttt.moves-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.moves :refer :all]))

(describe "get-last-player"
  (context "empty board"
    (it "returns nil"
      (should= nil
               (get-last-player []))))
  
  (context "X was last player"
    (it "returns :x"
      (should= :x
               (get-last-player [1]))))
  
  (context "O was last player"
    (it "returns :o"
      (should= :o
               (get-last-player [1 2])))))

(describe "update-moves"
  (context "empty board"
    (it "returns moves with single move added"
      (should= [1]
               (update-moves [] 1))))

  (context "partial board"
    (it "adds new move to end of board"
      (should= [2 1 3 4]
               (update-moves [2 1 3] 4))))

  (context "partial board"
    (it "doesnt add move if already made"
      (should= [2 1 3]
               (update-moves [2 1 3] 3)))))

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
