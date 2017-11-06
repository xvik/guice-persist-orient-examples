package ru.vyarus.guice.persis.orient.examples.repository.delegate;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import ru.vyarus.guice.persis.orient.examples.model.Model;
import ru.vyarus.guice.persis.orient.examples.repository.delegate.delegate.SimpleDelegate;
import ru.vyarus.guice.persist.orient.repository.command.query.Query;
import ru.vyarus.guice.persist.orient.repository.delegate.Delegate;

import java.util.List;

/**
 * This example just shows that when delegation is applied to class then all methods become delegates.
 * But if method is directly annotated it overrides class declaration.
 *
 * @author Vyacheslav Rusakov
 * @since 06.11.2017
 */
@Transactional
@ProvidedBy(DynamicSingletonProvider.class)
@Delegate(SimpleDelegate.class)
public interface ClassDelegationExample {

    // delegated call
    void simpleCall();

    // but direct method annotation usage override delegation
    @Query("select from Model")
    List<Model> query();
}
