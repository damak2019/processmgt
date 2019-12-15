package com.bnpparibas.ism.processmgt.infrastructure;

import com.bnpparibas.ism.processmgt.domain.Artifact;
import com.bnpparibas.ism.processmgt.domain.ProcessActivity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property="@UUID")
@Entity(name = "ARTIFACT")
public class ArtifactJPA {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "TAG")
    private String tag;
    @Column(name = "NAME")
    private String name;

   /* @ManyToMany(mappedBy = "artifactJPAs")
    private Set<ProcessActivityJPA> processActivityJPAs;*/

    public ArtifactJPA() {
    }

    public ArtifactJPA(Artifact artifact) {
        this.id = artifact.getId();
        this.tag = artifact.getTag();
        this.name = artifact.getName();
      //  this.processActivityJPAs = fromActivitySetToActivityJPASet(artifact.getProcessActivities());

    }

    private Set<ProcessActivityJPA> fromActivitySetToActivityJPASet(Set<ProcessActivity> processActivities) {
        return processActivities.stream().map(ProcessActivityJPA::new).collect(Collectors.toSet());
    }


    public Artifact toArtifact() {
    //    Set<ProcessActivity> processActivities = fromActivityJPASetToActivitySet(this.processActivityJPAs) ;
        return new Artifact(this.id, this.tag, this.name /*,processActivities*/);
    }

    private Set<ProcessActivity> fromActivityJPASetToActivitySet(Set<ProcessActivityJPA> processActivityJPAs) {
       return processActivityJPAs.stream().map(processActivityJPA -> processActivityJPA.toProcessActivity()).collect(Collectors.toSet());
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

   /* public Set<ProcessActivityJPA> getProcessActivityJPAs() {
        return processActivityJPAs;
    }*/
}
