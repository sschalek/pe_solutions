(defn get-pythagorean-triples-for-perimeter [p]
  (for [a (range 1 p)
        b (range a (- p a))
        :let [c (Math/sqrt (+ (* a a) (* b b)))]
        :when (= (Math/floor c) c)
        :when (= (+ a b (int c)) p)]
    [a b (int c)]))
(get-pythagorean-triples-for-perimeter 120)

(sort-by #(% :count) >
         (filter #(> (% :count) 0)
                 (map #(hash-map :p %
                                 :count (count (get-pythagorean-triples-for-perimeter %)))
                      (range 1 1001))))
