(defproject com.lucasbradstreet/instaparse-cljs "1.4.1.3-SNAPSHOT"
  :description "Instaparse: No grammar left behind"
  :url "https://github.com/lbradstreet/instaparse-cljs"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.40"]]
  :profiles {:dev {:dependencies 
                   [[org.clojure/tools.trace "0.7.5"]
                    [criterium "0.3.1"]
                    [rhizome "0.1.8"]]}
             :1.5 {:dependencies [[org.clojure/clojure "1.5.1"]]}
             :1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}
             :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]}
             :1.8 {:dependencies [[org.clojure/clojure "1.8.0"]]}
             :test {:plugins
                    [[com.keminglabs/cljx "0.6.0" :exclusions [org.clojure/clojure]]]
                    :prep-tasks [["cljx" "once"]]}}
  :aliases {"test-all" ["with-profile" "+1.5:+1.6:+1.7:+1.8" "test"]
            "cleantestcljs" ["do" "clean," "cljx" "once," "cljsbuild" "test" "unit-tests"]}
  :test-paths ["target/generated/src/clj" "target/generated/test/clj"]
  :source-paths ["src/cljs" "src/clj" "src/cljc"
                 "target/generated/src/clj"]
  :clj {:source-paths  ["src/clj",  "target/generated/src/clj"]
        :test-paths  ["test/clj",  "target/generated/test/clj"]}
  :cljx {:builds [{:source-paths  ["test/cljx"]
                   :output-path "target/generated/test/clj"
                   :rules :clj}
                  {:source-paths  ["test/cljx"]
                   :output-path  "target/generated/test/cljs"
                   :rules :cljs}]}
  :cljsee {:builds [{:source-paths ["src/cljc"]
                     :output-path "target/generated/src/clj"
                     :rules :clj}]}
  :plugins [[lein-cljsbuild "1.1.3"]
            [cljsee "0.1.0"]]
  ;:hooks [leiningen.cljsbuild]
  :target-path "target"
  :scm {:name "git"
        :url "https://github.com/lbradstreet/instaparse-cljs"}
  :cljsbuild {:builds [{:id "none"
                        :source-paths ["src/cljs"
                                       "src/cljc"]
                        :compiler {:output-to "target/js/none.js"
                                   :optimizations :none
                                   :pretty-print true}}
                       {:id "test"
                        :source-paths ["src/cljs"
                                       "src/cljc"
                                       "runner/cljs"
                                       "target/generated/test/clj"
                                       "target/generated/test/cljs"]
                        :compiler {:output-to "target/js/advanced-test.js"
                                   :optimizations :advanced
                                   :target :nodejs
                                   :pretty-print false}}]
              :test-commands {"unit-tests" ["node" "target/js/advanced-test.js"]}})
