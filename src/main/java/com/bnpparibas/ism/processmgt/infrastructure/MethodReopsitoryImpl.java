package com.bnpparibas.ism.processmgt.infrastructure;

import com.bnpparibas.ism.processmgt.domain.*;
import com.bnpparibas.ism.processmgt.domain.Process;
import com.bnpparibas.ism.processmgt.domain.exception.ErrorCodes;
import com.bnpparibas.ism.processmgt.domain.exception.MyProcessAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
/* This Class belonging to the Infrastructure layer implements the l'interface MethodRepository defined in the Domain layer
/ this allows the Inversion of Dependency */

@Repository
public class MethodReopsitoryImpl implements MethodRepository {

    // Injecting a dependency to the DAO (implementing JPA for the Method)
    @Autowired
    private MethodDAO methodDAO;

    // saving a Method (a domain object) througth JPA object
    @Override
    public Long save(Method method) {
        MethodJPA methodJPA = methodDAO.save(new MethodJPA(method));
        return methodJPA.getId();
    }

    // Obtaining a method by id
    @Override
    public Method get(Long id) {
        return methodDAO.findById(id).map(MethodJPA::toMethod).orElseThrow(() -> new MyProcessAppException(ErrorCodes.METHOD_NOT_FOUND));
    }

    // retreiving all methods
    @Override
    public List<Method> findAll() {
        return  methodDAO.findAll().stream().map(methodJPA -> methodJPA.toMethod()).collect(Collectors.toList());
      //  return  methodDAO.findAll().stream().map(MethodJPA::toMethod).collect(Collectors.toList()); // equivalent

    }

    // delete a methode
    @Override
    public void delete(Method method) {
        methodDAO.delete(new MethodJPA(method));
    }

    // Obtaing a list of methode by name
    @Override
    public List<Method> findByName(String name) {
        return this.methodDAO.findByName(name).stream().map(methodJPA -> methodJPA.toMethod()).collect(Collectors.toList());
    }

    // adding a MethodeMapping to a Method defined by its ID
    @Override
    public void addMethodMapping(Long idMethod, MethodMapping methodMapping) {
        MethodeMappingJPA methodeMappingJPA= new MethodeMappingJPA(methodMapping);
        MethodJPA methodJPA;
        if ( methodDAO.findById(idMethod).isPresent() ){
            methodJPA = methodDAO.findById(idMethod).get();
            methodJPA.addMethodMappingJPA(methodeMappingJPA);
            methodDAO.save(methodJPA);
        }


    }

    // adding a Process to a Method defined by its ID
    @Override
    public void addProcees(Long idMethod, Process process) {
        ProcessJPA processJPA = new ProcessJPA((process));
        MethodJPA methodJPA;
        if ( methodDAO.findById(idMethod).isPresent() ){
            methodJPA = methodDAO.findById(idMethod).get();
            methodJPA.addProcessJPA(processJPA);
            methodDAO.save(methodJPA);
        }
    }

    // adding a ProcessActivity  to a Process defined by its ID (in a method defined by its ID)
    @Override
    public void addProceesActivity(Long idMethod, Long idProcess, ProcessActivity processActivity) {
        ProcessActivityJPA processActivityJPA = new ProcessActivityJPA(processActivity);
        MethodJPA methodJPA;
        if ( methodDAO.findById(idMethod).isPresent() ) {
            methodJPA = methodDAO.findById(idMethod).get();
            if (methodJPA != null) {
                ProcessJPA processJPA = methodJPA.getProcessJPAById(idProcess);
                if (processJPA!=null){
                    processJPA.addProcessActivity(processActivityJPA);
                    methodDAO.save(methodJPA);
                }
            }
        }
    }


    // adding an Artifact  to an activity defined by its ID (belonging to a process defined by its ID. That process belongs to a method
    // defined by its ID)
    @Override
    public void addArtifactToActivity(Long idMethod, Long idProcess, Long idActivity, Artifact artifact) {
        ArtifactJPA artifactJPA = new ArtifactJPA(artifact);
        MethodJPA methodJPA;
        if ( methodDAO.findById(idMethod).isPresent() ) {
            methodJPA = methodDAO.findById(idMethod).get();
            if (methodJPA != null) {
                ProcessJPA processJPA = methodJPA.getProcessJPAById(idProcess);
                if (processJPA!=null){
                     ProcessActivityJPA processActivityJPA = processJPA.getProcessActivityJPAById(idActivity);
                    if (processActivityJPA!=null){
                        processActivityJPA.addNewArtifact(artifactJPA);
                        methodDAO.save(methodJPA);
                    }

                }
            }
        }
    }
}

