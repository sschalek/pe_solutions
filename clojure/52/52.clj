(defn same-digits-in-multiples? [n maxMultiple]
  (let [baseSortedDigitStr (sort (str n))
        multiples (map #(* n %) (range 2 (inc maxMultiple)))
        multipleSortedDigitStrs (map #(sort (str %)) multiples)]
    (every? #(= baseSortedDigitStr %) multipleSortedDigitStrs)))

(time
 (take 1
       (filter #(same-digits-in-multiples? % 6) (iterate inc 1))))
