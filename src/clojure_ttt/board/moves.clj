(ns clojure-ttt.board.moves)

(defn- get-x-or-o [moves]
  (let [total-moves (count moves)]
    (if (odd? total-moves)
      :x
      :o)))

(defn get-last-player [moves]
  (if-not (empty? moves)
    (get-x-or-o moves)))


(defn update-moves [moves move]
  (if-not (some #(= move %) moves)
    (conj moves move)
    moves))
