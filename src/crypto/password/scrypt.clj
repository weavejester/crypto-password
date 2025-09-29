(ns crypto.password.scrypt
  "Functions for encrypting passwords using the cutting-edge scrypt algorithm.

  See: https://www.tarsnap.com/scrypt/scrypt.pdf"
  (:import com.lambdaworks.crypto.SCryptUtil))

(def ^:private default-cpu-cost
  (Long/parseLong
   (System/getProperty "crypto.password.scrypt.default-cpu-cost" "32768")))

(def ^:private default-memory-cost
  (Long/parseLong
   (System/getProperty "crypto.password.scrypt.default-memory-cost" "8")))

(def ^:private default-parallelization-parameter
  (Long/parseLong
   (System/getProperty
    "crypto.password.scrypt.default-parallelization-parameter" "1")))

(defn encrypt
  "Encrypt a password string using the scrypt algorithm. This function takes
  three optional parameters:

  * `n` - the CPU cost, must be a power of 2, defaults to 2^15
  * `r` - the memory cost, defaults to 8
  * `p` - the parallelization parameter, defaults to 1"
  ([raw]
   (encrypt raw default-cpu-cost))
  ([raw n]
   (encrypt raw n default-memory-cost default-parallelization-parameter))
  ([raw n r p]
   (SCryptUtil/scrypt raw n r p)))

(defn check
  "Compare a raw string with a string encrypted with the [[encrypt]] function.
  Returns true if the string matches, false otherwise."
  [raw encrypted]
  (SCryptUtil/check raw encrypted))
