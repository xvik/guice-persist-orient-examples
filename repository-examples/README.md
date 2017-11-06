### Repositories usage examples

##### Basics

* [Basic examples](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/basic/BasicsExamples.java)
basic repository crud operation with various connection types
* [Bean examples](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/basic/BeanExamples.java)
repository methods usage inside guice bean
* [Result conversion examples](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/basic/ResultConversionExamples.java)
default allowed result conversions (repository method return types)
* [Query parameters definition examples](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/basic/QueryParametersExamples.java)
various ways to declare and use parameters in query
* [Query extensions examples](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/basic/QueryExtensionsExamples.java)
bundled extensions usage
* [Async query examples](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/basic/AsyncQueryExamples.java)
asynchronous queries examples
* [Function call examples](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/basic/FunctionExamples.java)
functions calling example
* [Script examples](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/basic/ScriptExamples.java)
scripts usage examples

##### Delegates

* [Delegation examples](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/delegate/DelegateExamples.java)
method delegation examples (delegation extensions usage)
* [Class level delegation](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/delegate/ClassDelegationExamples.java)
example of delegation declaration on class level

##### Mixins

* [Mixin examples](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/mixin_basic/MixinExamples.java)
mixins declaration and usage example (generics usage)
* [Delegating mixin example](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/mixin_basic/MixinDelegateExamples.java)
common implementation "pattern" for mixins implemented with delegation 

##### Bundled mixins (CRUD)

* [Document crud](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/mixin_bundled/DocumentCrudExample.java)
example of repository definition for document entity
* [Object crud](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/mixin_bundled/ObjectCrudExample.java)
example of repository definition fro object entity
* [Vertex crud](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/mixin_bundled/graph/VertexCrudExample.java)
example of repository definition for vertex with object mapping 
* [Edge crud*](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/mixin_bundled/graph/EdgeCrudExample.java)
example of repository definition for edge with object mapping (*just an example, barely useful in real life)
* [Vertex with single edge crud](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/mixin_bundled/graph/VertexCrudWithSingleEdgeExample.java)
example of repository definition for vertex together with methods for exact edge type manipulations
* [Vertex with generic edges crud](src/main/java/ru/vyarus/guice/persis/orient/examples/repository/mixin_bundled/graph/VertexCrudWithEdgesExample.java)
example of repository definition for vertex together with generic edge manipulation methods  