(set-env!
  :dependencies '[[adzerk/boot-cljs          "1.7.48-6"]
                  [adzerk/boot-reload        "0.4.1"]
                  [hoplon/boot-hoplon        "0.1.10"]
                  [tailrecursion/boot-heredoc "0.1.1"]
                  [hoplon/hoplon             "6.0.0-alpha10"]
                  [org.clojure/clojure       "1.7.0"]
                  [org.clojure/clojurescript "1.7.145"]
                  [tailrecursion/boot-jetty  "0.1.0"]
                  [alandipert/storage-atom   "1.2.4"]
                  [datascript                "0.13.1"]]
  :source-paths #{"src"}
  :asset-paths  #{"assets"})

(require
  '[adzerk.boot-cljs         :refer [cljs]]
  '[adzerk.boot-reload       :refer [reload]]
  '[hoplon.boot-hoplon       :refer [hoplon prerender]]
  '[boot.heredoc             :refer [heredoc]]
  '[tailrecursion.boot-jetty :refer [serve]])

(deftask dev
  "Build edit for local development."
  []
  (comp
    (watch)
    (speak)
    (heredoc)
    (hoplon)
    (reload)
    (cljs)
    (serve :port 8000)))

(deftask prod
  "Build edit for production deployment."
  []
  (comp
    (hoplon)
    (cljs :optimizations :advanced)
    (prerender)))
