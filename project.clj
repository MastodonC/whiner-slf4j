(def slf4j-version "1.7.21")
(defproject whiner-slf4j "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [org.clojure/tools.logging      "0.3.1"]
                 [ch.qos.logback/logback-classic "1.1.7"]
                 [org.slf4j/jul-to-slf4j         ~slf4j-version]
                 [org.slf4j/jcl-over-slf4j       ~slf4j-version]
                 [org.slf4j/log4j-over-slf4j     ~slf4j-version]
                 [org.clojure/core.async "0.2.385"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler whiner.handler/app
         :port 3356}
  :uberjar-name "whiner-slf4j.jar"
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
