package ru.vyarus.guice.persist.orient.examples.repository.delegate.delegate;

import ru.vyarus.guice.persist.orient.examples.model.Model;
import ru.vyarus.guice.persist.orient.repository.delegate.ext.connection.Connection;
import ru.vyarus.guice.persist.orient.repository.delegate.ext.generic.Generic;
import ru.vyarus.guice.persist.orient.repository.delegate.ext.instance.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Delegation cases showcase.
 *
 * @author Vyacheslav Rusakov
 * @since 06.11.2017
 */
public class SimpleDelegate {

    void simpleCall() {
        // impl does not matter
    }

    void foo() {
        // impl does not matter
    }

    // parameters delegation
    void passParameters(String foo, String bar) {
        // impl does not matter
    }

    // result will be passed through
    List<Model> anyResult() {
        return Collections.emptyList();
    }

    // this is very useful for generic methods implementations (usually something like base dao)
    // Generic is resolved from caller's generic declaration
    void genericUsage(@Generic(value = "T", genericHolder = SimpleGenericHolder.class) Class generic) {
        // impl does not matter
    }

    // connection object, used for repository method execution passed as parameter
    // note: type cold be anything (e.g. ODatabaseInternal or exact connection type)
    void connectionPass(@Connection Object db) {
        // impl does not matter
    }

    // called repository passed as parameter
    // note there may be any type (even exact DelegateExamples)
    void repositoryPass(@Repository Object repository) {
        // impl does not matter
    }
}
