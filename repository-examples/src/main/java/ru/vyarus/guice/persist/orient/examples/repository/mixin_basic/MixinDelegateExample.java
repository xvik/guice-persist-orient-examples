package ru.vyarus.guice.persist.orient.examples.repository.mixin_basic;

import ru.vyarus.guice.persist.orient.examples.model.Model;
import ru.vyarus.guice.persist.orient.examples.repository.mixin_basic.delegate.DelegatingMixin;

/**
 * Quite commonly, delegation come at first role for generic mixin implementation. In this case, it is recommended
 * to keep delegate bean abstract and make it implement mixin interface. The drawback is that not all methods
 * will be actually implemented, because of extensions usage (additional parameters), but generally it will tie
 * interface with the implementation which is better for navigation.
 *
 * @author Vyacheslav Rusakov
 * @since 06.11.2017
 */
public interface MixinDelegateExample extends DelegatingMixin<Model> {

    // new methods could be added here as usual
}
