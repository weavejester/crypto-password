(ns crypto.password.scrypt
  "Functions for encrypting passwords using the cutting-edge scrypt algorithm.
  See: https://www.tarsnap.com/scrypt/scrypt.pdf"
  (:import com.lambdaworks.crypto.SCryptUtil))

(defn encrypt
  "Encrypt a password string using the scrypt algorithm. This function takes
  three optional parameters:
    n - the CPU cost, must be a power of 2, defaults to 2^14
    r - the memory cost, defaults to 8
    p - the parallelization parameter, defaults to 1"
  ([raw]
     (encrypt raw 16384))
  ([raw n]
     (encrypt raw n 8 1))
  ([raw n r p]
     (SCryptUtil/scrypt raw n r p)))

(defn check
  "Compare a raw string with a string encrypted with the
  crypto.password.scrypt/encrypt function. Returns true the string match, false
  otherwise."
  [raw encrypted]
  (SCryptUtil/check raw encrypted))
