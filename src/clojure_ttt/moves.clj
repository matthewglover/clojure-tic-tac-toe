(ns clojure-ttt.moves
  (:require [clojure-ttt.lines :refer [convert-moves-to-lines]]
            [clojure-ttt.squares :refer [square-numbers]]))

(defn get-last-player [moves]
  (and (not-empty moves)
       (if (odd? (count moves)) :x :o)))

(defn get-next-player [moves]
  (if (even? (count moves)) :x :o))

(defn move-made? [moves move]
  (some #(= move %) moves))

(defn update-moves [moves move]
  (if-not (move-made? moves move)
    (conj moves move)
    moves))

(defn winner? [moves]
  (->> moves
       convert-moves-to-lines
       (remove #(every? nil? %))
       (filter (partial apply =))
       ((comp first first))))

(defn board-full? [moves]
  (= 9 (count moves)))

(defn game-over? [moves]
  (or (winner? moves)
      (board-full? moves)))

(defn available-moves [made-moves]
  (remove (partial move-made? made-moves) square-numbers))
