(ns clojure-ttt.computer-player
  (:require [clojure-ttt.moves :refer [move-made?]]
            [clojure-ttt.squares :refer [square-numbers]]))

(defn- available-moves [made-moves]
  (remove (partial move-made? made-moves) square-numbers))

(defn get-move-directly [moves]
  (first (available-moves moves)))

(defn get-move [moves]
  (Thread/sleep 500)
  (get-move-directly moves))
