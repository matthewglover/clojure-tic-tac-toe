(ns clojure-ttt.game.human-player
  (:require [clojure-ttt.ui.human-player :as ui-human-player]))

(defn get-human-move [moves]
  (ui-human-player/get-human-move moves))
