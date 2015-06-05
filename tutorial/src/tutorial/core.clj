(ns tutorial.core
  (:require [tutorial.sample :as ts]
            [questdb.core :refer :all]
            [clojure.set :refer [union difference]]))

(defn kuadratin-semua
  [lst]
  (map ts/square lst))

(defn cubin-semua
  [lst]
  (map ts/cube lst))

(defn gabungin
  [s1 s2]
  (union s1 s2))

(def db "hellodb")






