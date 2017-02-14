(ns kris.todo-helpers)

(defn add-todo
  "Add a todo item to the end of the list of todos"
  [list item]
  (conj list item))

(defn generate-id
  "Generates a random id number up to 100000"
  []
  (rand-int 100000))

(defn find-by-id
  "Takes an id and list and returns the todo from the list with that id"
  [search-id list]
  (first (filter #(= search-id (:id %)) list)))

(defn toggle-todo
  "Takes a todo item and returns that item with :isComplete boolean field toggled"
  [todo]
  (println (assoc todo :isComplete (not (:isComplete todo)))))
