(ns clojure-ttt.ui.game
  (:require [clojure-ttt.ui.board :as board-ui]
            [clojure-ttt.ui.helpers :as ui-helpers]
            [clojure-ttt.game.status :as game-status]))

(defn- format-winning-message [moves]
  (str (board-ui/convert-value-symbol-to-string (game-status/winner? moves)) " Wins!"))

(defn print-winning-message [moves]
  (println (format-winning-message moves)))

(defn print-drawing-message []
  (println "It's a draw!"))

(defn print-result [moves]
  (ui-helpers/clear-screen)
  (board-ui/print-board moves)
  (if (game-status/winner? moves)
    (print-winning-message moves)
    (print-drawing-message)))
