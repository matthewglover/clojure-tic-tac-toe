(ns clojure-ttt.player.computer-player-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.player.computer-player :refer :all]))

(describe "get-move"
  (it "returns 1 on first move"
    (should= 1
             (get-move [])))

  (it "returns 2 if 1 is taken"
    (should= 2
             (get-move [1 3]))))
