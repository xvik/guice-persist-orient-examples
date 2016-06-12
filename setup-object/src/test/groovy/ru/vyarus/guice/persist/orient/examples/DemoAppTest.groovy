package ru.vyarus.guice.persist.orient.examples

import spock.lang.Specification


/**
 * @author Vyacheslav Rusakov
 * @since 12.06.2016
 */
class DemoAppTest extends Specification {

    def "Check app correct"() {

        when: "running sample"
        DemoApp.main()

        then: "no exception"
        true
    }
}