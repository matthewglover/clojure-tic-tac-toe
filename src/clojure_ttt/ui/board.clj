(ns clojure-ttt.ui.board
  (:require [clojure.string :as string] 
            [clojure-ttt.board :as board]
            [clojure-ttt.ui.colours :as colours]))

(def square-colours {:x colours/blue :o colours/green})

(defn convert-value-symbol-to-string [value]
  (string/upper-case (name value)))
(defn format-square-value [value]
  (str (value square-colours)
       (convert-value-symbol-to-string value)
       colours/reset))


(defn format-square-data [[number value]]
  (if value
    (format-square-value value)
    (str number)))

(defn format-board [moves]
  (->> moves
       board/convert-to-board-data
       (map format-square-data)
       (map #(str " " % " "))
       (partition 3)
       (map #(string/join "|" %))
       (interpose "------------")))
