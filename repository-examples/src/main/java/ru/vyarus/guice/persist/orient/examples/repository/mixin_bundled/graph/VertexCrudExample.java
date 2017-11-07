package ru.vyarus.guice.persist.orient.examples.repository.mixin_bundled.graph;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicClassProvider;
import com.google.inject.persist.Transactional;
import ru.vyarus.guice.persist.orient.examples.model.VertexModel;
import ru.vyarus.guice.persist.orient.support.repository.mixin.graph.ObjectVertexCrud;

/**
 * Note: this example shows case when object api used together with graph entities. This is quite handy: both
 * vertexes and edges are usual object api entities. The only difference come when new edges must be created
 * or edge related operations performed (this just need to be kept in mind).
 * <p>
 * Vertex objects must be annotated with
 * {@link ru.vyarus.guice.persist.orient.db.scheme.initializer.ext.type.vertex.VertexType} (so scheme initializer
 * could correctly register them as "extends V").
 * <p>
 * Very important to use special crud mixins for graph entities, as there is an important differences
 * when graph api used (graph consistency must be kept and so graph api used in some cases).
 * <p>
 * Note that there are special methods to convert between object and vertex.
 *
 * @author Vyacheslav Rusakov
 * @since 02.11.2017
 */
@Transactional
@ProvidedBy(DynamicClassProvider.class)
public interface VertexCrudExample extends ObjectVertexCrud<VertexModel> {

    // here may be custom repository methods
}
