package ru.vyarus.guice.persis.orient.examples.repository.basic;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import ru.vyarus.guice.persis.orient.examples.model.Model;
import ru.vyarus.guice.persist.orient.repository.command.query.Query;

import java.util.List;

/**
 * Repository methods could be used inside any guice bean.
 * Or repository itself may use abstract class to implement some methods directly.
 *
 * @author Vyacheslav Rusakov
 * @since 03.11.2017
 */
@Transactional
// NOTE: this is still required only because of abstract class.. if abstract methods (like queey() ) would  not be used
// (only publicQuery() ) then it will work without provider.
@ProvidedBy(DynamicSingletonProvider.class)
public abstract class BeanExamples {

    // "internal" bean query
    @Query("select from Model")
    protected abstract List<Model> query();


    // it is not necessary to use abstract method
    @Query("select from Model")
    public List<Model> publicQuery() {
        return null;
    };

    public void doSomething() {
        // example of internal bean query usage
        List<Model> res = query();
        //...
    }
}
