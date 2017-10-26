(ns clojure-ttt.core
  (:require [clojure-ttt.ui.board :as board-ui]
            [clojure-ttt.ui.human-player :as human-player-ui]
            [clojure-ttt.ui.game :as game-ui]
            [clojure-ttt.ui.helpers :as ui-helpers]
            [clojure-ttt.board.moves :as board-moves]
            [clojure-ttt.game.status :as game-status]))

(defn run-game []
  (loop [moves []]
    (let [move (human-player-ui/get-human-move moves)
          moves (board-moves/update-moves moves move)]
      (if (game-status/over? moves)
        (game-ui/print-result moves)
        (recur moves))))) 

(defn -main [& args]
  (run-game))

