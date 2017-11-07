package ru.vyarus.guice.persist.orient.examples.repository.basic;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import ru.vyarus.guice.persist.orient.repository.command.script.Script;

/**
 * Script examples. Scripts looks very like functions (which are very like stored procedures), but
 * their main intent is to be able to execute multiple queries at once (without redundant network
 * round trips).
 *
 * @author Vyacheslav Rusakov
 * @since 03.11.2017
 * @see <a href="http://www.orientechnologies.com/docs/last/orientdb.wiki/SQL-batch.html">docs</a>
 */
@Transactional
@ProvidedBy(DynamicSingletonProvider.class)
public interface ScriptExamples {

    @Script("let model = select from Model where name='first'"
            + "return $model.nick")
    String nick();

    @Script("update model set name = ?")
    void positional(String name);

    @Script("begin"
            + "  let model = select from Model where name='first'"
            + "commit retry 100"
            + "return $model.nick")
    String nickUnderTransaction();


    @Script(language = "javascript", value =
            "for( i = 0; i < 1000; i++ ){"
                    + "  db.command('insert into Model(name) values (\"test'+i+'\")');"
                    + "}")
    void jsScript();
}
