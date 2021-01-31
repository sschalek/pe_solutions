(defn partial-factorial [n minNum]
  (loop [i n
         accum 1]
    (if (<= i minNum)
      accum
      (recur (dec i) (* i accum)))))

(defn factorial [n]
  (partial-factorial n 0))

(defn get-combination-ways [n r]
  (let [denomDiffFactorial (- n r)
        largeDenom (max r denomDiffFactorial)
        smallDenom (min r denomDiffFactorial)]
  (quot
   (partial-factorial n largeDenom)
   (factorial smallDenom))))

(time
 (count
  (for [n (range 1 (inc 100))
        r (range 1 (inc n))
        :when (try
                (> (get-combination-ways n r) 1000000)
                (catch ArithmeticException e true))]
    [n r])))
