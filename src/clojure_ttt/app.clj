(ns clojure-ttt.app
  (:require [clojure-ttt.player :refer [get-move]]
            [clojure-ttt.ui.board :refer [print-board]]
            [clojure-ttt.ui.helpers :refer [clear-screen]]
            [clojure-ttt.ui.game :refer [print-result play-again?]]
            [clojure-ttt.ui.game-choice :refer [get-game-choice]]
            [clojure-ttt.moves :refer [update-moves game-over?]]))

(defn- run-next-move [game-state]
    (let [move (get-move game-state)
          updated-moves (update-moves (:moves game-state) move)]
      (assoc game-state :moves updated-moves)))

(defn run-game [game-type]
  (loop [current-state (assoc game-type :moves [])]
    (clear-screen)
    (print-board (:moves current-state))
    (let [updated-state (run-next-move current-state)]
      (if-not (game-over? (:moves updated-state))
        (recur updated-state)
        (print-result (:moves updated-state))))))

(defn run-app []
  (run-game (get-game-choice))
  (if (play-again?)
    (run-app)))
