# crypto-password [![Build Status](https://github.com/weavejester/crypto-password/actions/workflows/test.yml/badge.svg)](https://github.com/weavejester/crypto-password/actions/workflows/test.yml)

A Clojure library for securing user passwords using a
[key derivation function][1]. Supports the following algorithms:

* [Argon2](https://github.com/P-H-C/phc-winner-argon2)
* [Bcrypt](http://bcrypt.sourceforge.net/)
* [PBKDF2](http://en.wikipedia.org/wiki/PBKDF2)
* [scrypt](http://www.tarsnap.com/scrypt.html)

[1]: http://en.wikipedia.org/wiki/Key_derivation_function

## Installation

Add the following dependency to your deps.edn file:

    crypto-password/crypto-password {:mvn/version "0.3.0"}

Or to your Leiningen project file:

    [crypto-password "0.3.0"]

## Usage

Pick an encryption algorithm, either `pbkdf2`, `bcrypt`, `scrypt`
or `argon2`:

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

## Defaults

The default options for the key derivation algorithms were chosen
based on benchmarks carried on on a AWS t1.micro server running Ubuntu
13.10 (ami-35dbde5c), in March 2014.

On this hardware, the key derivation functions take approximately
200ms to complete with their default options. This is a short enough
time to not be an inconvenience for a human being, but long enough to
make brute forcing encrypted passwords very costly.

## Documentation

* [API Docs](http://weavejester.github.com/crypto-password/)

## License

Copyright © 2025 James Reeves

Distributed under the Eclipse Public License, the same as Clojure.
