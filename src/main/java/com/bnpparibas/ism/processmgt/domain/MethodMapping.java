package com.bnpparibas.ism.processmgt.domain;

/**
 * A MethodMapping contain a name to map a method
 */
public class MethodMapping {
    private Long id;
    private String name;


    public MethodMapping() {
    }

    public MethodMapping(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void update (MethodMapping methodMappingWithNewInformation){
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
