(ns wip-tauri-cljs.events
  (:require
   [re-frame.core :as rf]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [wip-tauri-cljs.db :as db]
   ["@tauri-apps/api/fs" :as tauri.fs]))


(rf/reg-event-db
 ::init-rf-db
 (fn-traced [_ _]
   db/default-rf-db))

(rf/reg-event-db
 ::add-file
 (fn-traced [db [_ file-name content]]
   (assoc db
          :app/content content
          :app/file-name file-name)))

(rf/reg-event-fx
 ::read-file
 (fn-traced [_ [_ file-name]]
   (-> (tauri.fs/readTextFile file-name)
       (.then #(rf/dispatch [::add-file file-name %])))))
