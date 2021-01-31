;Triangle   Tn=n(n+1)/2   1, 3, 6, 10, 15, ...
;Pentagonal   Pn=n(3n−1)/2   1, 5, 12, 22, 35, ...
;Hexagonal   Hn=n(2n−1)   1, 6, 15, 28, 45, ...

(defn get-triangle-num [n]
  (quot (* n (+ n 1)) 2))

(defn get-pentagonal-num [n]
  (quot (* n (- (* 3 n) 1)) 2))

(defn get-hexagonal-num [n]
  (* n (- (* 2 n) 1)))

(time (loop [ti 1
             pi 1
             hi 1]
        (let [t (get-triangle-num ti)
              p (get-pentagonal-num pi)
              h (get-hexagonal-num hi)]
          (if (and (= t p h) (> t 40755))
            t
            (cond
             (or (< t p) (< t h))
             (recur (inc ti) pi hi)
             (or (< p t) (< p h))
             (recur ti (inc pi) hi)
             :else
             (recur ti pi (inc hi)))))))
