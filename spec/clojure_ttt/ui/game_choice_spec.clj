(ns clojure-ttt.ui.game-choice-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.ui.game-choice :refer :all]
            [clojure-ttt.app :refer [game-types]]))

(describe "choose-game"
  (it "returns human vs human for choice 1"
    (should= {:x :human :o :human}
             (with-in-str "1\n"
               (choose-game game-types))))

  (it "returns human vs computer for choice 2"
    (should= {:x :human :o :computer}
             (with-in-str "2\n"
               (choose-game game-types))))

  (it "returns computer vs human for choice 3"
    (should= {:x :computer :o :human}
             (with-in-str "3\n"
               (choose-game game-types))))

  (it "returns computer vs computer for choice 4"
    (should= {:x :computer :o :computer}
             (with-in-str "4\n"
               (choose-game game-types))))

  (it "returns nil for invalid choice"
    (should= nil
             (with-in-str "5\n"
               (choose-game game-types)))))

(describe "print-game-choices"
  (it "should print each choice"
    (should-contain "Choose game type:\n(1) Human vs Human\n(2) Human vs Computer\n(3) Computer vs Human\n(4) Computer vs Computer\n"
             (with-out-str (print-game-choices game-types)))))

(describe "prompt-for-game-choice"
  (around [it] (with-out-str (it)))

  (it "selects first input if valid game choice"
      (should= {:x :computer :o :human}
               (with-in-str "3\n"
                 (prompt-for-game-choice game-types))))

  (it "waits for valid game choice"
      (should= {:x :computer :o :human}
               (with-in-str "invalid\n\5\n3\n"
                 (prompt-for-game-choice game-types))))

  (it "warns of invalid input"
      (should-contain invalid-choice-message
                      (with-in-str "invalid\n1\n"
                        (with-out-str 
                          (prompt-for-game-choice game-types))))))
