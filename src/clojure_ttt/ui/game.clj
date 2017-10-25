(ns clojure-ttt.ui.game
  (:require [clojure-ttt.ui.board :as board-ui]
            [clojure-ttt.game :as game]))

(defn- format-winning-message [moves]
  (str (board-ui/convert-value-symbol-to-string (game/winner? moves)) " Wins!"))

(defn print-winning-message [moves]
  (println (format-winning-message moves)))

(defn print-drawing-message []
  (println "It's a draw!"))

(defn print-result [moves]
  (if (game/winner? moves)
    (print-winning-message moves)
    (print-drawing-message)))
