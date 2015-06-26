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

(defn prime?
  [n]
  (cond
    (== n 2) true
    (even? n) false
    :else (every? #(pos? (rem n %))
                  (range 3 n))))

(defn ^long sum-primes
  [^long lim]
  (loop [i (int 3) res (int 2)]
    (if (> i (quot lim 2))
      res
      (if (prime? i)
        (recur (+ i 2) (+ res i))
        (recur (+ i 2) res)))))






