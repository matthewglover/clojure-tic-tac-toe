(ns clojure-ttt.app
  (:require [clojure-ttt.player :refer [get-move]]
            [clojure-ttt.ui.board :refer [print-board]]
            [clojure-ttt.ui.helpers :refer [clear-screen]]
            [clojure-ttt.ui.game :refer [print-result play-again?]]
            [clojure-ttt.ui.game-choice :refer [prompt-for-game-choice]]
            [clojure-ttt.moves :refer [update-moves game-over?]]))

(def game-types [{:x :human :o :human}
                 {:x :human :o :computer}
                 {:x :computer :o :human}
                 {:x :computer :o :computer}])

(defn- run-next-move [game-state]
  (->> game-state
       get-move
       (update-moves (:moves game-state))
       (assoc game-state :moves)))

(defn run-game [game-type]
  (loop [current-state (assoc game-type :moves [])]
    (clear-screen)
    (print-board (:moves current-state))
    (let [updated-state (run-next-move current-state)]
      (if-not (game-over? (:moves updated-state))
        (recur updated-state)
        (print-result (:moves updated-state))))))

(defn run-app []
  (run-game (prompt-for-game-choice game-types))
  (if (play-again?)
    (run-app)))

