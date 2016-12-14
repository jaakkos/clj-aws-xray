(ns ring.middleware.aws.xray
  (:require [xray.core :refer [wrap-to-segment wrap-to-subsegment]])
  (:import [com.amazonaws.xray AWSXRay]))

(defn wrap-xray-subsegment
  ([handler] (wrap-xray-subsegment handler {:segment "nameless-sub-segment"}))
  ([handler options]
   (fn [request]
    (wrap-to-subsegment (:segment options) #(handler request)))))

(defn wrap-xray-segment
  ([handler] (wrap-xray-segment handler {:segment "nameless-segment"}))
  ([handler options]
   (fn [request]
     (wrap-to-segment (:segment options) handler request))))

