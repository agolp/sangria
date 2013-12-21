(ns sangria.core
  (:gen-class))

(def pronouns
  ["yo" "tú" "él" "nosotros" "vosotros" "ellos"])

(def endings
  [["o" "as" "a" "amos" "áis" "an"]
   ["o" "es" "e" "emos" "éis" "en"]
   ["o" "es" "e" "imos"  "ís" "en"]])

(def composite-pronouns
  ["me" "te" "se" "nos" "os" "se"])

(defn composite? [verb]
  (.endsWith verb "se"))

(defn decompose [verb]
  (clojure.string/join (drop-last 2 verb)))

(defn find-group [verb]
  (let [ending (clojure.string/join (take-last 2 verb))]
    (case ending
      "ar" 0
      "er" 1
      "ir" 2
      "se" (recur (decompose verb)))))

(defn find-root [verb]
  (clojure.string/join (drop-last 2 verb)))

(defn conjugate [verb pronoun-idx]
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
        (get-in endings [group pronoun-idx])))))
