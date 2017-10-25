(ns clojure-ttt.game-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.game :refer :all]))

(def empty-moves [])
(def drawing-moves [2 1 4 3 5 6 7 8 9])
(def x-winning-row [1 4 2 5 3])

(describe "winner?"
  (context "empty board"
    (it "is false"
      (should-not (winner? empty-moves))))

  (context "drawn board"
    (it "is false"
      (should-not (winner? drawing-moves))))

  (context "x winning board"
    (xit "is :x"
      (should= :x
               (winner? x-winning-row)))))


(describe "run-game"
  (context "X wins"
    (xit "notifies x wins"
      (with-in-str "1\n4\n2\n5\n3\n"
        (should-contain "X Wins!"
                        (with-out-str (run-game))))))

  (context "O wins"
    (xit "notifies o wins"
      (with-in-str "9\n1\n4\n2\n5\n3\n"
        (should-contain "O Wins!"
                        (with-out-str (run-game)))))))
