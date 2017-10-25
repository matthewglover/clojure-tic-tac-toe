(ns clojure-ttt.core
  (:require [clojure-ttt.ui.board :as board-ui]
            [clojure-ttt.ui.human-player :as human-player-ui]
            [clojure-ttt.board.moves :as board-moves]))


(defn -main
  [& args]
  (loop [moves []]
    (println "\033[H\033[2J")
    (println (board-ui/format-board moves))
    (let [move (human-player-ui/get-human-move moves)
          moves (board-moves/update-moves moves move)]
      (if (= 9 (count moves))
        (println "Bye bye")
        (recur moves)))))

