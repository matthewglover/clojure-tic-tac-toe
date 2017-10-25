(ns clojure-ttt.board.data-converters-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board.data-converters :refer :all]))

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
