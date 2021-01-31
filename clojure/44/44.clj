(ns p44.core
  (:require [clojure.math.numeric-tower :as math]))

(defn get-pentagonal [n]
  (bit-shift-right
   (*
    n
    (-
     (* 3 n)
     1))
   1))

(def pentagonals (map get-pentagonal (iterate inc 1)))

(defn pentagonal? [x]
  (let [rootRemainder (math/exact-integer-sqrt (+ 1 (* 24 x)))]
    (if (zero? (second rootRemainder))
      (zero? (rem (+ 1 (first rootRemainder)) 6))
      false)))

(first
      (for [a pentagonals
            b (take-while #(> a %) pentagonals)
            :when (pentagonal? (+ a b))
            :when (pentagonal? (- a b))]
        (- a b)))
