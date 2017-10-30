(ns clojure-ttt.ui.game
  (:require [clojure-ttt.ui.board :refer [convert-value-symbol-to-string print-board]]
            [clojure-ttt.ui.helpers :refer [clear-screen]]
            [clojure-ttt.moves :refer [winner?]]))

(defn- format-winning-message [moves]
  (str (convert-value-symbol-to-string (winner? moves)) " Wins!"))

(defn print-winning-message [moves]
  (println (format-winning-message moves)))

(defn print-drawing-message []
  (println "It's a draw!"))

(defn print-result [moves]
  (clear-screen)
  (print-board moves)
  (if (winner? moves)
    (print-winning-message moves)
    (print-drawing-message)))

(defn print-play-again-message []
  (print "Play again? [y/n]: ")
  (flush))

(defn play-again? []
  (print-play-again-message)
  (= "y" (read-line)))
