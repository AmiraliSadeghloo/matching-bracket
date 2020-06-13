(ns matching-brackets.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
;;/////////////////////////////////
(def string "(defn send-image-to-contact! [client contact image]\n  (go\n    (.sendFile client\n               (async/<! (find-contacts! client contact))\n               (async/<! image)\n               \"hellooo.jpeg\"\n               \"test bot\")))\n\u200B\n(defn send-image-to-contacts! [client contact-list image]\n  (go (let [c (async/chan)]\n        (loop []\n          (if-let [contact (async/>! c contact-list)]\n            (do (async/>! c (send-image-to-contact! client contact image))\n                (recur))\n            (async/close! c))))))\n\u200B\n(defn main []\n  (go (let [client   (<p! (wa/create))\n            image    (place-text-on-image! pic-gen-url)\n            contacts [\"person1\"\n                      \"person2\"\n                      \"person3\"]]\n        (prn \"begin\")\n        #_(send-image-to-contacts! client contacts image)\n        (send-image-to-contact! client \"person1\" image)\n        (prn \"finished\"))))\n")
(def strCount (count string))
; (println string)
;;////////////////////////////////
(defn processString []                                      ;;turns any string to a string of just brackets
  (loop [i 0 pString ""]
    (if (> i (dec strCount))
      pString                                               ;;reduce
      (case (nth string i)
        \( (recur (inc i) (str pString \())
        \) (recur (inc i) (str pString \)))
        \[ (recur (inc i) (str pString \[))
        \] (recur (inc i) (str pString \]))
        \{ (recur (inc i) (str pString \{))
        \} (recur (inc i) (str pString \}))
        (recur (inc i) pString)))))
(processString)
;;////////////////////////////////
(defn stack [one two]                                       ;;Stacks 3 types of opening-closing brackets
  (let [lastOne (last one)]
    (if (or (and (= lastOne \() (= two \)))
            (and (= lastOne \[) (= two \]))
            (and (= lastOne \{) (= two \})))
      (pop one)
      (conj one two)
      )))
;;////////////////////////////////                          ;;[string]
(defn reducedStack []                                       ;;if stack = nil -> returns true as result
  (let [reduced (reduce stack [(first (processString))] (rest (processString)))]
    reduced))
; (println (reducedStack))
(true? (= [] (reducedStack)))

