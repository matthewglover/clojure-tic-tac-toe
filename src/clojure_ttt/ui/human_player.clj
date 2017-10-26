(ns clojure-ttt.ui.human-player)

(def request-move-message "Enter your move: ")
(def invalid-move-message "Oops! Invalid move, try again: ")

(defn- get-user-input [input-validator]
  (let [input (read-line)]
    (if (input-validator input)
      (Integer/parseInt input))))

(defn- print-invalid-move-message []
  (print invalid-move-message)
  (flush))

(defn- prompt-for-move [input-validator]
  (let [user-input (get-user-input input-validator)]
    (or user-input
        (do (print-invalid-move-message)
            (prompt-for-move input-validator)))))

(defn- is-free-square [moves square]
  (not (some #(= square %) moves)))
  
(defn- is-parsable-integer [input]
  (re-matches #"^\d$" input))

(defn- input-validator [moves input]
  (and (is-parsable-integer input) 
       (is-free-square moves (Integer/parseInt input))))

(defn- print-move-request []
  (print request-move-message)
  (flush))

(defn get-move [moves]
  (print-move-request)
  (prompt-for-move (partial input-validator moves)))
