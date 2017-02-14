(ns kris.todoapp
  (:require [rum.core :as rum]
            [clojure.string :as str]
            [kris.todo-helpers :as helper]))

(enable-console-print!)

(def mock-todos
  [{:id 14130 :name "Set up an environment" :isComplete true}
   {:id 22708 :name "Learn Rum" :isComplete false}
   {:id 15571 :name "Build an Awesome App" :isComplete false}
   {:id 45416 :name "Ship it!" :isComplete true}])

(defn handle-submit
  [evt]
  (.preventDefault evt)
  (println "TODO Saving now: " (helper/add-todo mock-todos {:id (helper/generate-id) :name "TEST" :isComplete false})))

(defn handle-change
  [evt]
  (let [value (.-value (.-target evt))]
    (println (str "TODO - save " value))))

(defn handle-toggle
  [id]
  (helper/toggle-todo (helper/find-by-id id mock-todos)))
  ;; at this point have the toggled to do, but need to update the list

(rum/defc todo-input
  []
  [:div
    [:form {:on-submit handle-submit}
      [:input { :on-change handle-change}]]
    [:br]])

(rum/defc todo-checkbox
  [isComplete, id]
  [:input { :type "checkbox"
            :checked isComplete
            :on-click handle-toggle}])

(rum/defc todo-item
  [todo]
  [:li { :class "list-unstyled"}
    (todo-checkbox (:isComplete todo) (:id todo))
    (str "   " (:name todo))])

(rum/defc todo-list
  [mock-todos]
  [:ul { :class "text-justify"}
    (todo-input)
    (map #(todo-item %) mock-todos)])

(rum/defc app
  []
  [:div.app { :class "text-center"}
    [:h1 "ToDo App"]
    (todo-list mock-todos)])

(rum/mount (app) (.getElementById js/document "mount"))
