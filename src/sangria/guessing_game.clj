(ns sangria.guessing-game
  (:use sangria.core))

(def some-verbs ["escuchar" "hablar" "trabajar" "cantar" "bailar" "beber" "llamarse" "leer" "comer" "poner" "vivir" "sentir" "morir" "salir"])

(defn get-question
  "Randomly returns a question."
  []
  (let [rand-verb (rand-nth some-verbs)
        rand-pronoun-idx (rand-int (count pronouns))
        rand-pronoun (pronouns rand-pronoun-idx)]
    [[rand-verb rand-pronoun] (conjugate rand-verb rand-pronoun-idx)]))

(defn random-questions
  "Returns a lazy seq of random questions"
  []
  (repeatedly get-question))

(defn ask
  "Asks given question. Returns true if the given answer was correct, false otherwise.

  Usage: (ask (get-question))"
  [[[verb pronoun] answer]]
  (println (clojure.string/capitalize verb))
  (println (clojure.string/capitalize pronoun) "____ ?")
  (let [user-answer (->
                       (read-line)
                       (clojure.string/trim)
                       (clojure.string/lower-case))]
    (if (= user-answer answer)
      (do
        (println "Bien! :)")
        (println)
        true)
      (do
        (println "No! :(")
        (println "La buena respuesta es:" answer)
        (println)
        false))))

(defn ask-questions
  "Asks given questions. Returns the total number of correct answers.

  Usage: (ask-questions (take 5 (random-questions)))"
  [questions]
  (->>
   (map ask questions)
   (filter identity)
   (count)))
