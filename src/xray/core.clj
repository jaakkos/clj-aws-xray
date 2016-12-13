(ns xray.core
  (:import [com.amazonaws.xray AWSXRay]))

(defn wrap-to-segment [segment-name fun]
  (let [segment (AWSXRay/beginSegment segment-name)
        response (fun)]
    (.end segment)
    response))

(defn wrap-to-subsegment [segment-name fun]
  (let [segement (AWSXRay/beginSubsegment segment-name)
        response (fun)]
    (.end segement)
    response))

