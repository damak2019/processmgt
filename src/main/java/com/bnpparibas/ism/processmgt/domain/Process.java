package com.bnpparibas.ism.processmgt.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A Process is defined by a display name , a type and a Follow Up (a way that a project which use that process should be
 * followed up and controlled by Architects and Transvertial teams) .
 * A process has a list of Activities that have to be done by any project which follow this process
 * */

public class Process {
    private Long id;
    private String displayName;
    private ProcessType processType;
    private FollowUP followUP;
    private List<ProcessActivity> processActivities = new ArrayList<>();


    public Process() {
    }

    // Creating the process
    public Process(Long id, String displayName, ProcessType processType, FollowUP followUP, List<ProcessActivity> processActivities) {
        this.id = id;
        this.displayName = displayName;
        this.processType = processType;
        this.followUP = followUP;
        this.processActivities = processActivities;
    }

    // Updating the process
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

    public void addProcessActivity(ProcessActivity processActivity) {

        if(getProcessActivities()==null){
            this.processActivities = new ArrayList<>();
        }
        getProcessActivities().add(processActivity);
    }

    public  ProcessActivity getProcessActivityById (Long activityId) {
        ProcessActivity processActivity = null;
        List<ProcessActivity>  processActivities = this.getProcessActivities().
                stream().
                filter(act -> {  return (   (act.getId().intValue() == activityId.intValue())); }).
                collect(Collectors.toList());
        if (! (processActivities == null || processActivities.isEmpty()) ) {
            processActivity = processActivities.get(0);
        }
        return processActivity;
    }


}
