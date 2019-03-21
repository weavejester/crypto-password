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

    [crypto-password "0.2.1"]

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

Copyright © 2019 James Reeves

Distributed under the Eclipse Public License, the same as Clojure.
