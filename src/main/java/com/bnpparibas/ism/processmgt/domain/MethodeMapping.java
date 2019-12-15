package com.bnpparibas.ism.processmgt.domain;

public class MethodeMapping {
    private Long id;
    private String name;


    public MethodeMapping() {
    }

    public MethodeMapping(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void update (MethodeMapping methodMappingWithNewInformation){
        // this.id = methodWithNewInformation.getId();
        this.name = methodMappingWithNewInformation.getName();
    }


    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}
