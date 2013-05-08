(ns com.thelastcitadel.paths-test
  (:use clojure.test
        com.thelastcitadel.paths))

(deftest a-test
  (is (= "foo/bar" (as-string (append "foo" "bar") "/" identity)))
  (is (= "bar" (as-string (shift-left (append "foo" "bar") 1) "/" identity)))
  (is (= "" (as-string (shift-left (append "foo" "bar") 2) "/" identity)))
  (is (= "" (as-string (shift-left (append "foo" "bar") 3) "/" identity)))
  (is (= "baz" (as-string (ltrim (append (append "foo" "bar") "baz")
                                 (append "foo" "bar"))
                          "/"
                          identity)))
  (is (= "foo/bar/baz" (as-string (ltrim (append (append "foo" "bar") "baz")
                                         "baz")
                                  "/"
                                  identity)))
  (is (= "foo/bar" (as-string (rtrim (append (append "foo" "bar") "baz")
                                     "baz")
                              "/"
                              identity)))
  (is (= "baz" (as-string (leaf (append (append "foo" "bar") "baz"))
                          "/"
                          identity)))
  (is (= "foo/bar" (as-string (up (append (append "foo" "bar") "baz"))
                              "/"
                              identity)))
  (is (= "foo%2Fbar/hello%2Fworld"
         (as-string (up (append (append "foo/bar" "hello/world") "baz"))
                    "/"
                    #(java.net.URLEncoder/encode %))))
  (is (= "foo%2Fbar::hello%2Fworld"
         (as-string (up (append (append "foo/bar" "hello/world") "baz"))
                    "::"
                    #(java.net.URLEncoder/encode %))))
  (is (= "foo/bar" (as-string (as-path "foo/bar" "/" identity) "/" identity)))
  )
