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

    // Normaly for JPA  we are not obliged de declare this part of the relationship. But we added for more Clarity and
    // to avoid effect side that we had concerning merge problems
    @ManyToOne
    private MethodJPA method;

    public MethodeMappingJPA() {
    }

    // Needed to  create the  Infranstructure JPA Object from the  Domain object
    public MethodeMappingJPA(MethodMapping methodeMapping) {
        this.id = methodeMapping.getId();
        this.name = methodeMapping.getName();

    }
    // Needed to transform  Infrastructure JPA object to Domain Object
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
