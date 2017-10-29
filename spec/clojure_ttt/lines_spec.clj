(ns clojure-ttt.lines-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.lines :refer :all]))

(describe "convert-board-values-to-columns"
  (it "complete board to columns"
    (should= [[:o :x :x] [:x :x :o] [:o :o :x]]
             (convert-board-values-to-columns [:o :x :o :x :x :o :x :o :x]))))

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