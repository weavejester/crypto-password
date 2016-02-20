(defproject crypto-password "0.1.3"
  :description "Library for securely hashing passwords"
  :url "https://github.com/weavejester/crypto-password"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[codox "0.6.6"]]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [crypto-random "1.2.0"]
                 [crypto-equality "1.0.0"]
                 [commons-codec "1.10"]
                 [org.mindrot/jbcrypt "0.3m"]
                 [com.lambdaworks/scrypt "1.4.0"]]
  :codox {:src-dir-uri "http://github.com/weavejester/crypto-password/blob/0.1.3/"
          :src-linenum-anchor-prefix "L"}
  :aliases {"test-all" ["with-profile" "+1.6:+1.7:+1.8" "test"]}
  :profiles
  {:1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}
   :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]}
   :1.8 {:dependencies [[org.clojure/clojure "1.8.0"]]}})
