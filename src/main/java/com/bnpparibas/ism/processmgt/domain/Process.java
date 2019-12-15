package com.bnpparibas.ism.processmgt.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Process {
    private Long id;
    private String displayName;
    private ProcessType processType;
    private FollowUP followUP;
    private List<ProcessActivity> processActivities = new ArrayList<>();


    public Process() {
    }

    public Process(Long id, String displayName, ProcessType processType, FollowUP followUP, List<ProcessActivity> processActivities) {
        this.id = id;
        this.displayName = displayName;
        this.processType = processType;
        this.followUP = followUP;
        this.processActivities = processActivities;
    }
     public void update (Process  processWithNewInformation){
        // this.id = methodWithNewInformation.getId();
        this.displayName = processWithNewInformation.getDisplayName();
        this.processType = processWithNewInformation.getProcessType();
        this.followUP = processWithNewInformation.getFollowUP();
         this.processActivities = processWithNewInformation.getProcessActivities();
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public FollowUP getFollowUP() {
        return followUP;
    }

    public List<ProcessActivity> getProcessActivities() {
        return processActivities;
    }


}
