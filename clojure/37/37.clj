(def prime?
  (memoize (fn [n]
             (if (< n 2)
               false
               (let [possibleFactors (filter prime? (range 2 (inc (int (Math/floor (Math/sqrt n))))))]
                 (not-any? zero? (map #(rem n %) possibleFactors)))))))

(defn get-rtl-truncatable-primes [maxDigits]
  (letfn [(helper [maxDigits currentDigits]
                  (let [digitsNum (if (empty? currentDigits) 0 (Integer/parseInt currentDigits))
                        isCurrentPrime (prime? digitsNum)
                        shouldContinue (and (< (count currentDigits) maxDigits) (or (empty? currentDigits) isCurrentPrime))]
                    (concat
                     (if (and isCurrentPrime (> (count currentDigits) 1)) [digitsNum] [])
                     (if shouldContinue
                       (mapcat #(helper maxDigits (clojure.string/join (concat currentDigits (str %)))) [0 1 2 3 4 5 6 7 8 9])
                       []))))]
    (helper maxDigits "")))

(defn ltr-truncatable-prime? [n]
  (let [strNum (str n)
        rightPartStr (clojure.string/join (rest strNum))]
    (and
     (prime? n)
     (or (empty? rightPartStr) (ltr-truncatable-prime? (Integer/parseInt rightPartStr))))))

(defn get-ltr-rtl-truncatable-primes [maxDigits]
  (filter ltr-truncatable-prime? (get-rtl-truncatable-primes maxDigits)))

(reduce + (get-ltr-rtl-truncatable-primes 6))
