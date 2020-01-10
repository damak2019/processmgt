package com.bnpparibas.ism.processmgt.application;

import com.bnpparibas.ism.processmgt.domain.*;
import com.bnpparibas.ism.processmgt.domain.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// This Class provides the transactional Services

@Transactional
@Service
public class MethodService {

    /* We inject here the interface MethodRepository (any implementation of the Interface). Spring Framework injects the Implementation of methodRepository
     which is  in the Infrastructure
     We do not call directly the infrastructure Implementation. We avoid the direct dependency on the Infra.  The framework allows
     the inversion of dependency  */
    @Autowired
    private MethodRepository methodRepository;

    // Creating a Method  (The parameter is a Method as defined in the Domain layer)
    public Long create(Method method) {
        return this.methodRepository.save(method);
    }

    // Obtain a Method given an ID
    public Method obtain(Long id) {
        return this.methodRepository.get(id);
    }
    // Update a method :  The full method is updated
    public void update(Long id, Method methodWithNewInformations) {
        Method method = obtain(id);
        method.update(methodWithNewInformations);
        this.methodRepository.save(method);
    }
    // removing Methode
    public void remove(Long id) {
        Method method = obtain(id);
        this.methodRepository.delete(method);
    }



    public void addMethodMapping(Long methodId, MethodMapping  methodMapping) {

        this.methodRepository.addMethodMapping(methodId,methodMapping);
    }


    public void addProcess(Long methodId, Process process) {

        this.methodRepository.addProcees(methodId,process);

    }

    public void addProcessActivity(Long methodId, Long processId, ProcessActivity processActivity) {

        this.methodRepository.addProceesActivity(methodId,processId, processActivity);
    }


    public void addArtifactToActivity(Long methodId, Long processId, Long activityID, Artifact artifact) {

        this.methodRepository.addArtifactToActivity(methodId,processId, activityID, artifact);
    }


    // Obtain all methods
    public List<Method> listAll() {
        return this.methodRepository.findAll();
    }

    // List methods given a name
    public List<Method> listAllByName(String name) {
        return this.methodRepository.findByName(name);
    }

    //  Obtain the list of Processes given a mapping name  a ProcessType and  a process FollowUP
    public List<Process> listAllProccessByMappedNameAndProcessTypeFollow(String mappingName ,   String processType,  String followUP) {
        List<Method>  allMethods=   this.methodRepository.findAll();
        List<Process> processList = new ArrayList<Process>();

        for (Method method : allMethods) {
            if ( isMappedMethod(method,mappingName) ){
                processList.addAll(method.getProcesses());
            }
        }

        return  getFilterdProcessByTypeAndFollow(  processList,     processType,    followUP );

    }



    // Obtain the list of   processes given the method mapping name,  a Process display name ProcessType and process FollowUP
    public List<Process> listAllProccessByMappedNameAndProcess(String mappingName,   String displayName,   String processType,  String followUP) {
        List<Method>  allMethods=   this.methodRepository.findAll();
        List<Process> processList = new ArrayList<Process>();

        for (Method method : allMethods) {
            if ( isMappedMethod(method,mappingName) ){
                processList.addAll(method.getProcesses());
            }
        }
        return  getFilterdProcess(  processList,   displayName,     processType,    followUP );

    }




    // private methods to be used in services
    private boolean isMappedMethod(Method method, String mappedName) {
        List<MethodMapping>  methodMappingList =  method.getMethodMappings();

        return (!( methodMappingList.stream().filter(mapping -> {return (mapping.getName().equals(mappedName));}).collect(Collectors.toList()).isEmpty())) ;
    }

    // private methods to be used in services
    private List<Process> getFilterdProcess(List<Process> processList, String displayName,   String processType,  String followUP ){

        List<Process> filterdProcess  =   processList.
                stream().
                filter(process -> {  return ( (process.getDisplayName().equals(displayName))
                        && (process.getProcessType().toString().equals(processType))
                        && (process.getFollowUP().toString().equals(followUP)) ); }).
                collect(Collectors.toList());

        return  filterdProcess;

    }
    // private methods to be used in services
    private List<Process> getFilterdProcessByTypeAndFollow(List<Process> processList,    String processType,  String followUP ){

        List<Process> filterdProcess  =   processList.
                stream().
                filter(process -> {  return (
                        //(process.getDisplayName().equals(displayName)) &&
                        (process.getProcessType().toString().equals(processType)) &&
                                (process.getFollowUP().toString().equals(followUP)) ); }).
                collect(Collectors.toList());

        return  filterdProcess;

    }

    // Obtain the list of   processes given the method name, ProcessType and process FollowUP
    public List<Process> listAllProccessByMethNameAndProcess(String name,   String displayName,   String processType,  String followUP) {
        List<Method> allMethods =   this.methodRepository.findByName(name);
        List<Process> processList = new ArrayList<Process>();

        for (Method method : allMethods) {
            processList.addAll(method.getProcesses());
        }
        return  getFilterdProcess(  processList,   displayName,     processType,    followUP );
    }


}

