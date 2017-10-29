(ns clojure-ttt.game.status
  (:require [clojure-ttt.lines :refer [get-lines-from-moves]]))

(defn get-first-winning-line [non-empty-lines]
  (first (filter #(apply = %) non-empty-lines)))

(defn remove-empty-lines [lines]
  (remove #(every? nil? %) lines))

(defn winner? [moves]
  (->> moves
       get-lines-from-moves
       remove-empty-lines
       get-first-winning-line
       first))

(defn board-full? [moves]
  (= 9 (count moves)))

(defn over? [moves]
  (or (winner? moves)
      (board-full? moves)))
