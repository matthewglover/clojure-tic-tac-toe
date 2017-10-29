(ns clojure-ttt.lines-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.lines :refer :all]))

(describe "convert-squares-to-columns"
  (it "complete board to columns"
    (should= [[:o :x :x] [:x :x :o] [:o :o :x]]
             (convert-squares-to-columns [:o :x :o :x :x :o :x :o :x]))))

(describe "convert-squares-to-diagonals"
  (it "complete board to diagonals"
    (should= [[:o :x :x] [:o :x :x]]
             (convert-squares-to-diagonals [:o :x :o :x :x :o :x :o :x]))))

(describe "convert-squares-to-lines"
  (it "converts complete board to lines"
    (should= [[:o :x :o] [:x :x :o] [:x :o :x]
              [:o :x :x] [:x :x :o] [:o :o :x]
              [:o :x :x] [:o :x :x]]
             (convert-squares-to-lines [:o :x :o :x :x :o :x :o :x]))))

(describe "convert-moves-to-lines"
  (it "converts complete board to lines"
    (should= [[:o :x :o] [:x :x :o] [:x :o :x]
              [:o :x :x] [:x :x :o] [:o :o :x]
              [:o :x :x] [:o :x :x]]
             (convert-moves-to-lines [2 1 4 3 5 6 7 8 9]))))
