(ns clojure-ttt.ui.human-player)

(def str-move-request "Enter your move: ")

(defn- is-free-square [moves square]
  (not (some #(= square %) moves)))
  
(defn- is-parsable-integer [input]
  (re-matches #"^\d$" input))

(defn input-validator [moves input]
  (and 
    (is-parsable-integer input)
    (is-free-square moves (Integer/parseInt input))))

(defn- get-user-input [input-validator]
  (let [input (read-line)]
    (if (input-validator input)
      (Integer/parseInt input))))

(defn- print-move-request []
  (print str-move-request)
  (flush))

(defn prompt-for-move [input-validator]
  (print-move-request)
  (get-user-input input-validator))

(defn get-human-move [moves]
  (prompt-for-move (partial input-validator moves)))
