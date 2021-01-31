(def factors {3 2
              4 3
              5 5
              6 7
              7 11
              8 13
              9 17})

(defn int-from-digits [digits]
  (reduce +
          (map-indexed
           #(* (int (Math/pow 10 %1)) %2)
           (reverse digits))))

(defn get-divisible-pandigitals [digitChoices digits]
  (if (empty? digitChoices)
    [(int-from-digits digits)]
    (let [nextDigitInd (count digits)
          factorNeeded? (>= nextDigitInd 3)
          factor (if factorNeeded? (factors nextDigitInd) 0)
          prevTwoDigits (map digits (range (- nextDigitInd 2) nextDigitInd))
          potentialNextDigits (if factorNeeded?
                                (filter #(zero? (rem (int-from-digits (concat prevTwoDigits [%])) factor)) digitChoices)
                                digitChoices)]
      (if (empty? potentialNextDigits)
        []
        (mapcat
         #(get-divisible-pandigitals (filterv (fn [digit] (not (= digit %))) digitChoices) (into digits [%]))
         potentialNextDigits)))))

(reduce + (get-divisible-pandigitals [0 1 2 3 4 5 6 7 8 9] []))
