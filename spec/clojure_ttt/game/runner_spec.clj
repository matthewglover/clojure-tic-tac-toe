(ns clojure-ttt.game.runner-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.player.computer-player :as computer-player]
            [clojure-ttt.game.runner :refer :all]))

(describe "run-game"
  (with-redefs [computer-player/get-move computer-player/get-move-directly]
    (context "X wins"
            (it "notifies x wins"
                (with-in-str "3\n6\n9\n"
                  (should-contain "X Wins!"
                                  (with-out-str (run-game :human :computer))))))

    (context "O wins"
            (it "notifies o wins"
                (with-in-str "4\n5\n9\n"
                  (should-contain "O Wins!"
                                  (with-out-str (run-game :human :computer))))))

    (context "Drawn game"
            (it "notifies draw"
                (with-in-str (str (clojure.string/join "\n" [2 1 4 3 5 6 7 8 9]) "\n")
                  (should-contain "It's a draw!"
                                  (with-out-str (run-game :human :human))))))))
