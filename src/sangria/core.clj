(ns sangria.core
  (:use clojure.pprint))

(def pronouns
  ["yo" "tú" "él" "nosotros" "vosotros" "ellos"])

(def endings
  [["o" "as" "a" "amos" "áis" "an"]
   ["o" "es" "e" "emos" "éis" "en"]
   ["o" "es" "e" "imos"  "ís" "en"]])

(def irregular-verbs
  {"ser"   ["soy", "eres", "es", "somos", "sois", "son"],
   "tener" ["tengo", "tienes", "tiene", "tenemos", "tenéis", "tienen"],
   "estar" ["estoy", "estas", "está", "estamos", "estáis", "estan"]})

(def composite-pronouns
  ["me" "te" "se" "nos" "os" "se"])

(defn composite? [verb]
  (.endsWith verb "se"))

(defn decompose [verb]
  (let [cut (- (.length verb) 2)]
    (.substring verb 0 cut)))

(defn find-group [verb]
  (let [start (- (.length verb) 2)
        ending (.substring verb start)]
    (case ending
      "ar" 0
      "er" 1
      "ir" 2
      "se" (recur (decompose verb)))))

(defn find-root [verb]
  (let [cut (- (.length verb) 2)]
    (.substring verb 0 cut)))

(defn conjugate [verb pronoun-idx]
  (if-let [irregular-verb (get irregular-verbs verb)]
    (get irregular-verb pronoun-idx)
    (if (composite? verb)
    (let [verb (decompose verb)
          root (find-root verb)
          group (find-group verb)]
      (str
        (composite-pronouns pronoun-idx)
        " "
        root
        (get-in endings [group pronoun-idx])))
    (let [root (find-root verb)
          group (find-group verb)]
      (str
        root
        (get-in endings [group pronoun-idx]))))))
