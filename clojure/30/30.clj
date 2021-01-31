(defn digit-power-sum [n pow]
    (let [strN (str n)
          firstDigitPower (int (java.lang.Math/pow (java.lang.Integer/parseInt (str (first strN))) pow))]
        (if (= 1 (count strN))
            firstDigitPower
            (+
                firstDigitPower
                (digit-power-sum
                    (java.lang.Integer/parseInt (clojure.string/join (rest strN)))
                    pow)))))

(defn nums-match-digit-power-sum [pow from]
    (lazy-seq
        (let [sum (digit-power-sum from pow)
              rolloverNum? (> (count (str (inc from))) (count (str from)))
              sumBelowNum? (< sum from)]
            (if (and rolloverNum? sumBelowNum?)
                `()
                (let [remainingMatches (nums-match-digit-power-sum pow (inc from))]
                    (if (= sum from)
                        (cons from remainingMatches)
                        remainingMatches))))))

(apply + (nums-match-digit-power-sum 5 2))