(ns clojure-ttt.ui.human-player-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.ui.human-player :refer :all]))

(def failing-validator (constantly nil))
(def passing-validator (constantly true))

(describe "prompt-for-move"
          (it "prints move request prompt"
              (with-in-str "\n"
                        (should= str-move-request
                          (with-out-str
                            (prompt-for-move failing-validator)))))

          (context "with a valid input"
              (it "returns square number as an integer"
                       (with-in-str "5\n"
                                (should= 5
                                  (prompt-for-move passing-validator)))))
          
          (context "with invalid input"
              (it "returns nil"
                       (with-in-str "failing input\n"
                                (should= nil
                                  (prompt-for-move failing-validator))))))

(def test-moves '(1 2 3))

(describe "input-validator"
          (context "non integer input"
                   (it "returns false"
                       (should-not
                         (input-validator test-moves "non integer text"))))

          (context "move taken input"
                   (it "returns false"
                       (should-not
                         (input-validator test-moves "1")))))

(describe "get-human-move"
          (context "human inputs valid move"
              (it "returns move as an integer"
                        (with-redefs [prompt-for-move (constantly 1)]
                            (should= 1
                                  (get-human-move test-moves))))))
