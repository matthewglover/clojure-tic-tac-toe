(ns clojure-ttt.board.data-converters)

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
  (let [board-squares (range 1 10)
        convert-square (partial convert-to-square-data moves)]
    (map convert-square board-squares)))

(defn convert-to-board-values [moves]
  (->> moves
       convert-to-board-data
       (map last)
       (into [])))

(defn convert-board-values-to-rows [board-values]
  (partition 3 board-values))

(defn convert-board-values-to-columns [board-values]
  (let [column-start-indices (range 0 3)]
    (map #(take-nth 3 (subvec board-values %)) column-start-indices)))

(defn get-board-values-at-indices [board-values indices]
  (map #(nth board-values %) indices))

(defn convert-board-values-to-diagonals [board-values]
  (let [tl-diagonal-indices (range 0 9 4)
        tr-diagonal-indices (range 2 7 2)]
    [(get-board-values-at-indices board-values tl-diagonal-indices)
     (get-board-values-at-indices board-values tr-diagonal-indices)]))

(defn convert-board-values-to-lines [board-values]
  (mapcat identity [(convert-board-values-to-rows board-values)
                    (convert-board-values-to-columns board-values)
                    (convert-board-values-to-diagonals board-values)]))

(defn get-lines-from-moves [moves]
  (->> moves
       convert-to-board-values
       convert-board-values-to-lines))
