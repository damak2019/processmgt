package com.bnpparibas.ism.processmgt.infrastructure;

import com.bnpparibas.ism.processmgt.domain.MethodMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "METHOD_MAPPING")
public class MethodeMappingJPA {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;


    public MethodeMappingJPA() {
    }

    public MethodeMappingJPA(MethodMapping methodeMapping) {
        this.id = methodeMapping.getId();
        this.name = methodeMapping.getName();
    }
    public MethodMapping toMethodMapping() {
        return new MethodMapping(this.id, this.name);
    }


    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}
