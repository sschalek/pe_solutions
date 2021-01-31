(defn is-leap-year [year]
  (if (zero? (rem year 1000))
    (zero? (rem year 400))
    (zero? (rem year 4))))

(defn days-in-month [year month]
  (if (= month 2)
    (if (is-leap-year year)
      29
      28)
    ({1  31
      3  31
      4  30
      5  31
      6  30
      7  31
      8  31
      9  30
      10 31
      11 30
      12 31} month)))

(defn get-dates-in-range [startYear endYear startDay]
  (map #(hash-map :year (%2 :year)
          :month (%2 :month)
          :date (%2 :date)
          :day %1)
       (iterate #(rem (inc %) 7) startDay)
       (for [year (range startYear endYear)
             month (range 1 13)
             date (range 1 (+ (days-in-month year month) 1))]
         {:year year :month month :date date})))

(count
 (filter #(and
           (zero? (% :day))
           (= (% :date) 1)
           (>= (% :year) 1901))
         (get-dates-in-range 1900 2001 1)))
