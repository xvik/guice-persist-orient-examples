package ru.vyarus.guice.persis.orient.examples.repository.basic;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.record.impl.ODocument;
import ru.vyarus.guice.persis.orient.examples.model.FieldDefinition;
import ru.vyarus.guice.persis.orient.examples.model.Model;
import ru.vyarus.guice.persis.orient.examples.model.VertexModel;
import ru.vyarus.guice.persist.orient.repository.command.ext.dynamicparams.DynamicParams;
import ru.vyarus.guice.persist.orient.repository.command.ext.elvar.ElVar;
import ru.vyarus.guice.persist.orient.repository.command.ext.pagination.Limit;
import ru.vyarus.guice.persist.orient.repository.command.ext.pagination.Skip;
import ru.vyarus.guice.persist.orient.repository.command.ext.param.Param;
import ru.vyarus.guice.persist.orient.repository.command.ext.ridelvar.RidElVar;
import ru.vyarus.guice.persist.orient.repository.command.ext.var.Var;
import ru.vyarus.guice.persist.orient.repository.command.query.Query;

import java.util.List;

/**
 * Examples of various ways for parameter definitions.
 *
 * @author Vyacheslav Rusakov
 * @since 03.11.2017
 */
@Transactional
@ProvidedBy(DynamicSingletonProvider.class)
public interface QueryParametersExamples {

    // positional parameters
    @Query("select from Model where name=? and nick=?")
    List<Model> parametersPositional(String name, String nick);

    // named parameters (using both possible annotations)
    @Query("select from Model where name=:name and nick=:nick")
    List<Model> parametersNamed(@Param("name") String name, @Param("nick") String nick);

    // collection could be bound too (native orient support)
    @Query("select from Model where name in ?")
    List<Model> findWithList(List<String> names);


    // EL vars are simple placeholders. Often they help when it is not possible to use orient parameter bindings
    // (because there are many situations when parameters are ignored by orient, but mostly its not in queries).

    // over simplified example to show the essence
    @Query("select from Model where name=${name}")
    List<Model> var(@ElVar("name") String name);

    // allow only pre-defined strings
    @Query("select from Model where ${field} = ?")
    Model fieldVar(@ElVar(value = "field", allowedValues = {"name", "nick"}) String field, String value);

    // using enum instead of manual allowed string definition
    @Query("select from Model where ${field} = ?")
    Model findByEnumField(@ElVar("field") FieldDefinition field, String value);


    // Rid el vars (special type of el var specifically to map rid). Very useful in cases when orient parameters
    // can't be used (due to parser limitations)


    // not secured
    @Query("select from (traverse out from ${id})")
    List<VertexModel> stringRid(@RidElVar("id") String id);

    // better, but not often useful
    @Query("select from (traverse out from ${id})")
    List<VertexModel> oridRid(@RidElVar("id") ORID id);

    // direct mapping from entity
    @Query("select from (traverse out from ${id})")
    List<VertexModel> objectRid(@RidElVar("id") VertexModel id);

    // multiple rids at once (also could be list, iterator, iterable, array)
    @Query("select from (traverse out from ${ids})")
    List<VertexModel> selectVararg(@RidElVar("ids") VertexModel... ids);


    // Dynamic parameters


    @Query("select from Model where name=? and nick=?")
    List<ODocument> positionalList(@DynamicParams List<String> params);

    @Query("select from Model where name=? and nick=?")
    List<ODocument> positionalArray(@DynamicParams String[] params);

    @Query("select from Model where name=? and nick=?")
    List<ODocument> positionalVararg(@DynamicParams String... params);

    // universal query (condition and parameters are prepared outside)
    @Query("select from Model where ${cond}")
    List<ODocument> universalus(@ElVar("cond") String cond, @DynamicParams Object... params);


    // Query variables bonding

    // here list mapped directly as orient query variable (in contrast to elvar this is orient native mechanism)
    @Query("select name from Model where name in $tst")
    String[] list(@Var("tst") List tst);


    // Pagination

    @Query("select from Model where name=? and nick=?")
    List<Model> parametersPaged(String name, String nick, @Skip int start, @Limit int max);
}
