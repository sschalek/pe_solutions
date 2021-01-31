(defn- iter-perm [v]
  (let [len (count v)
    j (loop [i (- len 2)]
         (cond (= i -1) nil
           (< (v i) (v (inc i))) i
           :else (recur (dec i))))]
    (when j
      (let [vj (v j)
        l (loop [i (dec len)]
        (if (< vj (v i)) i (recur (dec i))))]
    (loop [v (assoc v j (v l) l vj) k (inc j) l (dec len)]
      (if (< k l)
        (recur (assoc v k (v l) l (v k)) (inc k) (dec l))
        v))))))

(defn- vec-lex-permutations [v]
  (when v (cons v (lazy-seq (vec-lex-permutations (iter-perm v))))))

(defn lex-permutations
  "Fast lexicographic permutation generator for a sequence of numbers"
  [c]
  (lazy-seq
   (let [vec-sorted (vec (sort c))]
     (if (zero? (count vec-sorted))
       (list [])
       (vec-lex-permutations vec-sorted)))))

; (defn prime? [n]
;     (if (>= n 2)
;         (reduce (fn [a b] (and a b)) true (map #(> (rem n %) 0) (take-while #(<= (* % %) n) (iterate inc 2))))
;         false))
(defn prime? [n]
    (.isProbablePrime (java.math.BigInteger/valueOf n) 5))

(defn pandigitals
    ([] (flatten (map pandigitals (range 1 10))))
    ([num-digits]
        (map
            #(-> % clojure.string/join java.lang.Integer/parseInt)
            (lex-permutations (range 1 (inc num-digits))))))

(apply max (filter prime? (pandigitals)))