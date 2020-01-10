package com.bnpparibas.ism.processmgt.domain;

/**
This Class describe an Artifac which is peace of work that should be delivered as input for an activity of a process
*/

public class Artifact {
    private Long id;
    private String tag;
    private String name;
    /* dont include  Set<ProcessActivity> to avoid looping
    private Set<ProcessActivity> processActivities; */

    public Artifact() {
    }

    public Artifact(Long id, String tag, String name) {
        this.id = id;
        this.tag = tag;
        this.name = name;
        /* dont include  Set<ProcessActivity> to avoid looping
          this.processActivities = processActivities; */
    }

    public void update (Artifact artifactWithNewInformation){
        /* do ont assign Ids in updating objects
         this.id = methodWithNewInformation.getId(); */
        this.tag = artifactWithNewInformation.getTag();
        this.name = artifactWithNewInformation.getName();

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

}
