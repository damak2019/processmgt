package com.bnpparibas.ism.processmgt.domain;

import java.util.Set;

public class Artifact {
    private Long id;
    private String tag;
    private String name;
   // private Set<ProcessActivity> processActivities;

    public Artifact() {
    }

    public Artifact(Long id, String tag, String name
           // ,Set<ProcessActivity> processActivities
            ) {
        this.id = id;
        this.tag = tag;
        this.name = name;
     //   this.processActivities = processActivities;
    }

    public void update (Artifact artifactWithNewInformation){
        // this.id = methodWithNewInformation.getId();
        this.tag = artifactWithNewInformation.getTag();
        this.name = artifactWithNewInformation.getName();
     //   this.processActivities = artifactWithNewInformation.getProcessActivities();
    }


    public Long getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }
/*    public Set<ProcessActivity> getProcessActivities() {
        return processActivities;
    }*/
}
