package ru.vyarus.guice.persist.orient.examples.service

import com.google.inject.Inject
import com.orientechnologies.orient.core.record.impl.ODocument
import ru.vyarus.guice.persist.orient.examples.AbstractTest

/**
 * @author Vyacheslav Rusakov
 * @since 09.06.2016
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
        ODocument rec = service.selectLast()
        then: "found"
        rec

        when: "search by name"
        rec = service.findByName(rec.field("name"))
        then: "found"
        rec

        when: "replace name"
        service.replaceName(rec.<String> field("name"), "test")
        then: "renamed in db"
        service.findByName("test")

    }
}