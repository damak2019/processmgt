package com.bnpparibas.ism.processmgt.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MethodDTO {
    @JsonProperty
    Long id;
    @JsonProperty
     String name;
    @JsonProperty
    List<ProcessDTO> processDTOList;

    @JsonProperty
    List<MethodMappingDTO> methodMappingDTOList;

    public MethodDTO() {
    }

    public MethodDTO(Long id, String name, List<ProcessDTO> processDTOList, List<MethodMappingDTO> methodMappingDTOList) {
        this.id = id;
        this.name = name;
        this.processDTOList = processDTOList;
        this.methodMappingDTOList = methodMappingDTOList;
    }


}
