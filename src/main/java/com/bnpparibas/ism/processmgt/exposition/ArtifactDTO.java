package com.bnpparibas.ism.processmgt.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * This class represents the Artifact in the exposition layer
 * */
public class ArtifactDTO {

    @JsonProperty
     String tag;
    @JsonProperty
     String name;
    // Do not add List<ProcessActivityDTO> in DTO to avoid looping
    /*@JsonProperty
      List<ProcessActivityDTO> processActivityDTOList;*/


    public ArtifactDTO(String tag, String name/*, List<ProcessActivityDTO> processActivityDTOList*/ ) {
        this.tag = tag;
        this.name = name;
       // this.processActivityDTOList = processActivityDTOList;
    }
}
