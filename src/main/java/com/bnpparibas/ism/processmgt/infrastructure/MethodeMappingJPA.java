package com.bnpparibas.ism.processmgt.infrastructure;

import com.bnpparibas.ism.processmgt.domain.MethodMapping;

import javax.persistence.*;

@Entity(name = "METHOD_MAPPING")
public class MethodeMappingJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",columnDefinition = "serial")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    private MethodJPA method;

    public MethodeMappingJPA() {
    }

    public MethodeMappingJPA(MethodMapping methodeMapping) {
        this.id = methodeMapping.getId();
        this.name = methodeMapping.getName();

    }
    public MethodMapping toMethodMapping() {
        return new MethodMapping(this.id, this.name);
    }

    public MethodJPA getMethod() {
        return method;
    }

    public void setMethod(MethodJPA method) {
        this.method = method;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}
