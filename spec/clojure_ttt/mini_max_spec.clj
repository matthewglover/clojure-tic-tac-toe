(ns clojure-ttt.mini-max-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.mini-max :refer :all]))

(describe "mini-max"
  (context "o o x\nx x o\no _ x"
    (it "returns final empty square"
      (should= 8
               (get-move [5 1 3 7 4 6 9 2]))))
  
  (context "o x _\no x x\n_ o x"
    (it "selects winning square when possible"
      (should= 7
               (get-move [2 1 5 4 6 8 9]))))
  
  (context "x o x\no x x\no _ _"
    (it "avoids losing when no winning move"
      (should= 9
               (get-move [1 2 3 4 5 7 6]))))
  
  (context "1 x 3\n4 5 x\no o x"
    (it "delays losing for as long as possible"
    ; See http://neverstopbuilding.com/minimax (Smart/Dumb example for visualisation)
      (should= 3
               (get-move [2 7 6 8 9])))))
