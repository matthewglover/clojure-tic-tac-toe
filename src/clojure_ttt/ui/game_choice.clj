(ns clojure-ttt.ui.game-choice
  (:require [clojure.string :refer [capitalize]]
            [clojure-ttt.ui.helpers :refer [clear-screen]]))

(def game-types [{:x :human :o :human}
                 {:x :human :o :computer}
                 {:x :computer :o :human}
                 {:x :computer :o :computer}])

(def invalid-choice-message "Oops - invalid game choice! Try again:")

(defn print-invalid-choice-message []
  (print invalid-choice-message)
  (flush))

(defn get-game [selection]
  (->> selection
       Integer/parseInt
       dec
       (nth game-types)))

(defn choose-game []
  (let [input (read-line)]
    (if (re-matches #"^[1-4]$" input)
      (get-game input))))

(def format-player (comp capitalize name))

(defn format-game-type [game-number game-type]
  (let [player-x (:x game-type)
        player-o (:o game-type)]
    (str "(" game-number ") " (format-player player-x) " vs " (format-player player-o))))

(defn print-game-choices []
  (println "Choose game type:")
  (doseq [[game-number game-type] (map-indexed #(vector (inc %1) %2) game-types)]
          (println (format-game-type game-number game-type))))

(defn get-game-choice []
  (clear-screen)
  (print-game-choices)
  (loop [game-choice (choose-game)]
    (if game-choice
      game-choice
      (do (print-invalid-choice-message)
          (recur (choose-game))))))
