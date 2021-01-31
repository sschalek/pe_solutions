(defn generate-primes
    ([max] (generate-primes max (range 2 max) 2))
    ([max candidates n]
        (if (> (* n n) max)
            candidates
            (recur
                max
                (filter
                    #(or (= % n) (not (= (mod % n) 0)))
                    candidates)
                (inc n)))))

(defn strrotl [s]
    (str (clojure.string/join (rest s)) (first s)))

(defn digit-rotations [n]
    ((fn rot-internal [curRot rotsRemaining]
        (if (zero? rotsRemaining)
            `()
            (cons curRot (rot-internal (strrotl curRot) (dec rotsRemaining)))))
    (str n) (count (str n))))

(defn all-digit-rotations-in-set? [n testSet]
    ((fn [rotations]
        (cond
            (zero? (count rotations))
                true
            (contains? testSet (java.lang.Integer/parseInt (first rotations)))
                (recur (rest rotations))
            true
                false))
    (digit-rotations n)))

(defn generate-circular-primes [max]
    (let [primes (set (generate-primes max))]
        (filter (fn [n] (all-digit-rotations-in-set? n primes)) primes)))

(count (generate-circular-primes 1000000))