(ns clojure-ttt.computer-player
  (:require [clojure-ttt.mini-max :as mini-max]))

(defn get-move [moves]
  (Thread/sleep 500)
  (mini-max/get-move 6 moves))
