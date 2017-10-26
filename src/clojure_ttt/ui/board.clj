(ns clojure-ttt.ui.board
  (:require [clojure.string :refer [join upper-case]] 
            [clojure-ttt.board.data-converters :refer [convert-to-board-data]]
            [clojure-ttt.ui.colours :refer [blue green yellow]))

(defn- add-row-dividers [row]
  (join "|" row))

(defn- pad-square [formatted-value]
  (str " " formatted-value " "))

(defn convert-value-symbol-to-string [value]
  (upper-case (name value)))

(defn get-square-colour [value is-last-move]
  (let [square-colours {:x blue :o green}] 
    (if is-last-move
      yellow
      (value square-colours))))

(defn- format-square-value [value is-last-move]
  (str (get-square-colour value is-last-move)
       (convert-value-symbol-to-string value)
       colours/reset))

(defn format-square-data [last-move [square-number square-value]]
  (if square-value
    (format-square-value square-value (= last-move square-number))
    (str square-number)))

(defn format-board [moves]
  (->> moves
       convert-to-board-data
       (map (partial format-square-data (last moves)))
       (map pad-square)
       (partition 3)
       (map add-row-dividers)
       (interpose "------------")
       (join "\n")))

(defn print-board [moves]
  (println (format-board moves)))
