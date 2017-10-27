(ns clojure-ttt.ui.game-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.ui.game :refer :all]))

(describe "play-again?"
  (around [it] (with-out-str (it)))

  (it "returns true"
      (should (with-in-str "y\n"
                (play-again?))))

  (it "returns false"
      (should-not (with-in-str "n\n"
                (play-again?)))))
