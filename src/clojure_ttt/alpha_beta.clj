(ns clojure-ttt.alpha-beta
  (:require [clojure-ttt.moves :refer
              [available-moves update-moves winner?]]))

(def draw-score 0)
(def base-winning-score 10)
(def base-losing-score -10)
(def default-max-depth 9)
(def default-alpha -1000)
(def default-beta 1000)

(defn result-score [maximising? depth]
  (let [base-score (if maximising? base-winning-score base-losing-score)
        depth-offset-fn (if maximising? - +)]
    (depth-offset-fn base-score depth)))

(defn [move-score max-depth maximising? depth moves]
  (cond
    (winner? moves) (result-score maximising? depth)
    (game-over? moves)))

(defn best-move [max-depth maximising? depth [alpha beta best-score best-moves :as acc] next-moves]
  (if (<= beta alpha)
    acc
    (let [new-score (move-score max-depth maximising? depth next-moves)]
      (if (better-score maximising? new-score best-score)
        (concat (update-alpha-beta maximising? alpha beta new-score) [new-score next-moves])
        accumulator))))

(defn alpha-beta
  ([max-depth moves] (alpha-beta max-depth true 0 moves))
  
  ([max-depth maximising? depth moves]
   (->> moves
        available-moves
        (map (partial update-moves moves))
        (reduce (partial best-move max-depth maximising? depth) [default-alpha default-beta]))))

(defn get-move 
  ([moves] (get-move default-max-depth moves))
  
  ([max-depth moves]
    (let [final-game-state (last (alpha-beta max-depth moves))]
     (nth final-game-state (count moves)))))
