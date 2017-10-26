(ns clojure-ttt.game.human-player-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.game.human-player :refer :all]
            [clojure-ttt.ui.human-player :as ui-human-player]))

(describe "get-human-move"
  (it "returns valid human move"
    (should= 1
             (with-redefs [ui-human-player/get-human-move (constantly 1)]
               (get-human-move []))))

  (it "returns valid human move"
    (should= 5
             (with-redefs [ui-human-player/get-human-move (constantly 5)]
               (get-human-move [])))))
