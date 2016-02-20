(ns crypto.password.test.pbkdf2
  (:use clojure.test)
  (:require [crypto.password.pbkdf2 :as password]))

(defn- encrypt-four-parameters [raw]
  (let [salt (byte-array (map byte [0 1 2 3 4 5 6 7]))
        algorithm "HMAC-SHA1"]
    (password/encrypt raw 100000 salt algorithm)))

(deftest test-passwords
  (are [s] (password/check s (password/encrypt s))
    "a"
    "foo"
    "password"
    "Testing"
    "Test123"
    "ÁäñßOÔ"
    "großpösna"
    "Some rather long pass phrase perhaps out of a book or poem")

  (are [s r] (not (password/check r (password/encrypt s)))
    "a" "b"
    "a" "a "
    "aaaaa" "aaaaa\n"
    "großpösna" "grossposna")

  (are [s] (password/check s (encrypt-four-parameters s))
    "foo" "foo")

  (are [s r] (not (password/check r (encrypt-four-parameters s)))
    "foo" "bar"))
