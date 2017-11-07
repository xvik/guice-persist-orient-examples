package ru.vyarus.guice.persist.orient.examples.repository.mixin_bundled.graph;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import ru.vyarus.guice.persist.orient.examples.model.EdgeModel;
import ru.vyarus.guice.persist.orient.examples.model.VertexModel;
import ru.vyarus.guice.persist.orient.support.repository.mixin.graph.EdgeTypeSupport;

/**
 * Note: this example shows case when object api used together with graph entities. This is quite handy: both
 * vertexes and edges are usual object api entities. The only difference come when new edges must be created
 * or edge related operations performed (this just need to be kept in mind).
 * <p>
 * Edge objects must be annotated with
 * {@link ru.vyarus.guice.persist.orient.db.scheme.initializer.ext.type.edge.EdgeType} (so scheme initializer
 * could correctly register them as "extends E").
 * <p>
 * Very important to use special crud mixins for graph entities, as there is an important differences
 * when graph api used (graph consistency must be kept and so graph api used in some cases).
 * <p>
 * Note that there are special methods to convert between object and edge.
 *
 * @author Vyacheslav Rusakov
 * @since 02.11.2017
 */
@Transactional
@ProvidedBy(DynamicSingletonProvider.class)
public interface EdgeCrudExample
        // crud for edge EdgeModel which connects VertexModel and VertexModel (not necessary to connect the same type)
        extends EdgeTypeSupport<EdgeModel, VertexModel, VertexModel> {

    // IMPORTANT in many cases it is not so handful to create separate edge repositories because quite often edges
    // manipulation appear in context of vertex modification, so it is more logical to  add edges support for existing
    // vertex repository (by applying EdgeTypeSupport or generic EdgesSupport mixin)


    // here may be custom repository methods
}
