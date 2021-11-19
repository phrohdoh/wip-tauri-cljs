(ns wip-tauri-cljs.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 ::content
 (fn [db]
   (:app/content db)))

(rf/reg-sub
 ::file-name
 (fn [db]
   (:app/file-name db)))
