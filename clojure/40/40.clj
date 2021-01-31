(defn get-digit-count [n]
  (letfn [(helper [n count]
                  (if (zero? n)
                    count
                    (recur (quot n 10) (inc count))))]
    (helper n 0)))

(defn get-digit [n digitNum]
  (let [digitCount (get-digit-count n)
        truncatedNum (nth (iterate #(quot % 10) n) (- digitCount digitNum 1))]
    (rem truncatedNum 10)))

(defn get-champ-constance-digit [digitNum]
  (letfn [(helper [digitNum previousDigitCount nextNumber]
                  (let [additionDigitCount (get-digit-count nextNumber)
                        newTotalDigitCount (+ previousDigitCount additionDigitCount)
                        targetDigitInAddition? (> newTotalDigitCount digitNum)]
                    (if targetDigitInAddition?
                      (get-digit nextNumber (- digitNum previousDigitCount))
                      (recur digitNum newTotalDigitCount (inc nextNumber)))))]
    (helper digitNum 0 1)))

(reduce * (map #(get-champ-constance-digit (- % 1)) [1 10 100 1000 10000 100000 1000000]))
