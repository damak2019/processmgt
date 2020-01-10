package com.bnpparibas.ism.processmgt.domain;

import java.util.List;

public interface MethodRepository {

    // saving a Method (a domain object)
    Long save(Method method);

    // Obtaining a method by id
    Method get(Long id);

    // Obtaining all methods
    List<Method> findAll();

    // Deleting a method
    void delete(Method method);

    // Obtaining Methods by name
    List<Method> findByName(String name);

    // Adding a MethodMapping to a Method
    void addMethodMapping(Long idMethod, MethodMapping methodMapping);

    // Adding a Process to a Method
    void addProcees(Long idMethod, Process process);

    // adding a ProcessActivity  to a Process defined by its ID (in a method defined by its ID)

    void addProceesActivity(Long idMethod, Long idProcess,ProcessActivity processActivity);

    // adding an Artifact  to an activity defined by its ID (belonging to a process defined by its ID. That process belongs to a method
    // defined by its ID)
    void addArtifactToActivity(Long methodId, Long processId, Long activityID, Artifact artifact) ;


}
