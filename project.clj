(defproject org.clojars.jaakkos/clj-aws-xray "0.0.3"
  :description "Clojure wrapper for AWS X-Ray"
  :url "https://github.com/jaakkos/clj-aws-xray"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.4.0"]
                 [com.amazonaws/aws-xray-recorder-sdk-core "1.0.0-beta"]
                 [com.amazonaws/aws-xray-recorder-sdk-aws-sdk "1.0.0-beta"]
                 [com.amazonaws/aws-xray-recorder-sdk-aws-sdk-instrumentor "1.0.0-beta"]
                 [com.amazonaws/aws-xray-recorder-sdk-apache-http "1.0.0-beta"]
                 [com.amazonaws/aws-xray-recorder-sdk-sql-postgres "1.0.0-beta"]
                 [com.amazonaws/aws-java-sdk-xray "1.11.64"]]
  :aliases {"test-all" ["with-profile" "default:+1.8:+1.9" "test"]}
  :profiles
  {:dev {:dependencies [[ring/ring-mock "0.3.0"]
                        [org.mockito/mockito-core "2.3.0"]]}
   :1.8 {:dependencies [[org.clojure/clojure "1.8.0"]]}
   :1.9 {:dependencies [[org.clojure/clojure "1.9.0-alpha10"]]}}
  :deploy-repositories
  [["clojars"
    {:password :gpg
     :username :gpg
     :passphrase :gpg
     :url "https://clojars.org/repo/"}]],
  :signing {:gpg-key "98B67CA0"})
