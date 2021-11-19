(ns wip-tauri-cljs.core
  (:require
   [wip-tauri-cljs.events :as events]
   [wip-tauri-cljs.subs]
   [wip-tauri-cljs.views :as views]
   [re-frame.core :as rf]
   [reagent.core]
   [reagent.dom :as rd]))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (rd/render [views/main-panel]
             (.getElementById js/document "app")))

(defn ^:export init []
  (rf/dispatch-sync [::events/init-rf-db])
  (mount-root))

(defn suspend! [])
(defn resume [])
