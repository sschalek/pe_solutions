(def increase-amount 3330)

(def prime?
  (memoize (fn [n]
             (if (< n 2)
               false
               (let [possibleFactors (filter prime? (range 2 (inc (int (Math/floor (Math/sqrt n))))))]
                 (not-any? zero? (map #(rem n %) possibleFactors)))))))

(for [x (range 1487 (- 10000 (* increase-amount 2)))
      :let [series [x (+ x increase-amount) (+ x (* increase-amount 2))]]
      :when (every? identity (map prime? series))
      :let [seriesStrings (map str series)]
      :when (= (count (distinct (map sort seriesStrings))) 1)]
  series)
