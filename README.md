# aws-xray-clj

[![Build Status](https://travis-ci.org/jaakkos/clj-aws-xray.svg?branch=master)](https://travis-ci.org/jaakkos/clj-aws-xray)

Clojure wrapper for Amazon X-Ray https://aws.amazon.com/xray/

## Installation

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.jaakkos/clj-aws-xray.svg)](https://clojars.org/org.clojars.jaakkos/clj-aws-xray)


## Usage

### With Ring

    (ns.some.awesome.app
      (:require [ring.middleware.aws.xray :refer [wrap-xray-segment]]))

    (def app
      (-> handler
      (wrap-xray-segment {:segment "my-awesome-segment"})))


## Options

### For Rind Middleware

    {:segment "segment name"}


## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
