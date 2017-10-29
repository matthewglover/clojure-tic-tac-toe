(ns clojure-ttt.lines
  (:require [clojure-ttt.board.data-converters :refer [convert-to-board-values]]))

(defn convert-board-values-to-rows [board-values]
  (partition 3 board-values))

(defn convert-board-values-to-columns [board-values]
  (let [col-start-values (range 0 3)
        get-board-from-col-start-value (partial subvec board-values)
        take-every-3rd-square-from-start-value #(take-nth 3 (get-board-from-col-start-value %))]
    (map take-every-3rd-square-from-start-value col-start-values)))

(defn get-board-values-at-indices [board-values indices]
  (map #(nth board-values %) indices))

(defn convert-board-values-to-diagonals [board-values]
  (let [tl-diagonal-indices (range 0 9 4)
        tr-diagonal-indices (range 2 7 2)]
    [(get-board-values-at-indices board-values tl-diagonal-indices)
     (get-board-values-at-indices board-values tr-diagonal-indices)]))

(defn convert-board-values-to-lines [board-values]
  (mapcat identity [(convert-board-values-to-rows board-values)
                    (convert-board-values-to-columns board-values)
                    (convert-board-values-to-diagonals board-values)]))

(defn get-lines-from-moves [moves]
  (->> moves
       convert-to-board-values
       convert-board-values-to-lines))

