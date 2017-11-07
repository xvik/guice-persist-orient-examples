package ru.vyarus.guice.persist.orient.examples.repository.basic;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import ru.vyarus.guice.persist.orient.examples.model.Model;
import ru.vyarus.guice.persist.orient.db.DbType;
import ru.vyarus.guice.persist.orient.repository.command.ext.param.Param;
import ru.vyarus.guice.persist.orient.repository.command.query.Query;

import java.util.List;

/**
 * Repository shows basic CRUD operations for all connection types.
 * Note that you can work with the same record as document, object or graph entity (if record class supports it)
 * and most of the time it does not matter what api is actually used.
 * <p>
 * For each method, required connection type will be determined by expected result. For example, when registered
 * entity required (like Model) then object connection used. If vertex or edge returned then graph connection is used.
 * Only return type is important because connection type is only affect record wrapping (automatic conversion of
 * raw document to object or vertex). The only exception is graph, when create and delete are also check graph
 * integrity and so graph connection should be used (its not illegal to use different connection type if you know
 * what you're doing).
 *
 * @author Vyacheslav Rusakov
 * @see <a href="http://www.orientechnologies.com/docs/last/orientdb.wiki/SQL.html">docs</a>
 * @since 02.11.2017
 */
@Transactional
@ProvidedBy(DynamicSingletonProvider.class)
public interface BasicsExamples {

    // basic CRUD operations for object

    // object connection used, result returned "as is" (no conversion)
    @Query("select from Model")
    List<Model> selectAll();

    // object connection, orient will return list, but converter will took only the first element
    @Query("select from Model where @rid=?")
    Model findById(String id);

    // the same as previous, just parsed orid object used
    @Query("select from Model where @rid=?")
    Model findByRid(ORID id);

    // document connection as no result mapping required
    @Query("update Model set name = ? where name = ?")
    int updateName(String newName, String oldName);

    // object connection
    @Query("insert into Model (name) values(:name)")
    Model create(@Param("name") String name);

    // document connection
    @Query("delete from Model where name = ?")
    int delete(String name);


    // documents may be used


    // the same as selectAll but return documents instead of pojos
    @Query("select from Model")
    List<ODocument> selectAllDoc();

    // the same as create but returns document
    @Query("insert into Model (name) values(:name)")
    ODocument createDoc(@Param("name") String name);


    // and the same could work for graph
    // note that different model used (VertexModel) as Model don't extend V and so can't be used in graph api

    @Query("select from VertexModel")
    List<Vertex> selectAllVertex();

    // OrientVertex may be used directly
    @Query("insert into VertexModel (name) values(:name)")
    OrientVertex createVertex(@Param("name") String name);

    // force graph connection to apply graph integrity checks
    @Query(value = "delete from VertexModel where name = ?", connection = DbType.GRAPH)
    int deleteVertex(String name);
}
