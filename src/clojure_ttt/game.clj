(ns clojure-ttt.game
  (:require [clojure-ttt.ui.board :as board-ui]
            [clojure-ttt.ui.human-player :as human-player-ui]
            [clojure-ttt.board.moves :as board-moves]))

(defn winner? [moves]
  nil)

(defn run-game []
  (loop [moves []]
    (println "\033[H\033[2J")
    (board-ui/print-board moves)
    (let [move (human-player-ui/get-human-move moves)
          moves (board-moves/update-moves moves move)]
      (if (= 5 (count moves))
        (println "Bye bye")
        (recur moves)))))
