(ns clojure-ttt.ui.colours)

(def blue "\u001B[34m")
(def green "\u001B[32m")
(def yellow "\u001B[33m")
(def reset "\u001B[0m")

(defn colourize [colour content]
  (str colour content reset))
