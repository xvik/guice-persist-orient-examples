package ru.vyarus.guice.persist.orient.examples.model;

import ru.vyarus.guice.persist.orient.db.scheme.annotation.Persistent;

import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Simple object mapping model.
 *
 * @author Vyacheslav Rusakov
 * @since 03.11.2017
 */
@Persistent
public class Model {

    @Id
    private String id;
    @Version
    private Long version;
    private String name;
    private String nick;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
