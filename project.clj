(defproject crypto-password "0.3.0"
  :description "Library for securely hashing passwords"
  :url "https://github.com/weavejester/crypto-password"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [crypto-random "1.2.1"]
                 [crypto-equality "1.0.1"]
                 [commons-codec "1.19.0"]
                 [at.favre.lib/bcrypt "0.10.2"]
                 [com.lambdaworks/scrypt "1.4.0"]
                 [de.mkammerer/argon2-jvm "2.12"]]
  :plugins [[lein-codox "0.9.4"]]
  :codox
  {:output-path "codox"
   :project  {:name "Crypto-Password"}
   :metadata {:doc/format :markdown}
   :source-uri "http://github.com/weavejester/crypto-password/blob/{version}/{filepath}#L{line}"}
  :aliases {"test-all" ["with-profile" "+1.6:+1.7:+1.8:+1.9:+1.10" "test"]}
  :profiles
  {:1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}
   :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]}
   :1.8 {:dependencies [[org.clojure/clojure "1.8.0"]]}
   :1.9 {:dependencies [[org.clojure/clojure "1.9.0"]]}
   :1.10 {:dependencies [[org.clojure/clojure "1.10.0"]]}})
