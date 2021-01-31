(defn generate-primes
    ([max] (generate-primes max (range 2 max) 2))
    ([max candidates n]
        (if (> (* n n) max)
            candidates
            (recur max (filter #(or (= % n) (not (= (mod % n) 0))) candidates) (inc n)))))

(defn prime? [n]
    (some #(= n %) (generate-primes (inc n))))

(defn choices [colls]
    (if (every? seq colls)
        (for [item (map first colls)
              sub-choice (choices (map rest colls))]
            (cons item sub-choice))
        '(())))

(defn generate-coef-pairs [min max]
    (choices (map (fn [n] [n n]) (range min max))))

(defn generate-quadratic-value [a b n]
    (+ (* n n) (* a n) b))

(defn generate-quadratic-values [a b]
    (map (fn [n] (generate-quadratic-value a b n) (iterate inc 0))))

(defn generate-prime-quadratic-values [a b n]
    (let [val (generate-quadratic-value a b n)]
        (if (prime? val)
            (cons val (generate-prime-quadratic-values a b (inc n)))
            `())))

(reduce
    (fn [curItem res]
        (if (> (second curItem) (second res))
            curItem
            res))
    (map
        (fn
            [coefs]
            [coefs (count (generate-prime-quadratic-values (first coefs) (second coefs) 0))])
        (generate-coef-pairs -999 1000)))