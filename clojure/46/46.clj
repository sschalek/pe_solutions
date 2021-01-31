(def prime?
  (memoize (fn [n]
             (if (< n 2)
               false
               (let [possibleFactors (filter prime? (range 2 (inc (int (Math/floor (Math/sqrt n))))))]
                 (not-any? zero? (map #(rem n %) possibleFactors)))))))

(defn get-goldbach [n]
  (for [p (filter prime? (range 2 n))
        s (range 1 n)
        :let [val (+ p (* 2 s s))]
        :when (= n val)]
    [p s]))

(take 1 (filter
         #(empty? (second %))
         (map
          (fn [n] [n (get-goldbach n)])
          (filter #(and (odd? %) (not (prime? %))) (iterate inc 2)))))
