# crypto-password

A Clojure library for encrypting passwords. Supports the following
algorithms:

* [PBKDF2](http://en.wikipedia.org/wiki/PBKDF2)
* [Bcrypt](http://bcrypt.sourceforge.net/)
* [scrypt](http://www.tarsnap.com/scrypt.html)

## Installation

Add the following dependency to your `project.clj` file:

    [crypto-password "0.1.0-SNAPSHOT"]

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

And the `equal?` function to check the encrypted password against a
plaintext password:

```clojure
(password/equal? "foobar" encrypted) ;; => true
```

## License

Copyright © 2013 James Reeves

Distributed under the Eclipse Public License, the same as Clojure.
