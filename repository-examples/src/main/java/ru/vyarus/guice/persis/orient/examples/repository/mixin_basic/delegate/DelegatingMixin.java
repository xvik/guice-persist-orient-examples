package ru.vyarus.guice.persis.orient.examples.repository.mixin_basic.delegate;

import ru.vyarus.guice.persist.orient.repository.delegate.Delegate;

/**
 * Mixin delegates all methods.
 *
 * @author Vyacheslav Rusakov
 * @since 06.11.2017
 */
@Delegate(DelegatingMixinBean.class)
public interface DelegatingMixin<T> {

    void directCall();

    void withExtension();
}
