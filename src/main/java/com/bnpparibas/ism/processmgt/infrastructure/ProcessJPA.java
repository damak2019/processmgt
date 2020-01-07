package com.bnpparibas.ism.processmgt.infrastructure;

import com.bnpparibas.ism.processmgt.domain.FollowUP;
import com.bnpparibas.ism.processmgt.domain.Process;

import com.bnpparibas.ism.processmgt.domain.ProcessActivity;
import com.bnpparibas.ism.processmgt.domain.ProcessType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "PROCESS")
public class ProcessJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",columnDefinition = "serial")
    private Long id;
    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROCESS_TYPE")
    private ProcessType processType;


    @Enumerated(EnumType.STRING)
    @Column(name = "FOLLOW_UP")
    private FollowUP followUP;

    @ManyToOne
    private MethodJPA method;

    public MethodJPA getMethod() {
        return method;
    }

    public void setMethod(MethodJPA method) {
        this.method = method;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,  mappedBy = "process")
   // @JoinColumn(name="PROCESS_ID", referencedColumnName = "ID")
    private List<ProcessActivityJPA> processActivitiesJPAs = new ArrayList<>();

    public ProcessJPA() {
    }

    public ProcessJPA(Process process) {
        this.id = process.getId();
        this.displayName = process.getDisplayName();
        this.processType = process.getProcessType();
        this.followUP = process.getFollowUP();
        this.processActivitiesJPAs = fromActivityListToActivityJPAList(process.getProcessActivities());
    }

    private List<ProcessActivityJPA> fromActivityListToActivityJPAList(List<ProcessActivity> processActivities) {
        return processActivities.stream().map(ProcessActivityJPA::new).collect(Collectors.toList());
    }

    public Process toProcess() {
         List<ProcessActivity> processActivities = fromActivityJPAListToActivityList(this.processActivitiesJPAs) ;
        return new Process(this.id, this.displayName, this.processType, this.followUP,processActivities );
    }



    private List<ProcessActivity> fromActivityJPAListToActivityList(List<ProcessActivityJPA> processActivitieJPASs) {
        return processActivitieJPASs.stream().map(ProcessActivityJPA::toProcessActivity).collect(Collectors.toList());

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

    public List<ProcessActivityJPA> getProcessActivitiesJPAs() {
        return processActivitiesJPAs;
    }

    public void addProcessActivity(ProcessActivityJPA processActivityJPA) {

        if(getProcessActivitiesJPAs()==null){
            this.processActivitiesJPAs = new ArrayList<>();
        }
        getProcessActivitiesJPAs().add(processActivityJPA);
        processActivityJPA.setProcess(this);
    }
    public  ProcessActivityJPA getProcessActivityJPAById (Long activityId) {
        ProcessActivityJPA processActivityJPA = null;
        List<ProcessActivityJPA>  processActivitieJPAs = this.getProcessActivitiesJPAs().
                stream().
                filter(act -> {  return (   (act.getId().intValue() == activityId.intValue())); }).
                collect(Collectors.toList());
        if (! (processActivitieJPAs == null || processActivitieJPAs.isEmpty()) ) {
            processActivityJPA = processActivitieJPAs.get(0);
        }
        return processActivityJPA;
    }


}
