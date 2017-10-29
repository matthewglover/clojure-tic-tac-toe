(ns clojure-ttt.squares)

(defn- filter-moves-by-index [predicate moves]
  (->> moves
       (map-indexed vector)
       (filter #(predicate (first %)))
       (map last)))

(defn move-filter [player]
  (if (= :x player) even? odd?))

(defn select-player-moves [player moves]
  (filter-moves-by-index (move-filter player) moves))

(defn square-taken-by? [moves square player]
  (let [player-moves (select-player-moves player moves)
        matches-square #(= % square)]
    (some matches-square player-moves)))

(defn convert-square-number-to-value [moves square-number]
  (let [taken-by? (partial square-taken-by? moves square-number)]
    (cond
      (taken-by? :x) :x
      (taken-by? :o) :o)))

(defn convert-moves-to-squares [moves]
  (let [square-numbers (range 1 10)
        convert-to-value (partial convert-square-number-to-value moves)]
    (map convert-to-value square-numbers)))
