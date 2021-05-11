(ns crypto.password.bcrypt
  "Functions for encrypting passwords using the widely-used bcrypt algorithm.

  See: https://github.com/patrickfav/bcrypt"
  (:import [at.favre.lib.crypto.bcrypt BCrypt]))

(def ^:private default-work-factor
  (Long/parseLong (System/getProperty "crypto.password.bcrypt.default-work-factor" "11")))

(defn encrypt
  "Encrypt a password string using the BCrypt algorithm. The optional work
  factor is the log2 of the number of hashing rounds to apply. The default
  work factor is 11."
  ([raw]
   (encrypt raw default-work-factor))
  ([raw work-factor]
   (.hashToString (BCrypt/withDefaults) work-factor (.toCharArray raw))))

(defn check
  "Compare a raw string with a string encrypted with the [[encrypt]] function.
  Returns true if the string matches, false otherwise."
  [raw encrypted]
  (.verified (.verify (BCrypt/verifyer) (.toCharArray raw) encrypted)))
