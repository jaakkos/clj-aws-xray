(ns xray.core
  (:require [clojure.string :as string :refer [upper-case]]
            [ring.util.request :refer [request-url]])
  (:import [com.amazonaws.xray AWSXRay]))

(defn get-client-ip [request]
  (if-let [ip-list (get-in request [:headers "x-forwarded-for"])]
    (-> ip-list
        (string/split #",")
        (first))
    (:remote-addr request)))

(defn wrap-to-segment [segment-name handler request]
  (let [segment (AWSXRay/beginSegment segment-name)
        response (handler request)]
    (.putHttp segment "request" {"method" (-> (:request-method request)
                                              (name)
                                              (upper-case))
                                 "client_ip" (get-client-ip request)
                                 "url" (request-url request)
                                 "user_agent" (get-in request [:headers "user-agent"] "Unkown")})
    (.putHttp segment "response" {"status" (:status response)
                                  "content_length" (get-in response [:headers "content-length"] 0) })
    (AWSXRay/endSegment)
    response))

(defn wrap-to-subsegment [segment-name handler]
  (AWSXRay/beginSubsegment segment-name)
  (let [response (handler)]
    (AWSXRay/endSubsegment)
    response))

