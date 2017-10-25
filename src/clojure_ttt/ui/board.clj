(ns clojure-ttt.ui.board
  (:require [clojure.string :as string] 
            [clojure-ttt.board.data-converters :as data-converters]
            [clojure-ttt.ui.colours :as colours]))

(def square-colours {:x colours/blue :o colours/green})

(defn- add-row-dividers [row]
  (string/join "|" row))

(defn- pad-square [formatted-value]
  (str " " formatted-value " "))

(defn- convert-value-symbol-to-string [value]
  (string/upper-case (name value)))

(defn- format-square-value [value]
  (str (value square-colours)
       (convert-value-symbol-to-string value)
       colours/reset))

(defn format-square-data [[number value]]
  (if value
    (format-square-value value)
    (str number)))

(defn format-board [moves]
  (->> moves
       data-converters/convert-to-board-data
       (map format-square-data)
       (map pad-square)
       (partition 3)
       (map add-row-dividers)
       (interpose "------------")
       (string/join "\n")))

(defn print-board [moves]
  (println (format-board moves)))
