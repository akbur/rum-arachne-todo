(ns kris.todoapp.web)

(defn healthcheck
  [req]
  {:status 200
   :body "OK"})
