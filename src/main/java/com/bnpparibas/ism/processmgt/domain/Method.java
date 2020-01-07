package com.bnpparibas.ism.processmgt.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Method {

    private Long id;
    private String name;

    private List<Process> processes = new ArrayList<>();



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
       // this.id = methodWithNewInformation.getId();  // IL ne faut pas
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



/*    public void addMethodMapping(MethodMapping methodMapping) {

        if(getMethodMappings()==null){
            this.methodMappings = new ArrayList<>();
        }
        getMethodMappings().add(methodMapping);
      //  methodMapping.setMethod(this);

    }*/

/*    public void addProcess(Process process) {

        if(getProcesses()==null){
            this.processes = new ArrayList<>();
        }
        getProcesses().add(process);
    }*/

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
