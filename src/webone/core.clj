(ns webone.core
  (:require
    [compojure.core :refer :all]
    [compojure.route :as croute]
    [org.httpkit.server :refer [run-server] :as hk]
    [noir.session :refer [wrap-noir-session] :as sess]
    [noir.response :as resp]
    [ring.middleware.defaults :refer :all]
    [hiccup.core :refer [html]]
    [hiccup.page :refer [include-js]]))

(defn page
  []
  (html [:head]
        [:body
         [:div {:id "body"}
          [:h1 "Muounyong"]]
         [:div {:id "tombol"}
          [:h1 "tulisan gede"]]
         (include-js "zepto.min.js")
         (include-js "app.js")]))

(defroutes
  home
  (GET "/" [] "<h1>Heloow</h1>")
  (GET "/monyong" req
    (page))
  (GET "/session" req
    (do (sess/put! :username "jojolipeta")
        (str "Hello " (sess/get :username))))
  (GET "/json" req
    (resp/json {:hello "world" :from "JSON"}))
  (croute/resources "resources/public"))

(def server (atom nil))

(def app
  (-> home
      (wrap-defaults (assoc site-defaults
                       :cookies true
                       :params {:keywordize true
                                :nested     true}))
      (wrap-noir-session)))

(defn run
  []
  (reset! server (run-server app {:port 3000})))

(defn stop
  []
  (@server))


