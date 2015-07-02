(ns donna.redis
  (:require [taoensso.carmine :as car :refer (wcar)]))

(def server1-conn
  {:poll {}
   :spec {:host "127.0.0.1" :port 6379}})

(defmacro my-redis [& body]
  `(car/wcar server1-conn ~@body))

(defn my-set
  [key value]
  (car/lua "return redis.call('set', _:my-key, 'lua '.. _:my-val)"
           {:my-key key}
           {:my-val value}
           ))

;;;;;;;; RECAP ;;;;;;;;

;; 1. Input Data Games & Movies
(defn uuid [] (str (java.util.UUID/randomUUID)))

;contoh:

(comment
  (let [id (uuid)]
    (my-redis (car/set id {:title "Dinoshark" :director "Unknown" :type :movies :genre :action})

              (indexing id))))



;; 2. Indexing Data Based On Types (Types :games & :movies)
(defn indexing
  [an-id]
  (let [type (get (my-redis (car/get an-id)) :type)]
    (car/set type
             (conj (my-redis (car/get type)) an-id))))


;; 3. Querying Data

; 3a. (fn [type {kv-pair}]) => (list)

(defn search-kv
  ([type kv-pair]
   (->> (my-redis (car/get type))
        (map #(my-redis (car/get %)))
        (filter #(= kv-pair (select-keys % (keys kv-pair))))
        ))
  ([type kv-pair bool]
    (if (true? bool) (search-kv type kv-pair)
      (->> (my-redis (car/get type))
           (filter
             #(= kv-pair (select-keys (my-redis (car/get %)) (keys kv-pair))))))))