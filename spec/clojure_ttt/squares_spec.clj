(ns clojure-ttt.squares-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.squares :refer :all]))

(describe "convert-moves-to-squares"
  (it "returns empty board data"
    (should= [nil nil nil nil nil nil nil nil nil]
              (convert-moves-to-squares [])))

  (it "returns board with :x as value for square 5"
    (should= [nil nil nil nil :x nil nil nil nil]
              (convert-moves-to-squares [5])))

  (it "returns complete board"
    (should= [:o :x :o :x :x :o :x :o :x]
              (convert-moves-to-squares [2 1 4 3 5 6 7 8 9]))))

