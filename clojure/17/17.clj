(defn integer-to-english-number [x]
    (cond
        (< x 20)
            (case x
                0 "zero"
                1 "one"
                2 "two"
                3 "three"
                4 "four"
                5 "five"
                6 "six"
                7 "seven"
                8 "eight"
                9 "nine"
                10 "ten"
                11 "eleven"
                12 "twelve"
                13 "thirteen"
                14 "fourteen"
                15 "fifteen"
                16 "sixteen"
                17 "seventeen"
                18 "eighteen"
                19 "nineteen")
        (= x 20)
            "twenty"
        (= x 30)
            "thirty"
        (= x 40)
            "forty"
        (= x 50)
            "fifty"
        (= x 60)
            "sixty"
        (= x 70)
            "seventy"
        (= x 80)
            "eighty"
        (= x 90)
            "ninety"
        (< x 100)
            (str (integer-to-english-number (* 10 (int (/ x 10)))) "-" (integer-to-english-number (mod x 10)))
        (< x 1000)
            (if (= (mod x 100) 0)
                (str (integer-to-english-number (/ x 100)) " hundred")
                (str
                    (integer-to-english-number (* (int (/ x 100)) 100))
                    " and "
                    (integer-to-english-number (mod x 100))))
        true
            "one thousand"))
            
(defn letter-count [str]
    (if (= (count str) 0)
        0
        (+
            (letter-count (rest str))
            (if (java.lang.Character/isLetter (first str)) 1 0))))
                    
(reduce + (map letter-count (map integer-to-english-number (range 1 1001))))