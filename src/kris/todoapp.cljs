(ns kris.todoapp
  (:require [rum.core :as rum]
            [clojure.string :as str]))

(enable-console-print!)

(def mock-todos
  [{:id 1 :name "Set up an environment" :isComplete true}
   {:id 2 :name "Learn Rum" :isComplete false}
   {:id 3 :name "Build an Awesome App" :isComplete false}
   {:id 4 :name "Ship it!" :isComplete true}])

(rum/defc todo-checkbox
  [isComplete]
  [:input { :type "checkbox"
            :checked isComplete
            :on-click (fn [_] (println "TODO - toggle check box"))}])

(defn handle-submit
  [evt]
  (.preventDefault evt)
  (println "TODO - save now")
  true)

(defn handle-change
  [evt]
  (let [value (.-value (.-target evt))]
    (println (str "TODO - save " value))))

(rum/defc todo-input
  []
  [:div
    [:form {:on-submit handle-submit}
      [:input { :on-change handle-change}]]
    [:br]])

(rum/defc todo-item
  [todo]
  [:li { :class "list-unstyled"}
    (todo-checkbox (:isComplete todo))
    (str "  " (:id todo) ". " (:name todo))])

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
