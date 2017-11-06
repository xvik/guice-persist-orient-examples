package ru.vyarus.guice.persis.orient.examples.repository.mixin_basic;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import ru.vyarus.guice.persis.orient.examples.model.Model;
import ru.vyarus.guice.persis.orient.examples.model.VertexModel;
import ru.vyarus.guice.persis.orient.examples.repository.mixin_basic.mixin.BaseRepo;
import ru.vyarus.guice.persis.orient.examples.repository.mixin_basic.mixin.Pageable;

/**
 * Mixins allows generification of logic. Most common example is "base dao" with generified queries
 * (type of class is resolved from repository generic: {@code MyRepo extends BaseRepo<Model>}).
 * Another example is generified behaviour parts like pagination queries (e.g.
 * {@code MyRepo extends Pagination<Model>}). Even more usages are possible by combining mixins with delegation.
 * <p>
 *  Mixins can build hierarchies of any depth (e.g. repository may use mixin which use other mixins).
 *
 * @author Vyacheslav Rusakov
 * @since 02.11.2017
 */
@Transactional
@ProvidedBy(DynamicSingletonProvider.class)
public interface MixinExamples extends BaseRepo<Model>, Pageable<VertexModel> {

    // all repository methods come from mixins
    // new methods could be added here as usual
}
