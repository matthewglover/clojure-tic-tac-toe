(ns clojure-ttt.lines
  (:require [clojure-ttt.squares :refer [convert-moves-to-squares]]))

(defn convert-squares-to-rows [squares]
  (partition 3 squares))

(defn convert-squares-to-columns [squares]
  (let [col-start-positions (range 0 3)
        get-squares-from-col-start-position #(drop % squares)
        take-every-3rd-square-from-start-position #(take-nth 3 (get-squares-from-col-start-position %))]
    (map take-every-3rd-square-from-start-position col-start-positions)))

(defn get-board-values-at-positions [squares positions]
  (map #(nth squares %) positions))

(defn convert-squares-to-diagonals [squares]
  (let [tl-diagonal-positions (range 0 9 4)
        tr-diagonal-positions (range 2 7 2)]
    [(get-board-values-at-positions squares tl-diagonal-positions)
     (get-board-values-at-positions squares tr-diagonal-positions)]))

(defn convert-squares-to-lines [squares]
  (mapcat identity [(convert-squares-to-rows squares)
                    (convert-squares-to-columns squares)
                    (convert-squares-to-diagonals squares)]))

(defn get-lines-from-moves [moves]
  (->> moves
       convert-moves-to-squares
       convert-squares-to-lines))

