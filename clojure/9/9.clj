(defn pythagorean-triple? [a b c] (= (+ (* a a) (* b b)) (* c c)))

(defn get-pythagorean-triples-upto [maxA maxB maxC]
    (lazy-seq
        (let [next-triple-candidate
              (if (> maxA 1)
                    [(- maxA 1) maxB maxC]
                    (if (> maxB 1)
                        [(- maxB 1) (- maxB 1) maxC]
                        (if (> maxC 1)
                            [(- maxC 1) (- maxC 1) (- maxC 1)])))
               remaining-triples
               (if (= nil next-triple-candidate)
                   (seq [])
                   (apply get-pythagorean-triples-upto next-triple-candidate))]
            (if (pythagorean-triple? maxA maxB maxC)
                (cons [maxA maxB maxC] remaining-triples)
                remaining-triples))))

(filter (fn [triple] (= 1000 (+ (triple 0) (triple 1) (triple 2)))) (get-pythagorean-triples-upto 1000 1000 1000))