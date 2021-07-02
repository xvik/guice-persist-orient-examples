### Basic setup sample

Sample introduce base configuration concepts using bare minimum of guice-persist-orient configuration.

This sample requires only document api (the lowest api for orient), so will work with object and graph apis excluded:

```java
implementation ('ru.vyarus:guice-persist-orient:4.0.0')
//implementation "com.orientechnologies:orientdb-object:3.0.38"
//implementation "com.orientechnologies:orientdb-graphdb:3.0.38"
```

[DbModule](src/main/java/ru/vyarus/guice/persist/orient/examples/module/DbModule.java) class 
is application specific (configured guice-persist-orient for specific case) module. Like `OrientModule` it requires db constraints as parameters to
be able to use it both in application and for tests (with in-memory db).

Custom [scheme initializer](https://github.com/xvik/guice-persist-orient#scheme-initialization) 
[ManualSchemeInitializer](src/main/java/ru/vyarus/guice/persist/orient/examples/module/init/ManualSchemeInitializer.java)
used to init database schema (one table `Sample` with two fields)

Custom [data initializer](https://github.com/xvik/guice-persist-orient#data-initialization) 
[SampleDataInitializer](src/main/java/ru/vyarus/guice/persist/orient/examples/module/init/SampleDataInitializer.java)
used to fill `Sample` table with some test data. This is useful for sample application or for testing purposes. Real application most likely, will not register custom data initializer.

### In-memory

The first demo shows in-memory database initialization (such db exist only in memory and disappear after application shutdown):
[InMemoryDbApp](src/main/java/ru/vyarus/guice/persist/orient/examples/InMemoryDbApp.java)

Demo use [transactional](https://github.com/xvik/guice-persist-orient#unit-of-work-transaction) service 
[SampleService](src/main/java/ru/vyarus/guice/persist/orient/examples/service/SampleService.java)
with basic orient api usage examples.

### Local

The second demo shows local database initialization:
[LocalDbApp](src/main/java/ru/vyarus/guice/persist/orient/examples/LocalDbApp.java)

Local database is stored on disk, so changes performed by demo stayed persisted. The limitation of local mode is single jvm usage (for example, you can't launch two 
application instances simultaneously). Local mode is faster than remote (because of no remote protocol overhead) and its ideal for embedded db mode.

Sample also demonstrates manual transaction definition.

### Test

Sample [spock](http://spockframework.github.io/spock/docs/1.0/index.html) tests prepared to show test setup using in-memory database.

[AbstractTest](src/test/groovy/ru/vyarus/guice/persist/orient/examples/AbstractTest.groovy) 
do all test context setup, so actual tests could be simple: 
[SampleServiceTest](src/test/groovy/ru/vyarus/guice/persist/orient/examples/service/SampleServiceTest.groovy)

