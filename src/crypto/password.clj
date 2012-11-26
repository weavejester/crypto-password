(ns crypto.password
  "Functions for encrypting passwords using the PBKDF2 algorithm, as recommended
  by the NIST:
  http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf"
  (:require [crypto.random :as random]
            [clojure.string :as str])
  (:import javax.crypto.SecretKeyFactory
           javax.crypto.spec.PBEKeySpec
           org.apache.commons.codec.binary.Base64))

(defn- encode-str [bytes]
  (String. (Base64/encodeBase64 bytes)))

(defn- encode-int [i]
  (String. (Base64/encodeInteger (BigInteger/valueOf i))))

(defn encrypt
  "Encrypt a password string using the PBKDF2 algorithm. The default number of
  iterations is 20,000. If a salt is not specified, 8 random bytes are
  generated from a cryptographically secure source.

  The number of iterations and salt are encoded in the output in the following
  format:
    <iterations>$<salt>$<encrypted password>

  All elements in the output string are Base64 encoded."
  ([raw]
     (encrypt raw 20000))
  ([raw iterations]
     (encrypt raw iterations (random/bytes 8)))
  ([raw iterations salt]
     (let [key-length  160
           key-spec    (PBEKeySpec. (.toCharArray raw) salt iterations key-length)
           key-factory (SecretKeyFactory/getInstance "PBKDF2WithHmacSHA1")]
       (->> (.generateSecret key-factory key-spec)
            (.getEncoded)
            (encode-str)
            (str (encode-str salt) "$")
            (str (encode-int iterations) "$")))))

(defn- decode-str [s]
  (Base64/decodeBase64 (.getBytes s)))

(defn- decode-int [s]
  (int (Base64/decodeInteger (.getBytes s))))

(defn- constant-time-eq? [^String a ^String b]
  (if (and a b (= (.length a) (.length b)))
    (zero? (reduce bit-or (map bit-xor (.getBytes a) (.getBytes b))))
    false))

(defn equal?
  "Compare a raw string with a string encrypted with the crypto.password/encrypt
  function. Returns true the string match, false otherwise."
  [raw encrypted]
  (let [[i s _]    (str/split encrypted #"\$")
        salt       (decode-str s)
        iterations (decode-int i)]
    (constant-time-eq? encrypted (encrypt raw iterations salt))))
