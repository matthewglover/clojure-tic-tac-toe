(ns clojure-ttt.player
  (:require [clojure-ttt.board.moves :refer [get-next-player]]
            [clojure-ttt.ui.human-player :as ui-human-player]
            [clojure-ttt.computer-player :as computer-player]))

(defn get-next-player-type [game-state]
  (->> game-state
       :moves
       get-next-player
       game-state))

(defmulti get-move get-next-player-type)

(defmethod get-move :human [{moves :moves}]
  (ui-human-player/get-move moves))

(defmethod get-move :computer [{moves :moves}]
  (computer-player/get-move moves))
