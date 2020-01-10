package com.bnpparibas.ism.processmgt.infrastructure;

import com.bnpparibas.ism.processmgt.domain.Artifact;
import com.bnpparibas.ism.processmgt.domain.ProcessActivity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This Class represent the JPA Entity for a ProcessActivity
 * */

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property="@UUID")
@Entity(name = "PROCESS_ACTIVITY")
public class ProcessActivityJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",columnDefinition = "serial")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    private ProcessJPA process;

    public ProcessJPA getProcess() {
        return process;
    }

    public void setProcess(ProcessJPA process) {
        this.process = process;
    }


    @ManyToMany(  cascade = CascadeType.ALL)
    @JoinTable(
            name = "ACTIVITY_HAS_ARTIFACT",
            joinColumns = @JoinColumn(name = "PROCESS_ACTIVITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ARTIFACT_ID")
    )
    private Set<ArtifactJPA> artifactJPAs;

    public ProcessActivityJPA() {
    }



    public ProcessActivityJPA(ProcessActivity processActivity) {
        this.id = processActivity.getId();
        this.name = processActivity.getName();
        this.artifactJPAs = fromArtefactSetToArtifactJPASet(processActivity.getArtifacts());
        this.artifactJPAs.forEach(art->{
            art.getProcessActivityJPAs().add(this);
        });

    }

    private Set<ArtifactJPA> fromArtefactSetToArtifactJPASet(Set<Artifact> artifacts) {
        return artifacts.stream().map(artifact ->  new ArtifactJPA(artifact)).collect(Collectors.toSet());
       // return artifacts.stream().map(artifact -> new ArtifactJPA(artifact)).collect(Collectors.toSet());
    }



    public ProcessActivity toProcessActivity() {
        Set<Artifact> artifacts = fromArtefactJPASetToArtifactSet(this.artifactJPAs);
       // List<ProcessActivity> processActivities = fromActivityJPAListToActivityList(this.processActivitiesJPAs) ;
        return new ProcessActivity(this.id, this.name,artifacts);
    }

    private Set<Artifact> fromArtefactJPASetToArtifactSet(Set<ArtifactJPA> artifactJPAs) {
        return  artifactJPAs.stream().map(ArtifactJPA::toArtifact).collect(Collectors.toSet());
    }


    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<ArtifactJPA> getArtifactJPASList() {
        return artifactJPAs;
    }

    public void addNewArtifact(ArtifactJPA artifactJPA) {

        if(getArtifactJPASList()==null){
            this.artifactJPAs = new HashSet<>();
        }
        getArtifactJPASList().add(artifactJPA);
        artifactJPA.getProcessActivityJPAs().add(this);
    }




}
