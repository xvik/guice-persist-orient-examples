### Repository setup sample

WARNING: base configuration concepts are covered in [base setup sample](../setup-base) 
and will not be described here in detail

Demo introduce repositories usage. It is a very similar (in essence) to spring-data.

[RepoDbModule](src/main/java/ru/vyarus/guice/persist/orient/examples/module/RepoDbModule.java) uses 
installs RepositoryModule to activate repository features.

### Demo

[RepoDemoApp](src/main/java/ru/vyarus/guice/persist/orient/examples/ObjectDemoApp.java) 
is almost the same as object demo (with few additions to show some repository features), just using repository instead of raw orient api calls.

See [SampleRepository](src/main/java/ru/vyarus/guice/persist/orient/examples/repository/SampleRepository.java) for repository
methods examples. 

### Test

Sample [spock](http://spockframework.github.io/spock/docs/1.0/index.html) tests prepared to show test setup using in-memory database.
 
[SampleRepositoryTest](src/test/groovy/ru/vyarus/guice/persist/orient/examples/repository/SampleRepositoryTest.groovy)