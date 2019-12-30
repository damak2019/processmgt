package com.bnpparibas.ism.processmgt.infrastructure;

import com.bnpparibas.ism.processmgt.domain.Method;
import com.bnpparibas.ism.processmgt.domain.MethodMapping;
import com.bnpparibas.ism.processmgt.domain.Process;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "METHOD")
public class MethodJPA {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="METHOD_ID", referencedColumnName = "ID")
    private List<ProcessJPA> processJPAS;



    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="METHOD_ID", referencedColumnName = "ID")
    private List<MethodeMappingJPA> methodMappingJPAS;

    private MethodJPA() {
    }

    public MethodJPA(Method method) {
        this.id = method.getId();
        this.name = method.getName();
        this.processJPAS = fromProcessListToProcessJPAList(method.getProcesses());
        this.methodMappingJPAS = fromMappingListToMappingJPAList(method.getMethodMappings());

    }

    private List<ProcessJPA> fromProcessListToProcessJPAList(List<Process> processes) {
        return processes.stream().map(ProcessJPA::new).collect(Collectors.toList());
    }
    private List<MethodeMappingJPA> fromMappingListToMappingJPAList(List<MethodMapping> methodMappings) {
        return methodMappings.stream().map(MethodeMappingJPA::new).collect(Collectors.toList());
    }

    public Method toMethod() {
        List<MethodMapping> methodMappingList = fromMappingJPAListToMethodMappingList(this.methodMappingJPAS);
        List<Process> processList = fromProcessJPAListToProcessList(this.processJPAS);
        return new Method(this.id, this.name, processList,methodMappingList);
    }

    private List<Process> fromProcessJPAListToProcessList(List<ProcessJPA> processJPAs) {
        return processJPAs.
                stream().
                map(ProcessJPA::toProcess). //new Process(pJPA.getId(), pJPA.getDisplayName(), pJPA.getProcessType(),pJPA.getFollowUP())).
                collect(Collectors.toList());
    }

    private List<MethodMapping> fromMappingJPAListToMethodMappingList(List<MethodeMappingJPA> methodMappingJPAS) {
        return methodMappingJPAS.
                stream().
                map(MethodeMappingJPA::toMethodMapping).
                collect(Collectors.toList());
    }

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
}
