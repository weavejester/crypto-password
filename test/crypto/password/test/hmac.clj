(ns crypto.password.test.hmac
  (:use clojure.test)
  (:require [crypto.password.hmac :as mac]))

(deftest test-hmac
  (are [algo result] (= (mac/hmac "key" "The quick brown fox jumps over the lazy dog" algo) result)
       :hmacmd5 "80070713463e7749b90c2dc24911e275"
       :hmacsha1 "de7c9b85b8b78aa6bc8a7a36f70a90701c9db4d9"
       :hmacsha256 "f7bc83f430538424b13298e6aa6fb143ef4d59a14946175997479dbc2d1a3cd8"
       :hmacsha384 "d7f4727e2c0b39ae0f1e40cc96f60242d5b7801841cea6fc592c5d3e1ae50700582a96cf35e1e554995fe4e03381c237"
       :hmacsha512 "b42af09057bac1e2d41708e48a902e09b5ff7f12ab428a4fe86653c73dd248fb82f948a549f7b791a5b41915ee4d1ec3935357e4e2317250d0372afa2ebeeb3a")

  (are [s] (mac/check (mac/hmac "foobar" s) "foobar" s)
    "a"
    "foo"
    "password"
    "Testing"
    "Test123"
    "ÁäñßOÔ"
    "großpösna"
    "Some rather long pass phrase perhaps out of a book or poem")

  (are [s r] (not (mac/check (mac/hmac "foobar" s) "foobar" r))
    "a" "b"
    "a" "a "
    "aaaaa" "aaaaa\n"
    "großpösna" "grossposna"))
