(ns crypto.password.hmac
  "Functions for calculating HMAC using multiple crypto hash functions."
  (:refer-clojure :exclude [bytes hash])
  (:require [crypto.equality :as crypto])
  (:import javax.crypto.Mac
           javax.crypto.spec.SecretKeySpec
           org.apache.commons.codec.binary.Hex))

(def ^{:private true :doc "Standard crypto hash functions for MAC."}
  hash {:hmacmd5 "HMACMD5"
        :hmacsha1 "HMACSHA1"
        :hmacsha256 "HMACSHA256"
        :hmacsha384 "HMACSHA384"
        :hmacsha512 "HMACSHA512"})


(defn- bytes
  "Convert a String to a byte-array."
  [^String s]
  (.getBytes s "UTF-8"))


(defn hmac
  "Compute HMAC of `msg` given secret key `k`.
   Optionally pass in the algorithm to be used for computing HMAC (defaults to :hmacsha1).
   Valid options are :hmacmd5, :hmacsha1, :hmacsha256, :hmac384 & :hmacsha512."
  ([k msg]
     (hmac k msg :hmacsha1))
  ([k msg algo]
     (if-let [algo* (hash algo)]
       (let [key-spec (SecretKeySpec. (bytes k) algo*)
             mac (.doFinal (doto (Mac/getInstance algo*) (.init key-spec))
                           (bytes msg))]
         (Hex/encodeHexString mac))
       (throw (IllegalArgumentException.
               (format "Incorrect hash algorithm %s specified. Valid choices are %s."
                       algo (keys hash)))))))


(defn check
  "Check if the given HMAC is correct given the key & algorithm.
   Optionally pass in the algorithm to be used for computing HMAC (defaults to :hmacsha1).
   Valid options are :hmacmd5, :hmacsha1, :hmacsha256, :hmac384 & :hmacsha512."
  ([mac k original]
     (check mac k original :hmacsha1))
  ([mac k original algo]
     (crypto/eq? mac (hmac k original algo))))
