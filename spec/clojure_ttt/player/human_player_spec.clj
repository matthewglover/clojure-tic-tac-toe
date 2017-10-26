(ns clojure-ttt.player.human-player-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.player.human-player :refer :all]
            [clojure-ttt.ui.human-player :as ui-human-player]))

(describe "get-move"
  (it "returns valid human move"
    (should= 1
             (with-redefs [ui-human-player/get-move (constantly 1)]
               (get-move []))))

  (it "returns valid human move"
    (should= 5
             (with-redefs [ui-human-player/get-move (constantly 5)]
               (get-move [])))))
