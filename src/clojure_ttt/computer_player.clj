(ns clojure-ttt.computer-player
  (:require [clojure-ttt.alpha-beta :as alpha-beta]))

(defn get-move [moves]
  (Thread/sleep 500)
  (alpha-beta/get-move 6 moves))
