(ns clojure-ttt.alpha-beta-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.alpha-beta :refer :all]))

(describe "result-score"
  (it "maximizing at depth zero returns 10"
    (should= 10
             (result-score true 0)))

  (it "maximizing at depth two returns 8"
    (should= 8 
             (result-score true 2)))

  (it "minimizing at depth zero returns -10"
    (should= -10
             (result-score false 0)))

  (it "minimizing at depth two returns -8"
    (should= -8
             (result-score false 2))))

(describe "move-score"
  (it "loser at depth two depth returns -8"
    (should= -8
             (move-score 10 false 2 -100 100 [1 4 2 5 3])))

  (it "winner at depth two returns 8"
    (should= 8
             (move-score 10 true 2 -100 100 [1 4 2 5 3])))

  (it "draw at any depth returns 0"
    (should= 0
             (move-score 10 true 2 -100 100 [5 1 3 7 4 6 9 2 9]))))

(describe "better-move"
  (context "maximizing"
    (it "is better when new-score > alpha"
        (should (better-move? true -10 10 10)))

    (it "is not better when new-score < alpha"
        (should-not (better-move? true 6 10 5))))

  (context "minimizing"
    (it "is better when new-score < beta"
        (should (better-move? false -10 10 -10)))

    (it "is not better when new-score > beta"
        (should-not (better-move? false -10 -5 -1)))))

(describe "get-move"
  (context "\no o x\nx x o\no _ x\n"
    (it "returns final empty square"
      (should= 8
               (get-move [5 1 3 7 4 6 9 2]))))

  (context "\no x _\no x x\n_ o x\n"
    (it "selects winning square when possible"
      (should= 7
               (get-move [2 1 5 4 6 8 9]))))

  (context "\nx o x\no x x\no _ _\n"
    (it "avoids losing when no winning move"
      (should= 9
              (get-move [1 2 3 4 5 7 6]))))

  (context "1 x 3\n4 5 x\no o x"
    (it "delays losing for as long as possible"
    ; See http://neverstopbuilding.com/minimax (Smart/Dumb example for visualisation)
      (should= 3
               (get-move [2 7 6 8 9]))))

  (context "1 x 3\n4 5 x\no o x"
      (it "setting search depth can affect result"
        (should= 1
                (get-move 0 [2 7 6 8 9])))))
