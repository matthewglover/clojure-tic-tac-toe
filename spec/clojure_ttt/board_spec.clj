(ns clojure-ttt.board-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board :refer :all]))

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


(describe "convert-to-board-data"
  (context "empty board"
    (it "returns empty board data"
      (should= [[1 nil] [2 nil] [3 nil] [4 nil] [5 nil] [6 nil] [7 nil] [8 nil] [9 nil]]
               (convert-to-board-data []))))

  (context "single move middle square"
    (it "returns board with :x as value for square 5"
      (should= [[1 nil] [2 nil] [3 nil] [4 nil] [5 :x] [6 nil] [7 nil] [8 nil] [9 nil]]
               (convert-to-board-data [5]))))

  (context "complete board"
    (it "returns complete board"
      (should= [[1 :o] [2 :x] [3 :o] [4 :x] [5 :x] [6 :o] [7 :x] [8 :o] [9 :x]]
               (convert-to-board-data [2 1 4 3 5 6 7 8 9])))))


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
