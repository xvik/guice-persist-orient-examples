package ru.vyarus.guice.persist.orient.examples.repository.mixin_bundled;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicClassProvider;
import com.google.inject.persist.Transactional;
import ru.vyarus.guice.persist.orient.examples.model.Model;
import ru.vyarus.guice.persist.orient.support.repository.mixin.crud.ObjectCrud;
import ru.vyarus.guice.persist.orient.support.repository.mixin.pagination.Pagination;

/**
 * Example of object connection repository definition with bundled object crud and (optionally) pagination
 * support.
 *
 * @author Vyacheslav Rusakov
 * @since 02.11.2017
 */
@Transactional
@ProvidedBy(DynamicClassProvider.class)
public interface ObjectCrudExample extends ObjectCrud<Model>,
        // optional (pagination mixin usage example)
        Pagination<Model, Model> {

    /// here may be custom repository methods
}
