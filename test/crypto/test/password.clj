(ns crypto.test.password
  (:use clojure.test)
  (:require [crypto.password :as password]))

(deftest test-passwords
  (are [s] (password/equal? s (password/encrypt s))
    "a"
    "foo"
    "password"
    "Testing"
    "Test123"
    "ÁäñßOÔ"
    "großpösna"
    "Some rather long pass phrase perhaps out of a book or poem"))
