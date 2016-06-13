### Graph db setup sample

WARNING: base configuration concepts are covered in [base setup sample](../setup-base) 
and will not be described here in detail

Demo introduce orient graph api. Graph api is a a higher level above document api, but document api
could still be used together with graph api (actually object api could be used too, but it would be shown in next sample).

This sample requires only graph api, so will work with object apis excluded:

```java
compile ('ru.vyarus:guice-persist-orient:3.1.1'){
    exclude module: 'orientdb-object'       
}
```

[GraphDbModule](src/main/java/ru/vyarus/guice/persist/orient/examples/module/GraphDbModule.java) defines
scheme initialized and data initializer (by analogy with document demo). 

Note that [scheme initializer](src/main/java/ru/vyarus/guice/persist/orient/examples/module/init/ManualSchemeInitializer.java)
also creates edge type (belongsTo). This is not required and edge type could be created when its instance will be created 
first time, but with warning message (so it's better to define edges manually, at least in production code).

Prefer using `OrientBaseGraph` instead of `OrientGraph` or `OrientGraphNoTx` because base graph suitable for both cases
(transactional or non transactional (NOTX mode) execution). Moreover, guice-persist-orient handles all transactional
 staff so there is no need for specific transactional methods.

### Demo

[GraphDemoApp](src/main/java/ru/vyarus/guice/persist/orient/examples/GraphDemoApp.java) 
is almost the same as previous demos, but initial 10 records are connected now with edges (kind of reverse linked list):

```
Sample0 <-- belongsTo -- Sample1 <-- belongsTo -- Sample2 ...
```

See [SampleService](src/main/java/ru/vyarus/guice/persist/orient/examples/service/SampleService.java) for graph api usage examples.
Pay attention to `selectLast`, `getParent`, `getChildren` methods which use edges in sql queries.

### Test

Sample [spock](http://spockframework.github.io/spock/docs/1.0/index.html) tests prepared to show test setup using in-memory database.

Note that [AbstractTest](src/test/groovy/ru/vyarus/guice/persist/orient/examples/AbstractTest.groovy) cleanup method
forces orient storages close. This is important to discard state between test: when storage not closed, database remain in
memory, only connection pools are closed by guice-persist-orient.

Probably this will not be enough for all cases, but should cover most usages (there might be problems with some internal caches).
You can always do `shutdown()` + `startup()` in cleanup instead of just storages close.
 
[SampleServiceTest](src/test/groovy/ru/vyarus/guice/persist/orient/examples/service/SampleServiceTest.groovy)
