package ru.vyarus.guice.persis.orient.examples.repository.mixin_basic.delegate;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import ru.vyarus.guice.persist.orient.repository.delegate.ext.generic.Generic;

/**
 * Using the same provider as repositories to be able to keep bean abstract.
 *
 * @author Vyacheslav Rusakov
 * @since 06.11.2017
 */
@ProvidedBy(DynamicSingletonProvider.class)
public abstract class DelegatingMixinBean implements DelegatingMixin {

    @Override
    public void directCall() {
        // method directly connected with interface (no extensions)
    }

    // direct implementation is impossible, but anyway it would be easy to spot delegation by looking at
    // overall class
    // NOTE in this case it is not required to specify target interface as bean already implement it
    public void withExtension(@Generic("T") Class type) {

    }
}
