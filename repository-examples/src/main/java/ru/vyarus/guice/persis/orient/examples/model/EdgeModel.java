package ru.vyarus.guice.persis.orient.examples.model;

import ru.vyarus.guice.persist.orient.db.scheme.annotation.Persistent;
import ru.vyarus.guice.persist.orient.db.scheme.initializer.ext.type.edge.EdgeType;

import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Edge object. More handful to use instead of pure edge.
 * <p>
 * Will be automatically recognized and scheme class created.
 *
 * @author Vyacheslav Rusakov
 * @since 06.11.2017
 */
@Persistent
@EdgeType
public class EdgeModel {

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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
