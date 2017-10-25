(ns clojure-ttt.board.moves-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board.moves :refer :all]))

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
