(defproject crypto-password "0.1.3"
  :description "Library for securely hashing passwords"
  :url "https://github.com/weavejester/crypto-password"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[codox "0.6.6"]]
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [crypto-random "1.2.0"]
                 [crypto-equality "1.0.0"]
                 [commons-codec "1.6"]
                 [org.mindrot/jbcrypt "0.3m"]
                 [com.lambdaworks/scrypt "1.4.0"]]
  :codox {:src-dir-uri "http://github.com/weavejester/crypto-password/blob/0.1.3/"
          :src-linenum-anchor-prefix "L"}
  :profiles
  {:1.3 {:dependencies [[org.clojure/clojure "1.3.0"]]}
   :1.4 {:dependencies [[org.clojure/clojure "1.4.0"]]}
   :1.5 {:dependencies [[org.clojure/clojure "1.5.1"]]}
   :1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}})
