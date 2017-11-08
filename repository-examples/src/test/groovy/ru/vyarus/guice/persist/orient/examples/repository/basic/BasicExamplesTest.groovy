package ru.vyarus.guice.persist.orient.examples.repository.basic

import com.orientechnologies.orient.core.id.ORecordId
import com.orientechnologies.orient.core.record.impl.ODocument
import com.tinkerpop.blueprints.impls.orient.OrientVertex
import ru.vyarus.guice.persist.orient.db.transaction.template.SpecificTxAction
import ru.vyarus.guice.persist.orient.examples.AbstractTest
import ru.vyarus.guice.persist.orient.examples.model.Model

import javax.inject.Inject

/**
 * @author Vyacheslav Rusakov
 * @since 08.11.2017
 */
class BasicExamplesTest extends AbstractTest {

    @Inject
    BasicsExamples repository

    def "Check basic object cases"() {

        when: "creating entity"
        def res = repository.create("sample")
        then: "created"
        res != null

        when: "get to by detaching object"
        String id = context.doInTransaction({ db -> res.id } as SpecificTxAction)
        then: "id resolved"
        id != null

        when: "search by id"
        res = repository.findById(id)
        then: "found"
        res != null

        when: "search by rid"
        res = repository.findByRid(new ORecordId(id))
        then: "found"
        res != null

        when: "update name"
        res = repository.updateName('test', 'sample')
        then: "updated"
        res == 1

        when: "select all"
        res = repository.selectAll()
        then: "found"
        res.size() == 1
        res.first() instanceof Model

        when: "remove record"
        res = repository.delete('test')
        then: "removed"
        res == 1
        repository.selectAll().isEmpty()
    }

    def "Check basic document cases"() {

        when: "create doc"
        def res = repository.createDoc('sample')
        then: "created"
        res.identity != null

        when: "select all docs"
        res = repository.selectAllDoc()
        then: "selected"
        res.size() == 1
        res.first() instanceof ODocument
    }

    def "Check basic graph cases"() {

        when: "create vertex"
        def res = repository.createVertex('sample')
        then: "created"
        res.identity != null

        when: "select all as vertexes"
        res = repository.selectAllVertex()
        then: "selected"
        res.size() == 1
        res.first() instanceof OrientVertex

        when: "delete under graph connection"
        res = repository.deleteVertex('sample')
        then: "deleted"
        res == 1
    }
}
