(def prime?
  (memoize (fn [n]
             (if (< n 2)
               false
               (let [possibleFactors (filter prime? (range 2 (inc (int (Math/floor (Math/sqrt n))))))]
                 (not-any? zero? (map #(rem n %) possibleFactors)))))))

(def primes-below-million (filter prime? (range 1000000)))

(defn find-longest-prime-sum-seq [maxNum]
  (let [primesBelowMax (filter prime? (range maxNum))]
    (loop [startInd 0
           endInd 0
           sum 0
           longestStart 0
           longestEnd 0
           longestSum 0]
      (if (>= (nth primesBelowMax startInd))
        ))))
