(ns whiner.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clojure.tools.logging :as log]))

(defroutes app-routes
  (GET "/info" []
       (log/info "informative message")
       "Info")
  (GET "/warn" []
       (log/warn "warning")
       "Warning")
  (GET "/error" []
       (log/error "error")
       "Error")
  (GET "/exception" []
       (do
         (throw (Exception. "this is an exception"))
         "never reached"))
  (GET "/runtime" []
       (do
         (quot 1 0)
         "never reached"))
  (route/not-found "Not Found"))

(defn wrap-catch-exceptions [handler]
  (fn [request]
    (try (handler request)
         (catch Throwable t (log/error t)))))

(def app
  (-> (wrap-defaults app-routes site-defaults)
      wrap-catch-exceptions))
