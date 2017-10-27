(ns clojure-ttt.player-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.player :as player]
            [clojure-ttt.player.computer-player :as computer-player]
            [clojure-ttt.player.human-player :as human-player]))

(describe "player/get-move"
  (around [it]
    (with-out-str (it)))

  (it "dispatches first move to computer player"
    (should= 5
             (with-redefs [computer-player/get-move (constantly 5)]
               (player/get-move {:moves [] :x :computer :o :human}))))

  (it "dispatches second move to human player"
    (should= 3
             (with-in-str "3\n" 
               (player/get-move {:moves [1] :x :computer :o :human})))))
