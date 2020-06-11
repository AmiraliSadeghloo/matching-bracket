(ns matching-brackets.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
;;/////////////////////////////////
(def string "[\"My Application\" \n \"This map has different keys like : \" \n [{:price 100 :side :sell}{:price 123 :side :buy}]\n \"Has a state\" \n :balance \n \"You can cancel the orders at any time\"  \n \"in form of a map : \" \n :profit \n \"!!!!\"\n {:balance 123000 \n  :profit 500 \n  :orders [{:price 100 :side :sell}\n           {:price 123 :side :buy}]} \n ]")
(def strCount (count string))
; (println string)
;;////////////////////////////////
(defn processString []                                      ;;turns any string to a string of just brackets
  (loop [i 0 pString ""]
    (if (> i (dec strCount))
      pString
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
    (if (and (= lastOne \() (= two \)))
      (pop one)
      (if (and (= lastOne \[) (= two \]))
        (pop one)
        (if (and (= lastOne \{) (= two \}))
          (pop one)
          (conj one two))))))
;;////////////////////////////////
(defn reducedStack []                                       ;;if stack = nil -> returns true as result
  (let [reduced (reduce stack [(first (processString))] (rest (processString)))]
    reduced))
; (println (reducedStack))
(true? (= [] (reducedStack)))

