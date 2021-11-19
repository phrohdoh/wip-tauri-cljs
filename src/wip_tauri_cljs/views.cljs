(ns wip-tauri-cljs.views
  (:require
   [wip-tauri-cljs.events :as events]
   [wip-tauri-cljs.subs :as subs]
   [re-frame.core :as rf]
   ["@tauri-apps/api/dialog" :as tauri.dialog]))

(defn read-text-file []
  (-> (tauri.dialog/open)
      (.then #(rf/dispatch [::events/read-file %])
             #_(fn [file-name]
               (prn [`read-text-file {'file-name file-name}])
               (rf/dispatch [::events/read-file file-name])))))

(def default-button-opts
  {:style {:padding "4px 8px"
           :cursor :pointer
           :background-color "#220066"
           :display :inline-block
           :border-radius "4px"
           :color :white}})

(defn button [opts content]
  [:div (merge default-button-opts opts)
   content])

(defn reset-db-button []
  [:div.flex.fixed.bottom-0.p-2.rounded-sm
   [:button.bg-white.border.rounded.py-1.px-2.text-xs.text-red-500.hover:bg-red-500.hover:text-white
    {:on-click #(js/alert "r u sure?"),#_#(rf/dispatch-sync [::events/init-rf-db])}
    "reset re-frame db"]])

(defn main-panel []
  [:div {:font-family "Arial, Helvetica, sans-serif"}
   [reset-db-button]
   [:h2
    {:style {:text-align :center}}
    "wip tauri+cljs"]
   [:div {:style {:margin "48px"}}
    [button {:on-click read-text-file} "Open text file..."]
    (when-let [content @(rf/subscribe [::subs/content])]
      [:div {:style {:margin-top "24px"}}
       [:h3 @(rf/subscribe [::subs/file-name])]
       [:div
        {:style {:padding "8px"
                 :margin-top "24px"
                 :max-width "600px"
                 :border "solid 2px #ddd"
                 :border-radius "4px"}}
        content]])]])
