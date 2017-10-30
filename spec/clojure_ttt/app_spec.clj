(ns clojure-ttt.app-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.computer-player :as computer-player]
            [clojure-ttt.app :refer :all]))

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
