(ns clojure-ttt.core
  (:require [clojure-ttt.game.runner :refer [run-app]]))

(defn -main [& args]
  (run-app))

