package com.bnpparibas.ism.processmgt.exposition;

import com.bnpparibas.ism.processmgt.domain.*;
import com.bnpparibas.ism.processmgt.domain.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MethodAdapter {
    private MethodAdapter() {
    }




   public static List<MethodDTO> adaptToMethodDTOList(List<Method> methods) {
        return methods.stream().map(method -> adaptToMethodDTO(method)).collect(Collectors.toList());
    }
    public static MethodDTO adaptToMethodDTO(Method method) {

        return  new MethodDTO(method.getId(),method.getName(),MethodAdapter.adaptToProcessListDTO(method.getProcesses()), MethodAdapter.adaptToMethodMappingListDTO(method.getMethodMappings()));
    }
    public static List<ProcessDTO> adaptToProcessListDTO(List<Process> processes) {
        return processes.stream().map(process -> adaptToProcessDTO(process)).collect(Collectors.toList());
    }
    public static List<MethodMappingDTO> adaptToMethodMappingListDTO(List<MethodMapping> methodMappings) {
        return methodMappings.stream().map(methodMapping -> adaptToMethodMappingDTO(methodMapping)).collect(Collectors.toList());
    }

    public static ProcessDTO adaptToProcessDTO(Process process) {

        List<ProcessActivityDTO> processActivityDTOList = adaptToProcessActivityListDTO (process.getProcessActivities());
        return  new ProcessDTO(process.getId(),process.getDisplayName(),process.getProcessType(),process.getFollowUP() ,processActivityDTOList);

    }
    public static MethodMappingDTO adaptToMethodMappingDTO(MethodMapping methodMapping) {

        //List<ProcessActivityDTO> processActivityDTOList = adaptToProcessActivityListDTO (process.getProcessActivities());
        return  new MethodMappingDTO(methodMapping.getName());

    }
    public static List<ProcessActivityDTO>  adaptToProcessActivityListDTO(List<ProcessActivity> processesActivityList) {
        return processesActivityList.stream().map(MethodAdapter::adaptToProcessActivityDTO).collect(Collectors.toList());

    }
    public static ProcessActivityDTO adaptToProcessActivityDTO(ProcessActivity processActivity) {
        List<ArtifactDTO> artifactDTOList = adaptToArtifactListDTO (processActivity.getArtifacts());
        return  new ProcessActivityDTO(processActivity.getId(), processActivity.getName(),artifactDTOList);
    }

    private static List<ArtifactDTO> adaptToArtifactListDTO(Set<Artifact> artifacts) {
        return artifacts.stream().map(artifact ->  adaptToArtifactDTO(artifact)).collect(Collectors.toList());
    }
    private static  ArtifactDTO adaptToArtifactDTO (Artifact artifact) {
        return  new ArtifactDTO(artifact.getTag(), artifact.getName() );
    }

    public static Method transformToMethod(MethodDTO methodDTO) {

        List<Process>  proccesList   = null;
        proccesList = transformToProcessList( methodDTO.processDTOList);

        List<MethodMapping>  methodMappingList   = null;
        methodMappingList = transformToMethodMappingsList( methodDTO.methodMappingDTOList);

        return new Method(
                //null,
                methodDTO.id,
                methodDTO.name,
                proccesList,
             //   methodDTO.processDTOList.stream().map(MethodAdapter::transformToProcess).collect(Collectors.toList()),
                methodMappingList
                //methodDTO.methodMappingDTOList.stream().map(MethodAdapter::transformToMethodMapping).collect(Collectors.toList())
                );
    }

    public static Process transformToProcess(ProcessDTO processDTO) {

        List<ProcessActivity> processActivities = transformToProcessActivityList(  processDTO.processActivityDTOList );
        return new Process(null, processDTO.displayName, processDTO.processType, processDTO.followUP, processActivities);
    }

    public static MethodMapping transformToMethodMapping(MethodMappingDTO methodMappingDTO) {

//        List<ProcessActivity> processActivities = transformToProcessActivityList(  processDTO.processActivityDTOList );
//        return new Process(null, processDTO.displayName, processDTO.processType, processDTO.followUP, processActivities);

        return  new MethodMapping(null,methodMappingDTO.name );
    }
    public static List<MethodMapping> transformToMethodMappingsList(List<MethodMappingDTO> methodMappingDTOList) {

        if(methodMappingDTOList == null) {
            return new ArrayList<>();
        }
        return methodMappingDTOList.stream().map(MethodAdapter::transformToMethodMapping).collect(Collectors.toList());
    }

    public static List<Process> transformToProcessList(List<ProcessDTO> processDTOList) {

        if(processDTOList == null) {
            return new ArrayList<>();
        }
        return processDTOList.stream().map(MethodAdapter::transformToProcess).collect(Collectors.toList());
    }





    private static List<ProcessActivity> transformToProcessActivityList(List<ProcessActivityDTO> processActivityDTOList) {
         if(processActivityDTOList == null) {
            return new ArrayList<>();
        }
        return processActivityDTOList.stream().map(MethodAdapter::transformToProcessActivity).collect(Collectors.toList());
    }



    public static ProcessActivity transformToProcessActivity(ProcessActivityDTO processActivityDTO) {
         Set<Artifact> artifacts = transformToArtifactset(processActivityDTO.artifactDTOList);
        return new ProcessActivity(null, processActivityDTO.name,artifacts);
    }

    private static Set<Artifact> transformToArtifactset(List<ArtifactDTO> artifactDTOList) {
        if(artifactDTOList == null) {
            return  Collections.emptySet();
        }

       return artifactDTOList.stream().map(artifactDTO -> transformToArtifact(artifactDTO) ).collect(Collectors.toSet());
    }


    public static  Artifact transformToArtifact (ArtifactDTO artifactDTO) {

        return new Artifact(null, artifactDTO.tag, artifactDTO.name/*, null*/);
    }



}
