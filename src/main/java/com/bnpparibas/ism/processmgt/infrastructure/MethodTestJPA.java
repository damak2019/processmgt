package com.bnpparibas.ism.processmgt.infrastructure;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
(name = "METHOD_TEST")
public class MethodTestJPA extends PersistableElement{
/*    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;*/

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "methodTestJPA")
    //@JoinColumn(name="METHOD_TEST_ID", referencedColumnName = "ID")
    private List<MethodMappingTestJPA> methodMappingTestJPAS;

    public MethodTestJPA() {
    }


    public MethodTestJPA(String name, List<MethodMappingTestJPA> methodMappingTestJPAS) {
        this.name = name;
        this.methodMappingTestJPAS = methodMappingTestJPAS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MethodMappingTestJPA> getMethodMappingTestJPAS() {
        return methodMappingTestJPAS;
    }

    public void setMethodMappingTestJPAS(List<MethodMappingTestJPA> methodMappingTestJPAS) {
        this.methodMappingTestJPAS = methodMappingTestJPAS;
    }

    public void addPhase(MethodMappingTestJPA methodMappingTestJPA){
        if(getMethodMappingTestJPAS()==null){
            this.methodMappingTestJPAS = new ArrayList<>();
        }
        getMethodMappingTestJPAS().add(methodMappingTestJPA);
        methodMappingTestJPA.setMethodTestJPA(this);
    }

}


