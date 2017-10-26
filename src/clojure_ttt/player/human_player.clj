(ns clojure-ttt.player.human-player
  (:require [clojure-ttt.ui.human-player :as ui]))

(defn get-move [moves]
  (ui/get-move moves))
