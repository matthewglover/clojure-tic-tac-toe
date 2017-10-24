(ns clojure-ttt.ui.human-player-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.ui.human-player :refer :all]))

(def test-moves [1 2 3])

(describe "get-human-move"
  (around [it]
    (with-out-str (it)))

  (it "prints move request prompt"
    (with-in-str "5\n"
      (should= request-move-message
        (with-out-str
          (get-human-move test-moves)))))

  (context "user enters valid move on first try"
    (it "returns square number as an integer"
      (with-in-str "5\n"
        (should= 5
          (get-human-move test-moves)))))
  
  (context "user enters invalid moves"
    (it "returns first valid input"
      (with-in-str "failing input\n2\n4\n"
        (should= 4 
          (get-human-move test-moves))))
      
    (it "alerts user of invalid move"
      (with-in-str "failing input\n4\n"
        (should-contain invalid-move-message
          (with-out-str
            (get-human-move test-moves)))))))
