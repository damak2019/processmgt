package com.bnpparibas.ism.processmgt.infrastructure;

import javax.persistence.*;

@Entity(name = "METHOD_MAPPING_TEST")
public class MethodMappingTestJPA  extends PersistableElement {
 /*   @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;*/

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    private MethodTestJPA methodTestJPA;

    public MethodMappingTestJPA() {
    }

    public MethodMappingTestJPA(String name) {
        this.name = name;
    }

    public MethodMappingTestJPA(String name, MethodTestJPA methodTestJPA) {
        this.name = name;
        this.methodTestJPA = methodTestJPA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MethodTestJPA getMethodTestJPA() {
        return methodTestJPA;
    }

    public void setMethodTestJPA(MethodTestJPA methodTestJPA) {
        this.methodTestJPA = methodTestJPA;
    }
}





