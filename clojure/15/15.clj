(def get-number-of-routes-helper
  (memoize (fn [width height x y]
    (if (and (= x width) (= y height))
      1
      (+ (if (< x width) (get-number-of-routes-helper width height (+ x 1) y) 0)
         (if (< y height) (get-number-of-routes-helper width height x (+ y 1)) 0))))))

(defn get-number-of-routes [width height]
  (get-number-of-routes-helper width height 0 0))

(print (get-number-of-routes 20 20))
