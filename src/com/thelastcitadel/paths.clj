(ns com.thelastcitadel.paths)

(defprotocol Path
  (as-string [p separator escape-fn])
  (append [p p2])
  (ltrim [p p2])
  (rtrim [p p2])
  (components [p])
  (up [p])
  (leaf [p])
  (shift-left [p n]))

(defrecord APath [the-components]
  Path
  (as-string [_ separator escape-fn]
    (apply str (interpose separator (map escape-fn the-components))))
  (append [_ s]
    (->APath (into the-components (components s))))
  (ltrim [p1 p2]
    (loop [np1 (components p1)
           np2 (components p2)]
      (if (empty? np2)
        (->APath (vec np1))
        (if (= (first np1)
               (first np2))
          (recur (rest np1)
                 (rest np2))
          p1))))
  (rtrim [p1 p2]
    (loop [np1 (reverse (components p1))
           np2 (reverse (components p2))]
      (if (empty? np2)
        (->APath (vec (reverse np1)))
        (if (= (first np1)
               (first np2))
          (recur (rest np1)
                 (rest np2))
          p1))))
  (components [_]
    (seq the-components))
  (up [p]
    (if (empty? the-components)
      (throw (Exception. "cannot go further up"))
      (->APath (pop the-components))))
  (leaf [p]
    (->APath [(last the-components)]))
  (shift-left [p n]
    (->APath (vec (drop n the-components)))))

(defn path [x]
  (if (instance? APath x)
    x
    (->APath [x])))

(defn as-path [string separator unescape-fn]
  (->APath (mapv unescape-fn (.split string separator))))

(extend-protocol Path
  String
  (as-string [p separator escape-fn]
    (escape-fn p))
  (append [p p2]
    (append (path p) p2))
  (ltrim [p p2]
    (ltrim (path p) p2))
  (rtrim [p p2]
    (rtrim (path p) p2))
  (components [p]
    [p])
  (leaf [p]
    p))
