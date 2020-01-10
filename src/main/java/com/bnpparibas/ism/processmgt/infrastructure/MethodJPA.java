package com.bnpparibas.ism.processmgt.infrastructure;

import com.bnpparibas.ism.processmgt.domain.Method;
import com.bnpparibas.ism.processmgt.domain.MethodMapping;
import com.bnpparibas.ism.processmgt.domain.Process;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This Class represent the JPA Entity for a Method
 * */

@Entity(name = "METHOD")
public class MethodJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",columnDefinition = "serial")
    private Long id;

    @Column(name = "NAME")
    private String name;

   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "method", fetch = FetchType.LAZY)
   // @JoinColumn(name="METHOD_ID", referencedColumnName = "ID") // Not needed as we added mappedBy = "method"
    private List<ProcessJPA> processJPAS;



    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "method")
    //@JoinColumn(name="METHOD_ID", referencedColumnName = "ID") // Not needed as we added mappedBy = "method"
    private List<MethodeMappingJPA> methodMappingJPAS;

    private MethodJPA() {
    }

    // Needed to  create the  Infrastructure JPA Object from the  Domain object
    public MethodJPA(Method method) {
        this.id = method.getId();
        this.name = method.getName();
        this.processJPAS = fromProcessListToProcessJPAList(method.getProcesses());
        this.methodMappingJPAS = fromMappingListToMappingJPAList(method.getMethodMappings());
        // added to avoid merging problems in JPA
        this.methodMappingJPAS.forEach(mm->{
            mm.setMethod(this);
        });

    }
    //Getters
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<ProcessJPA> getProcessJPAS() {
        return processJPAS;
    }
    public List<MethodeMappingJPA> getMethodMappingJPAS() {
        return methodMappingJPAS;
    }

    // Needed to transform  Infrastructure JPA object to Domain Object
    public Method toMethod() {
        List<MethodMapping> methodMappingList = fromMappingJPAListToMethodMappingList(this.methodMappingJPAS);
        List<Process> processList = fromProcessJPAListToProcessList(this.processJPAS);
        return new Method(this.id, this.name, processList,methodMappingList);
    }

    //Transform Domain Processes List to Infrastructure Processes JPA List
    private List<ProcessJPA> fromProcessListToProcessJPAList(List<Process> processes) {
        return processes.stream().map(ProcessJPA::new).collect(Collectors.toList());    }
    //Transform Domain Mapping List to Infrastructure Mapping JPA List
    private List<MethodeMappingJPA> fromMappingListToMappingJPAList(List<MethodMapping> methodMappings) {
        return methodMappings.stream().map(MethodeMappingJPA::new).collect(Collectors.toList());
    }


    // Transform Infrastructure Processes JPA List to Domain Processes List
    private List<Process> fromProcessJPAListToProcessList(List<ProcessJPA> processJPAs) {
        return processJPAs.
                stream().
                map(ProcessJPA::toProcess). //new Process(pJPA.getId(), pJPA.getDisplayName(), pJPA.getProcessType(),pJPA.getFollowUP())).
                collect(Collectors.toList());
    }

    // Transform Infrastructure Mapping JPA List to Domain Mapping List
    private List<MethodMapping> fromMappingJPAListToMethodMappingList(List<MethodeMappingJPA> methodMappingJPAS) {
        return methodMappingJPAS.
                stream().
                map(MethodeMappingJPA::toMethodMapping).
                collect(Collectors.toList());
    }


    // Adding a Mapping JPA to the list of Mapping JPA
    public void addMethodMappingJPA(MethodeMappingJPA methodeMappingJPA){
        if(getMethodMappingJPAS()==null){
            this.methodMappingJPAS = new ArrayList<>();
        }
        getMethodMappingJPAS().add(methodeMappingJPA);
        // setting Method for added MethodMappingJPA to this (current method) to avoid merge pb
        methodeMappingJPA.setMethod(this);
    }

    // Adding a Process JPA to the list of Procees JPA of the Method JPA
    public void addProcessJPA(ProcessJPA processJPA) {

        if(getProcessJPAS()==null){
            this.processJPAS = new ArrayList<>();
        }
        getProcessJPAS().add(processJPA);
        // setting Method for added ProcessJPA to this (current method) to avoid merge pb
        processJPA.setMethod(this);
    }

    // Obtaining a process from the list by the ID
    public ProcessJPA getProcessJPAById(Long idProcess) {
        ProcessJPA processJPA = null;
        List<ProcessJPA>  processes = this.getProcessJPAS().
                stream().
                filter(p -> {
                    // for checking
                    System.out.println("p.getId()" + p.getId());
                    //System.out.println("equal " +  (p.getId().intValue()  == idProcess.intValue() ));

                    return (   (p.getId().intValue() == idProcess.intValue()));

                }).
                collect(Collectors.toList());
        // observable
        if (! (processes == null || processes.isEmpty()) ) {
            processJPA = processes.get(0);
        }
        return processJPA;
    }


}
