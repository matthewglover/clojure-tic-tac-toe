(ns clojure-ttt.game.runner
  (:require [clojure-ttt.player.human-player :refer [get-move]]
            [clojure-ttt.ui.board :refer [print-board]]
            [clojure-ttt.ui.helpers :refer [clear-screen]]
            [clojure-ttt.ui.game :refer [print-result]]
            [clojure-ttt.board.moves :refer [update-moves]])
  (:require clojure-ttt.game.status)
  (:refer clojure-ttt.game.status :rename {over? game-over?}))


(defn- run-next-move [moves]
    (let [move (get-move moves)
          moves (update-moves moves move)]
      moves))

(defn run-game []
  (loop [current-moves []]
    (clear-screen)
    (print-board current-moves)
    (let [updated-moves (run-next-move current-moves)]
      (if-not (game-over? updated-moves)
        (recur updated-moves)
        (print-result updated-moves)))))
