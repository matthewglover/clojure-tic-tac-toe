(ns clojure-ttt.computer-player)

(defn- contains-move [moves move]
  (some #(= move %) moves))

(defn- available-moves [taken-moves]
  (remove #(contains-move taken-moves %) (range 1 10)))

(defn get-move-directly [moves]
  (first (available-moves moves)))

(defn get-move [moves]
  (Thread/sleep 500)
  (get-move-directly moves))
