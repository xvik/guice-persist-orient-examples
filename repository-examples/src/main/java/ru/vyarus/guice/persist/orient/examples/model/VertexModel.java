package ru.vyarus.guice.persist.orient.examples.model;

import ru.vyarus.guice.persist.orient.db.scheme.annotation.Persistent;
import ru.vyarus.guice.persist.orient.db.scheme.initializer.ext.type.vertex.VertexType;

import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Vertex object. More handful to use instead of pure vertex.
 * <p>
 * Will be automatically recognized and scheme class created.
 *
 * @author Vyacheslav Rusakov
 * @since 03.11.2017
 */
@Persistent
@VertexType
public class VertexModel {

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
