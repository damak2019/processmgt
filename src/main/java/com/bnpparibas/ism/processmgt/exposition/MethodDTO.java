package com.bnpparibas.ism.processmgt.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MethodDTO {
    @JsonProperty
     String name;
    @JsonProperty
    List<ProcessDTO> processDTOList;

    @JsonProperty
    List<MethodMappingDTO> methodMappingDTOList;

    public MethodDTO() {
    }

    public MethodDTO(String name, List<ProcessDTO> myProcessDTOList , List<MethodMappingDTO> myMethodMappingDTOList) {
        this.name = name;
        this.processDTOList = myProcessDTOList;
        this.methodMappingDTOList = myMethodMappingDTOList;
    }
}
