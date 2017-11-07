package ru.vyarus.guice.persist.orient.examples.repository.basic;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import ru.vyarus.guice.persist.orient.examples.model.Model;
import ru.vyarus.guice.persist.orient.db.DbType;
import ru.vyarus.guice.persist.orient.repository.command.query.Query;
import ru.vyarus.guice.persist.orient.repository.core.ext.service.result.ext.detach.DetachResult;
import ru.vyarus.guice.persist.orient.repository.core.ext.service.result.ext.off.NoConversion;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Examples of return type cases and allowed conversions.
 * <p>
 * For queries, orient always returns lists or iterators (graph), but result converter could automatically convert
 * it to different type.
 *
 * @author Vyacheslav Rusakov
 * @since 03.11.2017
 */
@Transactional
@ProvidedBy(DynamicSingletonProvider.class)
public interface ResultConversionExamples {

    // result converter is able to convert query result to any option:

    @Query("select from Model")
    List<Model> selectAllList();

    @Query("select from Model")
    Set<Model> selectAllSet();

    @Query("select from Model")
    Model[] selectAllArray();

    @Query("select from Model")
    Iterable<Model> selectAllIterable();

    @Query("select from Model")
    Iterator<Model> selectAllIterator();


    // guava or java 8 Optional could be returned

    @Query("select from Model where name = ?")
    Optional<Model> findByName(String name);

    @Query("select from Model where name = ?")
    com.google.common.base.Optional<Model> findByNameGuavaOptional(String name);


    // custom conversion mechanism could be switched off with an extension (in order to get raw result -
    // exactly what orient query returns)
    @NoConversion
    @Query("select from Model")
    List<Model> selectAllListNoConversion();

    // also, in some (mostly test) cases it is useful to automatically detach returned proxies to pure pojos
    // (which usage does not requires transaction):
    @DetachResult
    @Query("select from Model")
    List<Model> selectAllListOfPojos();


    // orient always returns documents, even if single field is required; result converter
    // could recognize this and "project" returned document to simple value


    // orient returns ODocument with singe field, but it will be recognized and "unwrapped"
    @Query("select count(@rid) from Model")
    int getCount();

    // WARNING: automatic projection will NOT WORK here because it would lead to unnecessary overhead for all
    // queries (check all queries for projection)
    // fixed in 3.3.0
    @Query("select name from Model")
    List<String> getNames();

    // orient will return list of ODocument and result will be "unwrapped" to raw values
    @Query("select name from Model")
    String[] getNamesArray();

    // tricky case: query return list, but as soon as one result expected only first element taken
    // and projection will work on it
    @Query("select name from Model")
    String getOneName();

    // in graph connections Vertex returned instead of document, but overall projection mechanism works the same

    @Query(value = "select name from VertexModel", connection = DbType.GRAPH)
    String[] getGraphNamesArray();

    @Query(value = "select count(@rid) from VertexModel", connection = DbType.GRAPH)
    int getGraphCount();

}
