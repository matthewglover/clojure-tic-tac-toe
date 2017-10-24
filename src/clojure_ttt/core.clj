(ns clojure-ttt.core
  (:require [clojure-ttt.ui.colours :as colours]))


(defn -main
  [& args]
  (println (colours/colourize colours/blue "Hello World")))

