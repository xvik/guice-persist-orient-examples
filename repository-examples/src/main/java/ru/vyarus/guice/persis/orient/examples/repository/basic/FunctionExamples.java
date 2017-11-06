package ru.vyarus.guice.persis.orient.examples.repository.basic;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import ru.vyarus.guice.persis.orient.examples.model.Model;
import ru.vyarus.guice.persist.orient.repository.command.ext.elvar.ElVar;
import ru.vyarus.guice.persist.orient.repository.command.function.Function;
import ru.vyarus.guice.persist.orient.repository.command.query.Query;

import java.util.List;

/**
 * Functions are orient stored procedures.
 *
 * @author Vyacheslav Rusakov
 * @see <a href="http://www.orientechnologies.com/docs/last/orientdb.wiki/Functions.html">docs</a>
 * @since 03.11.2017
 */
@Transactional
@ProvidedBy(DynamicSingletonProvider.class)
public interface FunctionExamples {

    // just en example of function creation (normally it would not be created like this)
    @Query("CREATE FUNCTION function1 \"select from Model\" LANGUAGE SQL")
    void createFunction();

    // just en example of function creation (normally it would not be created like this)
    @Query("CREATE FUNCTION function2 \"select from Model where name = ?\" LANGUAGE SQL")
    void createFunctionWithParameters();

    // function call
    @Function("function1")
    List<Model> function();

    // dynamic function name
    @Function("func${name}")
    List<Model> functionWithPlaceholder(@ElVar("name") String name);

    // function call with parameters
    @Function("function2")
    List<Model> function(String name);
}
