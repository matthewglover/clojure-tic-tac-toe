(ns clojure-ttt.ui.board
  (:require [clojure.string :as string] 
            [clojure-ttt.board.data-converters :as data-converters]
            [clojure-ttt.ui.colours :as colours]
            [clojure-ttt.board.moves :refer [last-move-value]]))

(defn- add-row-dividers [row]
  (string/join "|" row))

(defn- pad-square [formatted-value]
  (str " " formatted-value " "))

(defn convert-value-symbol-to-string [value]
  (string/upper-case (name value)))

(defn get-square-colour [value is-last-move]
  (let [square-colours {:x colours/blue :o colours/green}] 
    (if is-last-move
      colours/yellow
      (value square-colours))))

(defn- format-square-value [value is-last-move]
  (str (get-square-colour value is-last-move)
       (convert-value-symbol-to-string value)
       colours/reset))

(defn format-square-data [last-move [number value]]
  (if value
    (format-square-value value (= last-move number))
    (str number)))

(defn format-board [moves]
  (->> moves
       data-converters/convert-to-board-data
       (map (partial format-square-data (last moves)))
       (map pad-square)
       (partition 3)
       (map add-row-dividers)
       (interpose "------------")
       (string/join "\n")))

(defn print-board [moves]
  (println (format-board moves)))
