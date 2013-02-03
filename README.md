# crypto-password

A Clojure library for encrypting passwords. Supports the following
algorithms:

* [PBKDF2](http://en.wikipedia.org/wiki/PBKDF2)

## Installation

Add the following dependency to your `project.clj` file:

    [crypto-password "0.1.0-SNAPSHOT"]

## Functions

### `(crypto.password/encrypt raw)`

One-way encrypton for a plaintext password.

### `(crypto.password/equal? raw encrypted)`

Returns true if a plaintext password matches the encrypted value.

## License

Copyright © 2013 James Reeves

Distributed under the Eclipse Public License, the same as Clojure.
