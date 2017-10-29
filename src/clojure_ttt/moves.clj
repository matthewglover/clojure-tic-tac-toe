(ns clojure-ttt.moves)

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
