package com.bnpparibas.ism.processmgt.infrastructure;

import com.bnpparibas.ism.processmgt.domain.Artifact;
import com.bnpparibas.ism.processmgt.domain.ProcessActivity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This Class represent the JPA Entity for an Artifact
 * */

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property="@UUID")
@Entity(name = "ARTIFACT")
public class ArtifactJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",columnDefinition = "serial")
    private Long id;

    @Column(name = "TAG")
    private String tag;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "artifactJPAs")
    private Set<ProcessActivityJPA> processActivityJPAs;



    public ArtifactJPA() {
    }

    // Needed to  create the  Infrastructure JPA Object from the  Domain object
    public ArtifactJPA(Artifact artifact) {
        this.id = artifact.getId();
        this.tag = artifact.getTag();
        this.name = artifact.getName();
    }

    // Needed to transform  Infrastructure JPA object to Domain Object
    public Artifact toArtifact() {
        //    Set<ProcessActivity> processActivities = fromActivityJPASetToActivitySet(this.processActivityJPAs) ;
        return new Artifact(this.id, this.tag, this.name /*,processActivities*/);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public String getTag() {
        return tag;
    }
    public String getName() {
        return name;
    }

    public Set<ProcessActivityJPA> getProcessActivityJPAs() {
        if (processActivityJPAs ==null) {
            processActivityJPAs = new HashSet<>();
        }
        return processActivityJPAs;
    }
    public void setProcessActivityJPAs(Set<ProcessActivityJPA> processActivityJPAs) {
        this.processActivityJPAs = processActivityJPAs;
    }


    // Transform Domain Object List to Infrastructure JPA Object List
    private Set<ProcessActivityJPA> fromActivitySetToActivityJPASet(Set<ProcessActivity> processActivities) {
        return processActivities.stream().map(ProcessActivityJPA::new).collect(Collectors.toSet());
    }

    // Transform JPA Object List to Domain Object List
    private Set<ProcessActivity> fromActivityJPASetToActivitySet(Set<ProcessActivityJPA> processActivityJPAs) {
       return processActivityJPAs.stream().map(processActivityJPA -> processActivityJPA.toProcessActivity()).collect(Collectors.toSet());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtifactJPA that = (ArtifactJPA) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(tag, that.tag) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tag, name);
    }
}
