(ns crypto.password.argon2
  "Functions for encrypting passwords using the recommended argon2 algorithm.

  See: https://www.argon2.com/"
  (:import [de.mkammerer.argon2 Argon2 Argon2Factory]))

(def ^:private argon2
  (delay (Argon2Factory/create)))

(def ^:private default-iterations
  (Long/parseLong
   (System/getProperty "crypto.password.argon2.default-iterations" "10")))

(def ^:private default-memory-cost
  (Long/parseLong
   (System/getProperty "crypto.password.argon2.default-memory-cost" "65536")))

(def ^:private default-parallelization-parameter
  (Long/parseLong
   (System/getProperty
    "crypto.password.argon2.default-parallelization-parameter" "1")))

(defn encrypt
  "Encrypt a password string using the argon2 algorithm. This function takes
  three optional parameters:

  * `t` - the number of iterations, defaults to 10
  * `m` - the memory cost, defaults to 65536
  * `p` - the parallelization parameter, defaults to 1"
  ([raw]
   (encrypt raw default-iterations))
  ([raw t]
   (encrypt raw t default-memory-cost))
  ([raw t m]
   (encrypt raw t m default-parallelization-parameter))
  ([raw t m p]
   (.hash ^Argon2 @argon2 t m p raw)))

(defn check
  "Compare a raw string with a string encrypted with the [[encrypt]]
  function. Returns true if the string matches, false otherwise."
  [raw hash]
  (.verify ^Argon2 @argon2 hash raw))
