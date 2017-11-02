package ru.vyarus.guice.persist.orient.examples.repository;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import com.orientechnologies.orient.core.record.impl.ODocument;
import ru.vyarus.guice.persist.orient.examples.model.Sample;
import ru.vyarus.guice.persist.orient.repository.command.ext.param.Param;
import ru.vyarus.guice.persist.orient.repository.command.query.Query;
import ru.vyarus.guice.persist.orient.repository.core.ext.service.result.ext.detach.DetachResult;

import java.util.List;

/**
 * Repository. Note that {@link Transactional} is not required, but defined to be able to call repository
 * methods directly (creating short transactions).
 * <p>
 * Repository may use multiple connection types: required connection type is detected from required result type.
 * So single repository may contain methods for different connection types.
 * <p>
 * This {@code @ProvidedBy(DynamicSingletonProvider.class)} is important because otherwise guice will fail to
 * initialize (abstract types can't be used as beans). Provider creates proxy class, which guice extends to
 * apply aop (all method annotations (annotations annotated with
 * {@link ru.vyarus.guice.persist.orient.repository.core.spi.method.RepositoryMethod}) are handled with guice aop).
 *
 * @author Vyacheslav Rusakov
 * @since 02.11.2017
 */
@Transactional
@ProvidedBy(DynamicSingletonProvider.class)
public interface SampleRepository {

    /**
     * Document connection will be used and resulted ODocument will be projected to single value
     * by {@link ru.vyarus.guice.persist.orient.repository.core.ext.service.result.converter.ResultConverter}.
     *
     * @return count
     */
    @Query("select count(*) from Sample")
    long count();

    /**
     * Object connection will be used as result object is registered object type. Actual query will return list
     * but {@link ru.vyarus.guice.persist.orient.repository.core.ext.service.result.converter.ResultConverter}
     * will convert it to single element.
     *
     * @return first entity
     */
    @Query("select from Sample order by name limit 1")
    Sample first();

    /**
     * @param name name to search by
     * @return found document
     */
    @Query("select from Sample where name = :name")
    ODocument findDocumentByName(@Param("name") String name);

    /**
     * Update query performed under document connection. Parameter binding by position.
     * Return type could be void if its not required.
     *
     * @param to   new name
     * @param from old name (to search by)
     * @return number of updated records
     */
    @Query("update Sample set name = ? where name = ?")
    int updateName(String to, String from);

    /**
     * Normally, object connection returns proxies, which can't be used without underlined connection.
     * {@link DetachResult} extension will perform detach on all results and the final result will be pure pojos.
     *
     * @return list of pure pojo entities
     */
    @DetachResult
    @Query("select from Sample")
    List<Sample> selectDetached();

    /**
     * Object connection selected. Parameter bound by name.
     * Note that limit 1 will be applied automatically to avoid potential overhead (if there are more
     * matching entities).
     *
     * @param name name to search by
     * @return matched entity or null
     */
    @DetachResult
    @Query("select from Sample where name = :name")
    Sample findByNameDetached(@Param("name") String name);
}
