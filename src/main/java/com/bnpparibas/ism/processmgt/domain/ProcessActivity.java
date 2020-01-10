package com.bnpparibas.ism.processmgt.domain;


import java.util.HashSet;

import java.util.Set;

/**
 * This Class define an Activity that has to be done inside a Process
 * Un activity is defined by its name but also the artifacts that sould be present as input so that the activity could be done
 * */

public class ProcessActivity {
    private Long id;
    private String name;
    private Set<Artifact> artifacts = new HashSet<>();


    public ProcessActivity() {
    }
    // Creating the ProcessActivity
    public ProcessActivity(Long id, String name,Set<Artifact>  artifacts) {
        this.id = id;
        this.name = name;
        this.artifacts = artifacts;
    }

    // Updating an Activity
    public void update (ProcessActivity processActivityWithNewInformation){
        // this.id = methodWithNewInformation.getId();
        this.name = processActivityWithNewInformation.getName();
        this.artifacts = processActivityWithNewInformation.getArtifacts();
    }


    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<Artifact> getArtifacts() {
        return artifacts;
    }

    // Adding an Artifact
    public void addArtifact(Artifact artifact) {

        if(getArtifacts()==null){
            this.artifacts = new HashSet<>()  ;
        }
        getArtifacts().add(artifact);
    }

}
