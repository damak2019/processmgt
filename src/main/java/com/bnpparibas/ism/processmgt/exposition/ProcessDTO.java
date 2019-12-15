package com.bnpparibas.ism.processmgt.exposition;

import com.bnpparibas.ism.processmgt.domain.FollowUP;
import com.bnpparibas.ism.processmgt.domain.ProcessActivity;
import com.bnpparibas.ism.processmgt.domain.ProcessType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ProcessDTO {
    @JsonProperty
     String displayName;
    @JsonProperty
     ProcessType processType;
    @JsonProperty
     FollowUP followUP;

    @JsonProperty
    List<ProcessActivityDTO> processActivityDTOList;


    public ProcessDTO(String displayName, ProcessType processType, FollowUP followUP,List<ProcessActivityDTO> processActivityDTOList) {
        this.displayName = displayName;
        this.processType = processType;
        this.followUP = followUP;
        this.processActivityDTOList = processActivityDTOList;
    }
}
