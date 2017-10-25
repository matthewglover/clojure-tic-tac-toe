(ns clojure-ttt.game.status
  (:require [clojure-ttt.board.data-converters :as data-converters]))

(defn get-first-winning-line [non-empty-lines]
  (first (filter #(apply = %) non-empty-lines)))

(defn remove-empty-lines [lines]
  (remove #(every? nil? %) lines))

(defn winner? [moves]
  (->> moves
       data-converters/get-lines-from-moves
       remove-empty-lines
       get-first-winning-line
       first))

(defn board-full? [moves]
  (= 9 (count moves)))

(defn over? [moves]
  (or (winner? moves)
      (board-full? moves)))
