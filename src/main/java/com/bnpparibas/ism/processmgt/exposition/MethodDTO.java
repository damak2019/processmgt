package com.bnpparibas.ism.processmgt.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MethodDTO {
    @JsonProperty
     String name;
    @JsonProperty
    List<ProcessDTO> processDTOList;

    public MethodDTO() {
    }

    public MethodDTO(String name, List<ProcessDTO> myProcessDTOList ) {
        this.name = name;
        this.processDTOList = myProcessDTOList;
    }
}
