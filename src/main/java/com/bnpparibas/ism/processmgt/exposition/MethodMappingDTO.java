package com.bnpparibas.ism.processmgt.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MethodMappingDTO {
    @JsonProperty
    String name;

    public MethodMappingDTO(String name) {
        this.name = name;
    }

    public MethodMappingDTO() {
    }


}
