(ns crypto.password.test.scrypt
  (:use clojure.test)
  (:require [crypto.password.scrypt :as password]))

(deftest test-passwords
  (are [s] (password/equal? s (password/encrypt s))
    "a"
    "foo"
    "password"
    "Testing"
    "Test123"
    "ÁäñßOÔ"
    "großpösna"
    "Some rather long pass phrase perhaps out of a book or poem")
  
  (are [s r] (not (password/equal? r (password/encrypt s)))
    "a" "b"
    "a" "a "
    "aaaaa" "aaaaa\n"
    "großpösna" "grossposna"))