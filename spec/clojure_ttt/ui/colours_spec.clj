(ns clojure-ttt.ui.colours-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.ui.colours :refer :all]))

(describe "colourize"
  (it "colours text blue"
    (should= (str "\u001B[34m" "Hello World" "\u001B[0m")
             (colourize blue "Hello World")))
  
  (it "colours text green"
    (should= (str "\u001B[32m" "Hello World" "\u001B[0m")
             (colourize green "Hello World")))
  
  (it "colours text yellow"
    (should= (str "\u001B[33m" "Hello World" "\u001B[0m")
             (colourize yellow "Hello World"))))
