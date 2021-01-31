(defn generate-ordered-permutations [sourceItems n]
    (if (= (count sourceItems) 1)
        (list (seq sourceItems))
        (if (>= n (count sourceItems))
            `()
            (lazy-seq
                (concat
                    (let [orderedItems (sort sourceItems)
                          chosenItem (nth sourceItems n)
                          remainingItems (concat (take n sourceItems) (nthnext sourceItems (+ n 1)))]
                        (map
                            (fn [partialPermutation] (cons chosenItem partialPermutation))
                            (generate-ordered-permutations remainingItems 0)))
                    (generate-ordered-permutations sourceItems (+ n 1)))))))
                    
(nth (generate-ordered-permutations [0 1 2 3 4 5 6 7 8 9] 0) 999999)