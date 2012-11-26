(ns crypto.password
  (:require [crypto.random :as random])
  (:import javax.crypto.SecretKeyFactory
           javax.crypto.spec.PBEKeySpec
           org.apache.commons.codec.binary.Base64))

(defn encode-str [bytes]
  (String. (Base64/encodeBase64 bytes)))

(defn encode-int [i]
  (String. (Base64/encodeInteger (BigInteger/valueOf i))))

(defn encrypt [s]
  (let [salt        (random/bytes 8)
        key-length  160
        iterations  20000
        key-spec    (PBEKeySpec. (.toCharArray s) salt iterations key-length)
        key-factory (SecretKeyFactory/getInstance "PBKDF2WithHmacSHA1")]
    (->> (.generateSecret key-factory key-spec)
         (.getEncoded)
         (base64)
         (str (encode-str salt) "$")
         (str (encode-int iterations) "$"))))


