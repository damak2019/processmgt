package com.bnpparibas.ism.processmgt.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Method is the main object in the Domain. It is the candidate to be an agregate in DDD approch
 * it is defined by a name and several mapping name
 * It has alos several Process
 * */

public class Method {

    private Long id;
    private String name;

    // List of Process
    private List<Process> processes = new ArrayList<>();

    // List of mapping names for the method
    private List<MethodMapping> methodMappings = new ArrayList<>();

    public Method() {
    }

    public Method(Long id, String name , List<Process> myProcesses,List<MethodMapping> myMethodMappings) {
        this.id = id;
        this.name = name;
       this.processes = myProcesses;
       this.methodMappings = myMethodMappings;
    }


    public void update (Method methodWithNewInformation){
       // this.id = methodWithNewInformation.getId();  // IL ne faut pas assigner l'Id pour l'update
        this.name = methodWithNewInformation.getName();
        this.processes = methodWithNewInformation.getProcesses();
        this.methodMappings = methodWithNewInformation.getMethodMappings();
    }


    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Process> getProcesses() {
        return processes;
    }
    public List<MethodMapping> getMethodMappings() {
        return methodMappings;
    }

    public  Process getProcessById (Long processId) {
        Process process = null;
        List<Process>  processes = this.getProcesses().
                stream().
                filter(p -> {
                    System.out.println("p.getId()" + p.getId());
                    System.out.println("equal " +  (p.getId().intValue()  == processId.intValue() ));
                    return (   (p.getId().intValue() == processId.intValue()));
                }).
                collect(Collectors.toList());
        if (! (processes == null || processes.isEmpty()) ) {
            process = processes.get(0);
        }
        return process;
    }




}
