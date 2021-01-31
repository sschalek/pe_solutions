(defn square? [n]
  (let [nsqrt (Math/sqrt n)]
    (= nsqrt (Math/floor nsqrt))))

(reduce
 #(if (> (%1 2) (%2 2))
    %1
    %2)
 (mapcat
  #(take 1
         (for [x (iterate inc 1)
               :let [y (Math/sqrt (/ (- 1 (* x x)) (* -1 %)))]
               :when (and (pos? y) (= y (Math/floor y)))]
           [% x (int y)]))
  (filter #(not (square? %)) (range 1 (inc 5)))))

(Math/sqrt (quot (* 19 19) 10))
