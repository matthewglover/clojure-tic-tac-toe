(ns clojure-ttt.game.runner
  (:require [clojure-ttt.game.human-player :refer [get-human-move]]
            [clojure-ttt.ui.game :refer [print-result]]
            [clojure-ttt.ui.board :refer [print-board]]
            [clojure-ttt.ui.helpers :refer [clear-screen]]
            [clojure-ttt.board.moves :refer [update-moves]])
  (:require clojure-ttt.game.status)
  (:refer clojure-ttt.game.status :rename {over? game-over?}))


(defn display-last-move [moves]
  (clear-screen)
  (print-board moves))

(defn play-next-move [moves]
    (let [move (get-human-move moves)
          moves (update-moves moves move)]
      moves))

(defn run-game []
  (loop [moves []]
    (display-last-move moves)
    (let [updated-moves (play-next-move moves)]
      (if-not (game-over? updated-moves)
        (recur updated-moves)
        (print-result updated-moves)))))
