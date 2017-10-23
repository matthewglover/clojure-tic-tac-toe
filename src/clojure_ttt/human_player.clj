(ns clojure-ttt.human-player)

(def str-move-request "Enter your move: ")

(defn- print-move-request
  []
  (print str-move-request)
  (flush))

(defn- get-user-input
  [input-validator]
  (let [input (read-line)]
    (if (input-validator input)
      (Integer/parseInt input))))

(defn prompt-for-move
  [input-validator]
  (print-move-request)
  (get-user-input input-validator))
