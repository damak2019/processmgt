package com.bnpparibas.ism.processmgt.infrastructure;

import com.bnpparibas.ism.processmgt.domain.Artifact;
import com.bnpparibas.ism.processmgt.domain.ProcessActivity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity(name = "PROCESS_ACTIVITY")
public class ProcessActivityJPA {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;


    @ManyToMany(cascade = CascadeType.ALL)
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

    }

    private Set<ArtifactJPA> fromArtefactSetToArtifactJPASet(Set<Artifact> artifacts) {
        return artifacts.stream().map(artifact -> new ArtifactJPA(artifact)).collect(Collectors.toSet());
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


}
