(ns clojure-ttt.core
  (:require [clojure-ttt.ui.human-player :refer [get-human-move]]
            [clojure-ttt.ui.game :refer [print-result]]
            [clojure-ttt.board.moves :refer [update-moves]])
  (:require clojure-ttt.game.status)
  (:refer clojure-ttt.game.status :rename {over? game-over?}))

(defn run-game []
  (loop [moves []]
    (let [move (get-human-move moves)
          moves (update-moves moves move)]
      (if (game-over? moves)
        (print-result moves)
        (recur moves))))) 

(defn -main [& args]
  (run-game))

