(ns sangria.core
  (:gen-class))

(def endings
  [["o", "as", "a", "amos", "áis", "an"]
   ["o", "es", "e", "emos", "éis", "en"]
   ["o", "es", "e", "imos", "ís", "en"]])

(def pronouns
  ["me", "te", "se", "nos", "os", "se"])

(defn find-group [verb]
  (let [ending (clojure.string/join (take-last 2 verb))]
    (case ending
      "ar" 0
      "er" 1
      "ir" 2)))

(defn find-root [verb]
  (clojure.string/join (drop-last 2 verb)))

(defn conjugate [verb pronoun]
  (let [composite? (= clojure.string/join (take-last 2 verb) "se")
        root (find-root verb)
        group (find-group verb)]
    (str
      root
      (get-in endings [group pronoun]))))
