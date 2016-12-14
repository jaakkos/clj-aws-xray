(ns ring.middleware.aws.xray-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer [request]]
            [ring.util.response :refer [response]]
            [ring.middleware.aws.xray :refer :all])
  (:import [com.amazonaws.xray AWSXRayRecorder AWSXRay]
           [com.amazonaws.xray.emitters Emitter]
           [org.mockito Mockito]))

(def ^:dynamic *mocked-emitter* nil)
(def ^:dynamic *mocked-recorder* nil)

(defn set-global-recorder [f]
  (let [emitter (Mockito/mock Emitter)
        recorder (AWSXRayRecorder.)
        mock-xray-recorder (Mockito/spy recorder)]
    (binding [*mocked-recorder* mock-xray-recorder
              *mocked-emitter* emitter]
      (.setEmitter *mocked-recorder* emitter)
      (AWSXRay/setGlobalRecorder *mocked-recorder*)
      (f))))

(use-fixtures :each set-global-recorder)

(deftest ring.middleware.aws.xray
  (let [handle-foobar (constantly (response "foobar"))]
    (testing "wrap main with segment"
      (let [handler (wrap-xray-segment handle-foobar {:segment "my-segment"})
            res (handler (request :get "/"))]
        (doto (Mockito/verify *mocked-recorder*)
          (.beginSegment "my-segment"))))))
