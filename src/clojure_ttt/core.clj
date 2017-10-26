(ns clojure-ttt.core
  (:require [clojure-ttt.game.runner :refer [run-game]]))

(defn -main [& args]
  (run-game))

