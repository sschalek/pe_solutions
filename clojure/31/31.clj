(def coinValues [1 2 5 10 20 50 100 200])

(defn get-coin-combination-count-for [targetValue coins]
  (cond
   (empty? coins) 0
   (< targetValue 0) 0
   (zero? targetValue) 1
   :else
   (let [descendingCoins (reverse (sort coins))]
     (+ (get-coin-combination-count-for (- targetValue (reduce max descendingCoins)) descendingCoins)
        (get-coin-combination-count-for targetValue (rest descendingCoins))))))

(time (get-coin-combination-count-for 200 coinValues))
