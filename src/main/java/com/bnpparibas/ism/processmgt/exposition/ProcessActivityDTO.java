package com.bnpparibas.ism.processmgt.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
/**
 * This class represents the ProcessActivity in the exposition layer
 * */
public class ProcessActivityDTO {
    @JsonProperty
    Long id;
    @JsonProperty
    String name;
     @JsonProperty
    List<ArtifactDTO> artifactDTOList;

    public ProcessActivityDTO() {
    }

    public ProcessActivityDTO(Long id, String name, List<ArtifactDTO> artifactDTOList) {
        this.id = id;
        this.name = name;
        this.artifactDTOList = artifactDTOList;
    }


}
