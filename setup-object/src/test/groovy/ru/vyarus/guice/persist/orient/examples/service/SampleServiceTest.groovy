package ru.vyarus.guice.persist.orient.examples.service

import com.google.inject.Inject
import ru.vyarus.guice.persist.orient.examples.AbstractTest
import ru.vyarus.guice.persist.orient.examples.model.Sample

/**
 * @author Vyacheslav Rusakov
 * @since 12.06.2016
 */
class SampleServiceTest extends AbstractTest {

    @Inject
    SampleService service

    def "Check methods"() {

        when: "lookup count"
        long cnt = service.count()
        then: "ok"
        cnt == 10

        when: "lookup with query"
        cnt = service.count2()
        then: "ok"
        cnt == 10

        when: "select last"
        Sample rec = service.selectLast()
        then: "found"
        rec

        when: "search by name"
        rec = service.detach(rec)
        rec = service.findByName(rec.getName())
        then: "found"
        rec

        when: "replace name"
        rec = service.detach(rec)
        service.replaceName(rec.getName(), "test")
        then: "renamed in db"
        service.findByName("test")

    }
}