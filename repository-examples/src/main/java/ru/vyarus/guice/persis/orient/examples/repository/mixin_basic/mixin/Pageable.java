package ru.vyarus.guice.persis.orient.examples.repository.mixin_basic.mixin;

import ru.vyarus.guice.persist.orient.repository.command.ext.pagination.Limit;
import ru.vyarus.guice.persist.orient.repository.command.ext.pagination.Skip;
import ru.vyarus.guice.persist.orient.repository.command.query.Query;

import java.util.List;

/**
 * Example of pagination mixin (behaviour mixin).
 * Real pagination mixin is bundles
 * (see {@link ru.vyarus.guice.persist.orient.support.repository.mixin.pagination.Pagination})
 *
 * @author Vyacheslav Rusakov
 * @since 06.11.2017
 */
public interface Pageable<T> {

    // generic query with pagination
    @Query("select from ${T}")
    List<T> getPage(@Skip int skip, @Limit int limit);
}
