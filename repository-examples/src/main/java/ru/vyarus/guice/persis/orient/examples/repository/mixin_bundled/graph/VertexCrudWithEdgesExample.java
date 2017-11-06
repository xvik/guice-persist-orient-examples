package ru.vyarus.guice.persis.orient.examples.repository.mixin_bundled.graph;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicClassProvider;
import com.google.inject.persist.Transactional;
import ru.vyarus.guice.persis.orient.examples.model.VertexModel;
import ru.vyarus.guice.persist.orient.support.repository.mixin.graph.EdgesSupport;
import ru.vyarus.guice.persist.orient.support.repository.mixin.graph.ObjectVertexCrud;

/**
 * This is extended example for {@link VertexCrudExample}. Here not just crud methods added, but also generic
 * edge manipulation methods.
 * <p>
 * Note that {@link EdgesSupport} could be used directly as repository as universal edges repository.
 *
 * @author Vyacheslav Rusakov
 * @since 06.11.2017
 */
@Transactional
@ProvidedBy(DynamicClassProvider.class)
public interface VertexCrudWithEdgesExample
        // vertex crud + generic edges support
        extends ObjectVertexCrud<VertexModel>, EdgesSupport {

    // here may be custom repository methods
}
