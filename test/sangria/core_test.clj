(ns sangria.core-test
  (:use clojure.test
        sangria.core))

(deftest group-test
  (testing "groups"
    (is (= (find-group "hablar") 0))
    (is (= (find-group "beber") 1))
    (is (= (find-group "vivir") 2)
    (is (= (find-group "llamarse") 0)))))

(deftest root-test
  (testing "root"
    (is (= (find-root "hablar") "habl"))
    (is (= (find-root "beber") "beb"))
    (is (= (find-root "vivir") "viv"))))

(deftest conjugate-test
  (testing "conjugation"
    (is (= (conjugate "hablar" 0) "hablo"))
    (is (= (conjugate "beber" 3) "bebemos"))
    (is (= (conjugate "vivir" 4) "vivís"))
    (testing "composite verbs"
      (is (= (conjugate "llamarse" 4) "os llamáis")))
    (testing "irregular verbs"
      (is (= (conjugate "ser" 1) "eres"))
      (is (= (conjugate "tener" 0) "tengo"))
      (is (= (conjugate "estar" 2) "está")))))
