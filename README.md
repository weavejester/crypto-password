# crypto-password

[![Build Status](https://travis-ci.org/weavejester/crypto-password.png?branch=master)](https://travis-ci.org/weavejester/crypto-password)

A Clojure library for securing user passwords using a
[key derivation function][1]. Supports the following algorithms:

* [PBKDF2](http://en.wikipedia.org/wiki/PBKDF2)
* [Bcrypt](http://bcrypt.sourceforge.net/)
* [scrypt](http://www.tarsnap.com/scrypt.html)

[1]: http://en.wikipedia.org/wiki/Key_derivation_function

## Installation

Add the following dependency to your `project.clj` file:

    [crypto-password "0.1.2"]

## Usage

Pick an encryption algorithm, either `pbkdf2`, `bcrypt` or `scrypt`:

```clojure
(require '[crypto.password.<algorithm> :as password])
```

Then use the `encrypt` function to apply a secure, one-way encryption
algorithm to a password:

```clojure
(def encrypted (password/encrypt "foobar"))
```

And the `check` function to check the encrypted password against a
plaintext password:

```clojure
(password/check "foobar" encrypted) ;; => true
```

## Documentation

* [API Docs](http://weavejester.github.com/crypto-password/)

## License

Copyright © 2014 James Reeves

Distributed under the Eclipse Public License, the same as Clojure.
