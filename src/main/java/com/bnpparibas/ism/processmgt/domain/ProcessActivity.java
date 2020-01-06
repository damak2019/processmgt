package com.bnpparibas.ism.processmgt.domain;


import java.util.HashSet;

import java.util.Set;

public class ProcessActivity {
    private Long id;
    private String name;
    private Set<Artifact> artifacts = new HashSet<>();


    public ProcessActivity() {
    }

    public ProcessActivity(Long id, String name,Set<Artifact>  artifacts) {
        this.id = id;
        this.name = name;
        this.artifacts = artifacts;
    }

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

    public void addArtifact(Artifact artifact) {

        if(getArtifacts()==null){
            this.artifacts = new HashSet<>()  ;
        }
        getArtifacts().add(artifact);
    }

}
