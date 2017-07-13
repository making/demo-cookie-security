```
$ curl localhost:8080
{"timestamp":1499921904508,"status":403,"error":"Forbidden","message":"Access Denied","path":"/"}

$ curl localhost:8080 -H "Cookie: uid=foo"
Hello foo
```