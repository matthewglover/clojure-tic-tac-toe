(ns clojure-ttt.ui.board-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.ui.board :refer :all]
            [clojure-ttt.ui.colours :as colours]))


(describe "format-square-data"
  (it "formats empty square as square number"
    (should= "1"
             (format-square-data nil [1 nil])))

  (it "formats :x square as X"
    (should= (str colours/blue "X" colours/reset)
             (format-square-data nil [1 :x])))

  (it "formats :o square as O"
    (should= (str colours/green "O" colours/reset)
             (format-square-data nil [1 :o]))))

(describe "converts moves to string representation of board"
  (context "empty board"
    (it "formats empty board"
      (should= (clojure.string/join "\n" [" 1 | 2 | 3 " "------------" " 4 | 5 | 6 " "------------" " 7 | 8 | 9 "])
               (format-board []))))

  (context "with moves"
    (it "formats squares properly"
      (should= (clojure.string/join "\n" [(str  " " colours/yellow "O" colours/reset " | " colours/blue "X" colours/reset " | 3 ") 
                 "------------" " 4 | 5 | 6 " "------------" " 7 | 8 | 9 "])
               (format-board [2 1])))))

(describe "format-board"
  (it "prints moves as a board"
    (should= (str (format-board [2 1]) "\n")
             (with-out-str (print-board [2 1])))))
