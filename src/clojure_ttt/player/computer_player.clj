(ns clojure-ttt.player.computer-player)

(defn- contains-move [moves move]
  (some #(= move %) moves))

(defn- available-moves [taken-moves]
  (remove #(contains-move taken-moves %) (range 1 10)))

(defn get-move [moves]
  (first (available-moves moves)))
