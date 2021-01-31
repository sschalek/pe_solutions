(def prime-factors
  (memoize (fn [n]
             (if (< n 2)
               []
               (let [possibleFactors (filter #(empty? (prime-factors %)) (range 2 (inc (quot n 2))))]
                 (filter #(zero? (rem n %)) possibleFactors))))))

(defn find-prime-factor-sequence [length]
  (take 1 (for [n1 (iterate inc 1)
                :let [numbers (range n1 (+ n1 length))
                      factors (map #(distinct (prime-factors %)) numbers)]
                :when (every? identity (map #(= (count %) length) factors))]
            [factors numbers])))

(find-prime-factor-sequence 4)
