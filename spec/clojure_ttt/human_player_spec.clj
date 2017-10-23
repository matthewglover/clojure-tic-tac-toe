(ns clojure-ttt.human-player-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.human-player :refer :all]))

(def failing-validator (constantly nil))
(def passing-validator (constantly true))

(describe "prompt-for-move"
          (it "prints move request prompt"
              (should= str-move-request
                        (with-out-str
                          (with-in-str "\n"
                            (prompt-for-move failing-validator)))))

          (context "with a valid input"
                   (it "returns square number as an integer"
                       (should= 5
                                (with-in-str "5\n"
                                  (prompt-for-move passing-validator)))))
          
          (context "with invalid input"
                   (it "returns nil"
                       (should= nil
                                (with-in-str "failing input\n"
                                  (prompt-for-move failing-validator))))))
