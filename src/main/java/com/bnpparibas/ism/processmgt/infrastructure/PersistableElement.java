package com.bnpparibas.ism.processmgt.infrastructure;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class PersistableElement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Version
    @Column
    protected Timestamp optlock;

    public PersistableElement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getOptlock() {
        return optlock;
    }

    public void setOptlock(Timestamp optlock) {
        this.optlock = optlock;
    }
}

