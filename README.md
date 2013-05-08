# paths

paths is a clojure library for manipulating paths in the abstract.

a path is an ordered set of subcomponents which you might want to
string together via some kind of separator.

At work I was translating paths and urls from one system to another,
and it sucked. So I wrote paths. Hopefully next time it will suck less

## Usage

`[com.thelastcitadel/paths "0.0.1"]`

`foo/bar` is a path turned into a string with `/` as the
separator. You can build the path like so `(append "foo"
"bar")`. `append` appends one path to another. Strings are considered
to be single element paths.

Once you create a path you can turn it into a string using
`as-string`. `as-string` takes a separator and an escaping function.
`(as-string (append "foo" "bar") "/" identity)` results in the string
`foo/bar`.


See tests for more examples

## License

Copyright © 2013 Kevin Downey

Distributed under the Eclipse Public License, the same as Clojure.
