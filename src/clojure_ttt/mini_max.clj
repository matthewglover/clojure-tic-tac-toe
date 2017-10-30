(ns clojure-ttt.mini-max
  (:require [clojure-ttt.moves :refer
              [available-moves get-next-player update-moves winner?]]))

(def draw-score 0)
(def base-winning-score 10)
(def base-losing-score -10)

(defn terminal-state? [move-states]
  (= 1 (count move-states)))

(defn move-score [maximising? depth]
  (let [base-score (if maximising? base-winning-score base-losing-score)
        depth-offset-fn (if maximising? - +)]
    (depth-offset-fn base-score depth)))

(defn better-score [maximising? [score-a :as a] [score-b :as b]]
  (let [comparer (if maximising? > <)]
    (if (comparer score-b score-a) b a)))

(defn select-highest [maximising? depth mini-max-results]
  (reduce (partial better-score maximising?) mini-max-results))

(declare mini-max)

(defn next-move-scores [max-depth maximising? depth move-states]
  (let [apply-mini-max (partial mini-max max-depth (not maximising?) (inc depth))]
    (map apply-mini-max move-states)))

(defn best-move-state [max-depth maximising? depth move-states]
  (let [winning-states (filter winner? move-states)]
    (cond
        (not-empty winning-states) [(move-score maximising? depth) (first winning-states)]
        (or (>= depth max-depth) 
            (terminal-state? move-states)) [draw-score (first move-states)]
        :else (select-highest maximising? depth (next-move-scores max-depth maximising? depth move-states)))))

(defn mini-max 
  ([max-depth moves] (mini-max max-depth true 0 moves))

  ([max-depth maximising? depth moves]
    (->> moves
        available-moves
        (map (partial update-moves moves))
        (best-move-state max-depth maximising? depth))))

(defn get-move 
  ([moves] (get-move 9 moves))
  
  ([max-depth moves]
   (let [[_ final-game-state] (mini-max max-depth moves)]
     (nth final-game-state (count moves)))))
