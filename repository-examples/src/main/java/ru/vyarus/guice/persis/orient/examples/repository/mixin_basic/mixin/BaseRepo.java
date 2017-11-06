package ru.vyarus.guice.persis.orient.examples.repository.mixin_basic.mixin;

import ru.vyarus.guice.persist.orient.repository.command.query.Query;

import java.util.List;

/**
 * Very simple example of base repository definition. There are a lot of bundled base mixins which implements
 * basic crud operations for all connection types.
 * <p>
 * Not that mixin is not annotated (like other repositories), as it is just an interface.
 *
 * @author Vyacheslav Rusakov
 * @since 06.11.2017
 */
public interface BaseRepo<T> {

    // generic resolved from actual repository
    @Query("select from ${T}")
    List<T> all();

    @Query("select from ${T} where name = ?")
    T findByName(String name);
}
