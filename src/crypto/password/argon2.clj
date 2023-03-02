(ns crypto.password.argon2
 "Functions for encrypting passwords using the recommended argon2 algorithm.

  See: https://infosecscout.com/best-algorithm-password-storage
  https://github.com/phxql/argon2-jvm"
 (:import [de.mkammerer.argon2 Argon2 Argon2Factory Argon2Advanced]))

(def argon2 (Argon2Factory/create))

(def ^:private default-iterations
  (Long/parseLong (System/getProperty "crypto.password.argon2.default-iterations" "22")))

(def ^:private default-memory-cost
  (Long/parseLong (System/getProperty "crypto.password.argon2.default-memory-cost" "65536")))

(def ^:private default-parallelization-parameter
  (Long/parseLong (System/getProperty "crypto.password.argon2.default-parallelization-parameter" "1")))

(defn encrypt
  "Encrypt a password string using the argon2 algorithm. This function takes
  three optional parameters:

  * `iter`     - the number of iterations, defaults to 22
  * `mem`      - the memory cost, defaults to 65536
  * `parallel` - the parallelization parameter, defaults to 1"
  ([raw]
   (encrypt raw
            default-iterations
            default-memory-cost
            default-parallelization-parameter))
  ([raw iter mem parallel]
   (.hash argon2 iter mem parallel raw)))

(defn check
  "Compare a raw string with a string encrypted with the [[encrypt]]
  function. Returns true if the string matches, false otherwise."
  [raw hash]
  (.verify argon2 hash raw))
