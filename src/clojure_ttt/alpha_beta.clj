(ns clojure-ttt.alpha-beta
  (:require [clojure-ttt.moves :refer [available-moves winner? game-over? update-moves]]))

(def base-winning-score 10)
(def base-losing-score -10)
(def draw-score 0)
(def default-max-depth 9)
(def default-alpha -1000)
(def default-beta 1000)

(declare alpha-beta)

(defmulti result-score (fn [maximizing? _] (if maximizing? :maximizing :minimizing)))
(defmethod result-score :maximizing [_ depth] (- base-winning-score depth))
(defmethod result-score :minimizing [_ depth] (+ base-losing-score depth))

(defmulti move-score (fn [max-depth _ depth _ _ moves]
  (cond
    (winner? moves) :winner
    (game-over? moves) :draw
    (>= depth max-depth) :draw
    :else :incomplete)))

(defmethod move-score :winner [_ maximizing? depth _ _ moves] (result-score maximizing? depth))
(defmethod move-score :draw [& _] draw-score)
(defmethod move-score :incomplete [max-depth maximizing? depth alpha beta moves] 
  (let [next-maximizing? (not maximizing?)
        [new-alpha new-beta _ :as res] (alpha-beta max-depth next-maximizing? (inc depth) alpha beta moves)]
    (if next-maximizing? new-alpha new-beta)))


(defmulti update-alpha-beta (fn [maximizing? & _] (if maximizing? :alpha :beta)))
(defmethod update-alpha-beta :alpha [_ score alpha beta] [(if (> score alpha) score alpha) beta])
(defmethod update-alpha-beta :beta [_ score alpha beta] [alpha (if (< score beta) score beta)])


(defmulti better-move? (fn [maximizing? & _] (if maximizing? :maximizing :minimizing)))
(defmethod better-move? :maximizing [_ alpha _ new-score] (> new-score alpha))
(defmethod better-move? :minimizing [_ _ beta new-score] (< new-score beta))


(defn choose-better-move [max-depth maximizing? depth [acc-alpha acc-beta acc-moves :as acc] next-moves]
  (if (<= acc-beta acc-alpha)
      acc
      (let [new-score (move-score max-depth maximizing? depth acc-alpha acc-beta next-moves)
            [new-alpha new-beta] (update-alpha-beta maximizing? new-score acc-alpha acc-beta)]
        (cond
          (nil? acc-moves) [new-alpha new-beta next-moves]
          (better-move? maximizing? acc-alpha acc-beta new-score) [new-alpha new-beta next-moves]
          :else acc))))

(defn alpha-beta
  ([max-depth moves] (alpha-beta max-depth true 0 default-alpha default-beta moves))
  
  ([max-depth maximizing? depth alpha beta moves]
    (let [update-moves (partial update-moves moves)
          seed [alpha beta nil]
          choose-better-move (partial choose-better-move max-depth maximizing? depth)]
    (->> moves
         available-moves
         (map update-moves)
         (reduce choose-better-move seed)))))

(defn get-move
  ([moves] (get-move default-max-depth moves))
  
  ([max-depth moves]
    (let [final-game-state (last (alpha-beta max-depth moves))]
      (nth final-game-state (count moves)))))

