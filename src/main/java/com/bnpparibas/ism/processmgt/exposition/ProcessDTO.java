package com.bnpparibas.ism.processmgt.exposition;

import com.bnpparibas.ism.processmgt.domain.FollowUP;
import com.bnpparibas.ism.processmgt.domain.ProcessActivity;
import com.bnpparibas.ism.processmgt.domain.ProcessType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
/**
 * This class represents the Process in the exposition layer
 * */
public class ProcessDTO {
    @JsonProperty
    Long id;
    @JsonProperty
     String displayName;
    @JsonProperty
     ProcessType processType;
    @JsonProperty
     FollowUP followUP;

    @JsonProperty
    List<ProcessActivityDTO> processActivityDTOList;

    public ProcessDTO(Long id, String displayName, ProcessType processType, FollowUP followUP, List<ProcessActivityDTO> processActivityDTOList) {
        this.id = id;
        this.displayName = displayName;
        this.processType = processType;
        this.followUP = followUP;
        this.processActivityDTOList = processActivityDTOList;
    }

}
