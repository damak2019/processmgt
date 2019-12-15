package com.bnpparibas.ism.processmgt.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProcessActivityDTO {

    @JsonProperty
    String name;
     @JsonProperty
    List<ArtifactDTO> artifactDTOList;

    public ProcessActivityDTO() {
    }

    public ProcessActivityDTO(String name  , List<ArtifactDTO> artifactDTOList) {
        this.name = name;
        this.artifactDTOList = artifactDTOList;
    }
}
