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
                                  (with-out-str (run-game {:x :human :o :computer}))))))

    (context "O wins"
            (it "notifies o wins"
                (with-in-str "4\n5\n9\n"
                  (should-contain "O Wins!"
                                  (with-out-str (run-game {:x :human :o :computer}))))))

    (context "Drawn game"
            (it "notifies draw"
                (with-in-str (str (clojure.string/join "\n" [2 1 4 3 5 6 7 8 9]) "\n")
                  (should-contain "It's a draw!"
                                  (with-out-str (run-game {:x :human :o :human}))))))))

(describe "run-app"
    (context "Select Human vs Human (1)"
             (it "Plays Human vs Human game"
                 (should-contain "X Wins!"
                                 (with-out-str (with-in-str "1\n1\n4\n2\n5\n3\n"
                                   (run-app))))))

    (context "Don't play again"
             (it "Offers play again - exits if not selected"
                 (should-contain "Play again? [y/n]: "
                                 (with-out-str (with-in-str "1\n1\n4\n2\n5\n3\nn\n"
                                   (run-app))))))

    (context "Play again"
             (it "Offers play again - plays again"
                 (should-contain #"(?s)Play again\? \[y/n]:.*Choose game type:"
                                 (with-out-str (with-in-str "1\n1\n4\n2\n5\n3\ny\n1\n1\n4\n2\n5\n3\nn\n"
                                    (run-app)))))))
