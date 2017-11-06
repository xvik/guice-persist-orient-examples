package ru.vyarus.guice.persis.orient.examples.repository.mixin_bundled.graph;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicClassProvider;
import com.google.inject.persist.Transactional;
import ru.vyarus.guice.persis.orient.examples.model.EdgeModel;
import ru.vyarus.guice.persis.orient.examples.model.VertexModel;
import ru.vyarus.guice.persist.orient.support.repository.mixin.graph.EdgeTypeSupport;
import ru.vyarus.guice.persist.orient.support.repository.mixin.graph.ObjectVertexCrud;

/**
 * This is extended example for {@link VertexCrudExample}. Here not just crud methods added, but also single edge
 * type manipulation methods.
 * <p>
 * Unfortunately, in real life it is quite rare case, when only one edge used with vertex and only in one direction.
 * Anyway, if it's your case then {@link EdgeTypeSupport} provide type safe methods for edge manipulation
 * (otherwise use generic {@link ru.vyarus.guice.persist.orient.support.repository.mixin.graph.EdgesSupport}).
 *
 * @author Vyacheslav Rusakov
 * @since 06.11.2017
 */
@Transactional
@ProvidedBy(DynamicClassProvider.class)
public interface VertexCrudWithSingleEdgeExample
        // vertex crud + single edge type support
        extends ObjectVertexCrud<VertexModel>, EdgeTypeSupport<EdgeModel, VertexModel, VertexModel> {

    // here may be custom repository methods
}
