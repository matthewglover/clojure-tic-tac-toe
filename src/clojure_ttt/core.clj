(ns clojure-ttt.core
  (:require [clojure-ttt.ui.human-player :refer [get-human-move]]
            [clojure-ttt.ui.game :refer [print-result]]
            [clojure-ttt.board.moves :refer [update-moves]]
            [clojure-ttt.game.status :refer [over?]]))

(defn run-game []
  (loop [moves []]
    (let [move (get-human-move moves)
          moves (update-moves moves move)]
      (if (over? moves)
        (print-result moves)
        (recur moves))))) 

(defn -main [& args]
  (run-game))

