(ns clojure-ttt.board.data-converters)

(def board-squares (range 1 10))

(defn- remove-indices-from-moves [moves-with-indices]
  (map #(last %) moves-with-indices))

(defn- add-indices-to-moves [moves]
  (map-indexed #(identity %&) moves))

(defn filter-indexed-moves [predicate indexed-moves]
  (filter #(predicate (first %)) indexed-moves))

(defn- filter-moves-by-index [predicate moves]
  (->> moves
       add-indices-to-moves
       (filter-indexed-moves predicate)
       remove-indices-from-moves))

(def get-o-moves (partial filter-moves-by-index odd?))
(def get-x-moves (partial filter-moves-by-index even?))

(defn- square-in? [square moves]
  (some #(= % square) moves))

(defn- taken-by-o? [square moves]
  (if (square-in? square (get-o-moves moves))
    :o))

(defn- taken-by-x? [square moves]
  (if (square-in? square (get-x-moves moves))
    :x))

(defn- get-value-of-square [square moves]
  (or
    (taken-by-x? square moves)
    (taken-by-o? square moves)))

(defn- convert-to-square-data [moves square]
  [square (get-value-of-square square moves)])

(defn convert-to-board-data [moves]
  (map (partial convert-to-square-data moves) board-squares))
