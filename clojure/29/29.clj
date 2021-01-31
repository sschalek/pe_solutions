(defn choices [colls]
    (if (every? seq colls)
        (for [item (map first colls)
              sub-choice (choices (map rest colls))]
            (cons item sub-choice))
        '(())))

(count (set (map
    #(java.lang.Math/pow (first %) (second %))
    (choices (map (fn [n] [n n]) (range 2 101))))))