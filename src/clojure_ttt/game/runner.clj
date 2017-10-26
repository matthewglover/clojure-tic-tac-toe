(ns clojure-ttt.game.runner
  (:require [clojure-ttt.player :refer [get-move]]
            [clojure-ttt.ui.board :refer [print-board]]
            [clojure-ttt.ui.helpers :refer [clear-screen]]
            [clojure-ttt.ui.game :refer [print-result]]
            [clojure-ttt.board.moves :refer [update-moves]])
  (:require clojure-ttt.game.status)
  (:refer clojure-ttt.game.status :rename {over? game-over?}))

(defn- run-next-move [game-state]
    (let [move (get-move game-state)
          updated-moves (update-moves (:moves game-state) move)]
      (assoc game-state :moves updated-moves)))

(defn run-game [player-x player-o]
  (loop [current-state {:moves [] :x player-x :o player-o}]
    (clear-screen)
    (print-board (:moves current-state))
    (let [updated-state (run-next-move current-state)]
      (if-not (game-over? (:moves updated-state))
        (recur updated-state)
        (print-result (:moves updated-state))))))
