(ns clojure-ttt.ui.game-choice
  (:require [clojure.string :refer [capitalize]]
            [clojure-ttt.ui.helpers :refer [clear-screen]]))

(def invalid-choice-message "Oops - invalid game choice! Try again:")

(defn print-invalid-choice-message []
  (print invalid-choice-message)
  (flush))

(defn get-game [game-types selection]
  (->> selection
       Integer/parseInt
       dec
       (nth game-types)))

(defn choose-game [game-types]
  (let [input (read-line)]
    (if (re-matches #"^[1-4]$" input)
      (get-game game-types input))))

(def format-player (comp capitalize name))

(defn format-game-type [game-number game-type]
  (let [player-x (:x game-type)
        player-o (:o game-type)]
    (str "(" game-number ") " (format-player player-x) " vs " (format-player player-o))))

(defn print-game-choices [game-types]
  (println "Choose game type:")
  (doseq [[game-number game-type] (map-indexed #(vector (inc %1) %2) game-types)]
          (println (format-game-type game-number game-type))))

(defn prompt-for-game-choice [game-types]
  (clear-screen)
  (print-game-choices game-types)
  (loop [game-choice (choose-game game-types)]
    (if game-choice
      game-choice
      (do (print-invalid-choice-message)
          (recur (choose-game game-types))))))

