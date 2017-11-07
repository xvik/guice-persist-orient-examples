package ru.vyarus.guice.persist.orient.examples.repository.delegate;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import ru.vyarus.guice.persist.orient.examples.model.Model;
import ru.vyarus.guice.persist.orient.examples.repository.delegate.delegate.SimpleDelegate;
import ru.vyarus.guice.persist.orient.examples.repository.delegate.delegate.SimpleGenericHolder;
import ru.vyarus.guice.persist.orient.db.DbType;
import ru.vyarus.guice.persist.orient.repository.delegate.Delegate;

import java.util.List;

/**
 * Delegate allows redirection of method execution to some other method. This is very useful because this way
 * we can call delegate method with different arguments (providing some delegating method context information).
 * This allows writing very generic delegate methods and, from the other side, very simple repository
 * methods.
 * <p>
 * There is actually no limitations fof the delegate object: it could be pure bean or other repository
 * (but in practice it is more convenient to use repositories).
 *
 * @author Vyacheslav Rusakov
 * @since 06.11.2017
 */
@Transactional
@ProvidedBy(DynamicSingletonProvider.class)
public interface DelegateExamples
        // here this is used only to show how to bypass generic (oversimplified example)
        extends SimpleGenericHolder<Model> {

    // on method call it will lookup bean in guice context and call it's simpleCall() method (see method search rules
    // in javadoc)
    // also, note that annotation could be placed at the class level to delegate all calls (useful for mixins)
    @Delegate(SimpleDelegate.class)
    void simpleCall();

    // when it is impossible to select method automatically, it could be directly specified
    @Delegate(value = SimpleDelegate.class, method = "foo")
    void simpleCallFoo();

    // any parameters could be used (caller method may have more parameters if it use extensions (see below))
    @Delegate(SimpleDelegate.class)
    void passParameters(String foo, String bar);

    // any result type could be used (delegate result wil pass through)
    @Delegate(SimpleDelegate.class)
    List<Model> anyResult();



    // the most power comes to delegates with an extensions

    /**
     * Delegate could use generic declared on caller class.
     * See {@link SimpleDelegate#genericUsage(Class)}.
     */
    @Delegate(SimpleDelegate.class)
    void genericUsage();

    /**
     * Connection object, used for repository method execution could be passed as delegate parameter.
     * This is handy for multiple db types support in method (e.g. document and object share common base class)
     * or simply to avoid provider injection in delegate.
     * See {@link SimpleDelegate#connectionPass(Object)}.
     */
    @Delegate(SimpleDelegate.class)
    void connectionPass();

    // used type of connection could be altered with a hint
    @Delegate(value = SimpleDelegate.class, method = "connectionPass", connection = DbType.OBJECT)
    void connectionPassAltered();


    /**
     * Caller repository itself passes as parameter. This is very powerful for "abstract daos", when multiple
     * repositories share common interface.
     */
    @Delegate(SimpleDelegate.class)
    void repositoryPass();

}
