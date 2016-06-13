package ru.vyarus.guice.persist.orient.examples.service

import com.google.inject.Inject
import com.tinkerpop.blueprints.Vertex
import ru.vyarus.guice.persist.orient.examples.AbstractTest

/**
 * @author Vyacheslav Rusakov
 * @since 14.06.2016
 */
class SampleServiceTest extends AbstractTest {

    @Inject
    SampleService service

    def "Check methods"() {

        when: "lookup count"
        long cnt = service.count()
        then: "ok"
        cnt == 10

        when: "select last"
        Vertex rec = service.selectLast()
        then: "found"
        rec.getProperty("name") == 'Sample9'

        when: "search by name"
        rec = service.findByName(rec.<String>getProperty("name"))
        then: "found"
        rec

        when: "replace name"
        service.replaceName(rec.<String>getProperty("name"), "test")
        then: "renamed in db"
        service.findByName("test")
    }

    def "Check parent-child logic"() {

        when: "checking parent"
        Vertex rec = service.selectLast()
        Vertex parent = service.getParent(rec.<String>getProperty("name"))
        then: "parent found"
        parent.getProperty("name") == 'Sample8'

        when: "checking children"
        List<Vertex> children = service.getChildren(parent.<String>getProperty("name"))
        then: "one child found"
        children.size() == 1
        children[0].getProperty("name") == 'Sample9'

        when: "Adding new child"
        Vertex child = service.addChild(parent, 'Child', 12)
        then: "child added"
        child.getProperty("name") == 'Child'
        service.findByName('Child')

        when: "checking children after addition"
        children = service.getChildren(parent.<String>getProperty("name"))
        then: "two children found"
        children.size() == 2
        children.collect {it.getProperty("name")} as Set == ['Sample9', 'Child'] as Set

        when: "check alternative child approach"
        children = service.getChildren2(parent)
        then: "two children found"
        children.size() == 2
        children.collect {it.getProperty("name")} as Set == ['Sample9', 'Child'] as Set

    }
}