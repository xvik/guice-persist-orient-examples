### Object db setup sample

WARNING: base configuration concepts are covered in [base setup sample](../setup-base) 
and will not be described here in detail

Demo introduce orient object api. It is very similar (in essence) to jpa (hibernate). Object api is a higher level above document api, but document api
could still be used together with object api.

This sample requires only object api, so will work with graph apis excluded:

```java
compile ('ru.vyarus:guice-persist-orient:3.1.1'){
    exclude module: 'orientdb-graphdb'       
}
```

[DbModule](src/main/java/ru/vyarus/guice/persist/orient/examples/module/DbModule.java) uses 
special SchemeInitializer (in document demo it was hand-written): PackageSchemeInitializer.
It is used to update database model according to model classes (see [object mapping docs](https://github.com/xvik/guice-persist-orient#object-scheme-mapping)).

### Demo

[DemoApp](src/main/java/ru/vyarus/guice/persist/orient/examples/DemoApp.java) 
is almost the same as document demo, just using objects instead of ODocument instances.

See [SampleService](src/main/java/ru/vyarus/guice/persist/orient/examples/service/SampleService.java) for object api usage examples.
Note that usage of projection or aggregate function in sql will lead to ODocument instances as a result. 
