package com.bnpparibas.ism.processmgt.domain;

import java.util.List;

public interface MethodRepository {
    Long save(Method method);
    Method get(Long id);
    List<Method> findAll();
    void delete(Method method);

    List<Method> findByName(String name);

    void addMethodMapping(Long idMethod, MethodMapping methodMapping);
    void addProcees(Long idMethod, Process process);
    void addProceesActivity(Long idMethod, Long idProcess,ProcessActivity processActivity);
    void addArtifactToActivity(Long methodId, Long processId, Long activityID, Artifact artifact) ;


}
