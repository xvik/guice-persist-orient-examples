package ru.vyarus.guice.persist.orient.examples

import spock.lang.Specification


/**
 * @author Vyacheslav Rusakov
 * @since 14.06.2016
 */
class GraphDemoAppTest extends Specification {

    def "Check app correct"() {

        when: "running sample"
        GraphDemoApp.main()

        then: "no exception"
        true
    }
}