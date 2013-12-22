(ns sangria.guessing-game
  (:use sangria.core))

(def some-verbs ["escuchar" "hablar" "trabajar" "cantar" "bailar" "beber" "llamarse" "leer" "comer" "poner" "vivir" "sentir" "morir" "salir"])

(defn random-questions
  "Returns a lazy seq of random questions"
  []
  (lazy-seq
    (let [rand-verb (rand-nth some-verbs)
          rand-pronoun-idx (rand-int (count pronouns))
          rand-pronoun (pronouns rand-pronoun-idx)]
       (cons [[rand-verb rand-pronoun] (conjugate rand-verb rand-pronoun-idx)]
              (random-questions)))))

(defn get-question
  "Randomly returns a question."
  []
  (first (random-questions)))

(defn ask
  "Asks given question.

  Usage: (ask (get-question))"
  [[[verb pronoun] answer]]
  (println (clojure.string/capitalize verb))
  (println (str pronoun " ____ ?"))
  (let [user-answer (->
                       (read-line)
                       (clojure.string/trim)
                       (clojure.string/lower-case))]
    (if (= user-answer answer)
      (println "Bien! :)")
      (do
        (println "No! :(")
        (println "La buena respuesta es:" answer)))))

(defn ask-questions
  "Asks given questions.

  Usage: (ask-questions (take 5 (random-questions)))"
  [questions]
  (dorun (map ask questions)))
