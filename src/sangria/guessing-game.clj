(ns sangria.guessing-game
  (:use sangria.core))

(def some-verbs ["escuchar" "hablar" "trabajar" "cantar" "bailar" "beber" "llamarse" "leer" "comer" "poner" "vivir" "sentir" "morir" "salir"])

(defn get-question []
  (let [rand-verb (rand-nth some-verbs)
        rand-pronoun-idx (rand-int (count pronouns))
        rand-pronoun (pronouns rand-pronoun-idx)]
    [[rand-verb rand-pronoun] (conjugate rand-verb rand-pronoun-idx)]))

(defn ask [[[verb pronoun] answer]]
  (do
    (println (clojure.string/capitalize verb))
    (println (str pronoun " _ ?"))
    (let [user-answer (clojure.string/trim (read-line))]
      (if (= user-answer answer)
        (println "Bien! :)")
        (println "No! :(")))))
