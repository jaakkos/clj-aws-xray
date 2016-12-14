(ns xray.core
  (:import [com.amazonaws.xray AWSXRay]))

(defn wrap-to-segment [segment-name fun]
  (AWSXRay/beginSegment segment-name)
  (let [response (fun)]
    (AWSXRay/endSegment)
    response))

(defn wrap-to-subsegment [segment-name fun]
  (AWSXRay/beginSubsegment segment-name)
  (let [response (fun)]
    (AWSXRay/endSubsegment)
    response))

