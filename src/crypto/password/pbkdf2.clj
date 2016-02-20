(ns crypto.password.pbkdf2
  "Functions for encrypting passwords using the PBKDF2 algorithm, as recommended
  by the NIST:
  http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf"
  (:require [crypto.random :as random]
            [crypto.equality :as crypto]
            [clojure.string :as str])
  (:import javax.crypto.SecretKeyFactory
           javax.crypto.spec.PBEKeySpec
           org.apache.commons.codec.binary.Base64))

(def algorithm-codes
  {"HMAC-SHA1"   "PBKDF2WithHmacSHA1"
   "HMAC-SHA256" "PBKDF2WithHmacSHA256"})

(defn- encode-str [bytes]
  (String. (Base64/encodeBase64 bytes)))

(defn- encode-int [i]
  (String. (Base64/encodeInteger (BigInteger/valueOf i))))

(defn encrypt
  "Encrypt a password string using the PBKDF2 algorithm. The default number of
  iterations is 100,000. If a salt is not specified, 8 random bytes are
  generated from a cryptographically secure source.  The default algorithm is
  \"HMAC-SHA1\". When running on JDK 1.8 \"HMAC-SHA256\" is also supported.

  The number of iterations and salt are encoded in the output in the following
  formats for HMAC-SHA1:

    <iterations>$<salt>$<encrypted password>

  And for all other algoritms:

    <iterations>$<algorithm>$<salt>$<encrypted password>

  The iterations, salt, and encrypted password are all Base64 encoded."
  ([raw]
     (encrypt raw 100000))
  ([raw iterations]
     (encrypt raw iterations "HMAC-SHA1"))
  ([raw iterations algorithm]
     (encrypt raw iterations algorithm (random/bytes 8)))
  ([raw iterations algorithm salt]
     {:pre [(contains? algorithm-codes algorithm)]}
     (let [key-length  160
           key-spec    (PBEKeySpec. (.toCharArray raw) salt iterations key-length)
           key-factory (SecretKeyFactory/getInstance (get algorithm-codes algorithm))]
       (->> (.generateSecret key-factory key-spec)
            (.getEncoded)
            (encode-str)
            (str (encode-str salt) "$")
            (str (if (= algorithm "HMAC-SHA1") "" (str algorithm "$")))
            (str (encode-int iterations) "$")))))

(defn- decode-str [s]
  (Base64/decodeBase64 (.getBytes s)))

(defn- decode-int [s]
  (int (Base64/decodeInteger (.getBytes s))))

(defn check
  "Compare a raw string with a string encrypted with the
  crypto.password.pbkdf2/encrypt function. Returns true if the string matches,
  false otherwise."
  [raw encrypted]
  (let [parts         (str/split encrypted #"\$")
        iterations    (decode-int (parts 0))
        algorithm     (if (= (count parts) 4) (parts 1) "HMAC-SHA1")
        salt          (decode-str (if (= algorithm "HMAC-SHA1") (parts 1) (parts 2)))
        raw-encrypted (encrypt raw iterations algorithm salt)]
    (crypto/eq? raw-encrypted encrypted)))
