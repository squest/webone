(ns donna.core
    (:require [questdb.core :refer :all]
              [compojure.core :refer :all]
              [compojure.route :as route]
              [org.httpkit.server :refer [run-server]]))

(defroutes donna
           (GET "/" [] "Hello, Excellence!")
           (GET "/westeros" [] "Hello, Westeros!")
           (GET "/essos" [] "Hello, Essos!"))

(defn -main []
  (run-server donna {:port 5000}))

(def db "firstdb")

(for [i (get-docs db)
      j [{:homeland :Westeros}]
      :let [gabung (put-doc! db (merge (get-doc db (i :uuid)) j))]]
    gabung)

(def lannbroh [{:name "Tywin Lannister" :status :deceased :description "killed by a dwarf" :homeland :Westeros :last_seen "King's Landing"}
               {:name "Cersei Lannister" :status :alive :description "imprisoned by a sparrow" :homeland :Westeros :last_seen "Sacred Sept"}
               {:name "Jamie Lannister" :status :alive :description "drinking with Prince Doran" :last_seen "Dorne"}
               {:name "Tyrion Lannister" :status :alive :description "with Khaleesi. Advising" :last_seen "Mereen"}])

(for [i lannbroh
      j [{:type :noble :hometown "Casterly Rock"}]
      :let [gabungan (merge i j)]]
  gabungan)

(defn a [x] (str x))