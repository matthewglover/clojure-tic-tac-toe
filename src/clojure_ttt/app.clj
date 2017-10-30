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

(defn- display-current-game-state [state]
  (clear-screen)
  (print-board (:moves state)))

(defn- display-final-game-state [state]
  (print-result (:moves state)))

(defn run-game [game-type]
  (loop [current-state (assoc game-type :moves [])]
    (display-current-game-state current-state)
    (let [updated-state (run-next-move current-state)]
      (if-not (game-over? (:moves updated-state))
        (recur updated-state)
        (display-final-game-state updated-state)))))

(defn run-app []
  (run-game (prompt-for-game-choice game-types))
  (if (play-again?)
    (run-app)))

