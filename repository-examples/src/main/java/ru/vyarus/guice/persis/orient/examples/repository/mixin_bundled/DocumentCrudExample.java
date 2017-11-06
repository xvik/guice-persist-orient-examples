package ru.vyarus.guice.persis.orient.examples.repository.mixin_bundled;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicClassProvider;
import com.google.inject.persist.Transactional;
import com.orientechnologies.orient.core.record.impl.ODocument;
import ru.vyarus.guice.persis.orient.examples.model.Model;
import ru.vyarus.guice.persist.orient.support.repository.mixin.crud.DocumentCrud;
import ru.vyarus.guice.persist.orient.support.repository.mixin.pagination.Pagination;

/**
 * Example of document connection repository definition with bundled document crud and (optionally) pagination
 * support.
 *
 * @author Vyacheslav Rusakov
 * @since 06.11.2017
 */
@Transactional
@ProvidedBy(DynamicClassProvider.class)
public interface DocumentCrudExample
        // generic is required to substitute placeholders in queries, BUT this may be not real object connection class
        // just class with the same name as required scheme ({@code class Model {}} - empty class used just for name)
        extends DocumentCrud<Model>,
        // pagination mixin example
        Pagination<Model, ODocument> {

    // here may be custom repository methods
}
