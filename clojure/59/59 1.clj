(def frequencyDictionary
  (zipmap
   (filter not-empty
           (clojure.string/split
            (slurp "english_word_frequency.txt")
            #"[0-9]*\.|\r\n"))
   (iterate inc 0)))

(def cipherText
  (mapv
   #(Integer/parseInt (clojure.string/trim-newline %))
   (clojure.string/split
    (slurp "cipher1.txt")
    #",")))

(defn decrypt-text [text key]
  (let [keyNums (if (empty? key) [0] (map int key))]
    (clojure.string/join
     (map #(char (bit-xor (int %1) %2)) text (cycle keyNums)))))

(defn get-word-count [text dictionary]
  (let [textLength (count text)
        maxWordLength (reduce max (map count dictionary))]
    (count (for [start (range 0 (count text))
                 end (range (inc start) (min (+ start maxWordLength 1) (inc textLength)))
                 :let [word (subs text start end)]
                 :when (contains? dictionary word)]
             word))))

(defn find-decryption-key [text dictionary]
  (sort-by first >
           (for [c1 (map char (range (int \a) (inc (int \z))))
                 c2 (map char (range (int \a) (inc (int \z))))
                 c3 (map char (range (int \a) (inc (int \z))))
                 :let [key (clojure.string/join [c1 c2 c3])
                       decryptedText (decrypt-text text key)
                       wordCount (get-word-count decryptedText dictionary)]]
             [wordCount decryptedText])))

(println (first (find-decryption-key cipherText frequencyDictionary)))
(reduce + (map int (second (first (find-decryption-key cipherText frequencyDictionary)))))

(for [c1 (map char (range (int \a) (inc (int \z))))
      c2 (map char (range (int \a) (inc (int \z))))
      c3 (map char (range (int \a) (inc (int \z))))
      :let [key (clojure.string/join [c1 c2 c3])]]
  key)
