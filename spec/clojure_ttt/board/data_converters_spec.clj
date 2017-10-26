(ns clojure-ttt.board.data-converters-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board.data-converters :refer :all]))

(describe "convert-to-board-data"
  (it "returns empty board data"
    (should= [[1 nil] [2 nil] [3 nil] [4 nil] [5 nil] [6 nil] [7 nil] [8 nil] [9 nil]]
              (convert-to-board-data [])))

  (it "returns board with :x as value for square 5"
    (should= [[1 nil] [2 nil] [3 nil] [4 nil] [5 :x] [6 nil] [7 nil] [8 nil] [9 nil]]
              (convert-to-board-data [5])))

  (it "returns complete board"
    (should= [[1 :o] [2 :x] [3 :o] [4 :x] [5 :x] [6 :o] [7 :x] [8 :o] [9 :x]]
              (convert-to-board-data [2 1 4 3 5 6 7 8 9]))))

(describe "convert-to-board-values"
  (it "converts empty board"
    (should= [nil nil nil nil nil nil nil nil nil]
              (convert-to-board-values [])))

  (it "converts complete board"
    (should= [:o :x :o :x :x :o :x :o :x]
              (convert-to-board-values [2 1 4 3 5 6 7 8 9]))))

(describe "convert-board-values-to-columns"
  (it "complete board to columns"
    (should= [[:o :x :x] [:x :x :o] [:o :o :x]]
             (convert-board-values-to-columns [:o :x :o :x :x :o :x :o :x]))))

(describe "convert-board-values-to-tl-diagonal"
  (it "complete board to diagonals"
    (should= [:o :x :x]
             (convert-board-values-to-tl-diagonal [:o :x :o :x :x :o :x :o :x])))

  (it "complete board to diagonals"
    (should= [:o :x nil]
             (convert-board-values-to-tl-diagonal [:o :x :o :x :x :o :x :o nil]))))

(describe "convert-board-values-to-tr-diagonal"
  (it "complete board to diagonals"
    (should= [:o :x :x]
             (convert-board-values-to-tr-diagonal [:o :x :o :x :x :o :x :o :x])))

  (it "complete board to diagonals"
    (should= [:o nil :x]
             (convert-board-values-to-tr-diagonal [:o :x :o :x nil :o :x :o nil]))))

(describe "convert-board-values-to-diagonals"
  (it "complete board to diagonals"
    (should= [[:o :x :x] [:o :x :x]]
             (convert-board-values-to-diagonals [:o :x :o :x :x :o :x :o :x]))))

(describe "convert-board-values-to-lines"
  (it "converts complete board to lines"
    (should= [[:o :x :o] [:x :x :o] [:x :o :x]
              [:o :x :x] [:x :x :o] [:o :o :x]
              [:o :x :x] [:o :x :x]]
             (convert-board-values-to-lines [:o :x :o :x :x :o :x :o :x]))))

(describe "get-lines-from-moves"
  (it "converts complete board to lines"
    (should= [[:o :x :o] [:x :x :o] [:x :o :x]
              [:o :x :x] [:x :x :o] [:o :o :x]
              [:o :x :x] [:o :x :x]]
             (get-lines-from-moves [2 1 4 3 5 6 7 8 9]))))
