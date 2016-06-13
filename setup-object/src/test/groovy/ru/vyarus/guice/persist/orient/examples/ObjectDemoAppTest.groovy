package ru.vyarus.guice.persist.orient.examples

import spock.lang.Specification


/**
 * @author Vyacheslav Rusakov
 * @since 12.06.2016
 */
class ObjectDemoAppTest extends Specification {

    def "Check app correct"() {

        when: "running sample"
        ObjectDemoApp.main()

        then: "no exception"
        true
    }
}