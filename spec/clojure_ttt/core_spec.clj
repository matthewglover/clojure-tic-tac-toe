(ns clojure-ttt.core-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.core :refer :all]))

(describe "run-game"
  (context "X wins"
    (it "notifies x wins"
      (with-in-str "1\n4\n2\n5\n3\n"
        (should-contain "X Wins!"
                        (with-out-str (run-game))))))

  (context "O wins"
    (it "notifies o wins"
      (with-in-str "9\n1\n4\n2\n5\n3\n"
        (should-contain "O Wins!"
                        (with-out-str (run-game))))))

  (context "Drawn game"
    (it "notifies draw"
      (with-in-str (str (clojure.string/join "\n" [2 1 4 3 5 6 7 8 9]) "\n")
        (should-contain "It's a draw!"
                        (with-out-str (run-game)))))))
