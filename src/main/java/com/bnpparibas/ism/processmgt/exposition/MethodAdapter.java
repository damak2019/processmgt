package com.bnpparibas.ism.processmgt.exposition;

import com.bnpparibas.ism.processmgt.domain.*;
import com.bnpparibas.ism.processmgt.domain.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/** This Class is used to  transfrom Domain layer Objects to Exposition layer  Objects (DTO)
   and transform Back the exposition Layer objects to the Domain objects */

public class MethodAdapter {
    private MethodAdapter() {
    }

    // Transform a list of Method (from Domain layer ) to a list of MethodDTO (Exposition Layer)
   public static List<MethodDTO> adaptToMethodDTOList(List<Method> methods) {
        return methods.stream().map(method -> adaptToMethodDTO(method)).collect(Collectors.toList());
    }
    // Transform a Method (Domain layer)  to MethodDTO (Exposition layer)
    public static MethodDTO adaptToMethodDTO(Method method) {

        return  new MethodDTO(method.getId(),method.getName(),MethodAdapter.adaptToProcessListDTO(method.getProcesses()), MethodAdapter.adaptToMethodMappingListDTO(method.getMethodMappings()));
    }

    // Transform a List of Process (domain Layer) to list of ProcessDTO (exposition Layer)
    public static List<ProcessDTO> adaptToProcessListDTO(List<Process> processes) {
        return processes.stream().map(process -> adaptToProcessDTO(process)).collect(Collectors.toList());
    }

    // Transform a List of MethodMapping (domain Layer) to list of MethodMappingDTO (exposition Layer)
    public static List<MethodMappingDTO> adaptToMethodMappingListDTO(List<MethodMapping> methodMappings) {
        return methodMappings.stream().map(methodMapping -> adaptToMethodMappingDTO(methodMapping)).collect(Collectors.toList());
    }

    // Transform a Process (domain Layer) to a ProcessDTO (exposition Layer)
    public static ProcessDTO adaptToProcessDTO(Process process) {

        List<ProcessActivityDTO> processActivityDTOList = adaptToProcessActivityListDTO (process.getProcessActivities());
        return  new ProcessDTO(process.getId(),process.getDisplayName(),process.getProcessType(),process.getFollowUP() ,processActivityDTOList);

    }

    // Transform a  MethodMapping (domain Layer) to a MethodMappingDTO (exposition Layer)
    public static MethodMappingDTO adaptToMethodMappingDTO(MethodMapping methodMapping) {

        return  new MethodMappingDTO(methodMapping.getName());

    }
    // Transform a List of ProcessActivity (domain Layer) to list of ProcessActivityDTO (exposition Layer)
    public static List<ProcessActivityDTO>  adaptToProcessActivityListDTO(List<ProcessActivity> processesActivityList) {
        return processesActivityList.stream().map(MethodAdapter::adaptToProcessActivityDTO).collect(Collectors.toList());

    }
    // Transform a  ProcessActivity (domain Layer) to a ProcessActivityDTO (exposition Layer)
    public static ProcessActivityDTO adaptToProcessActivityDTO(ProcessActivity processActivity) {
        List<ArtifactDTO> artifactDTOList = adaptToArtifactListDTO (processActivity.getArtifacts());
        return  new ProcessActivityDTO(processActivity.getId(), processActivity.getName(),artifactDTOList);
    }
    // Transform a Set of Artifact (domain Layer) to list of ArtifactDTO (exposition Layer)
    private static List<ArtifactDTO> adaptToArtifactListDTO(Set<Artifact> artifacts) {
        return artifacts.stream().map(artifact ->  adaptToArtifactDTO(artifact)).collect(Collectors.toList());
    }

    // Transform an Artifact (domain Layer) to an ArtifactDTO (exposition Layer)
    private static  ArtifactDTO adaptToArtifactDTO (Artifact artifact) {
        return  new ArtifactDTO(artifact.getTag(), artifact.getName() );
    }

    // Transform a MethodDTO  (exposition Layer) to a Method (Domain Layer)
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
                methodMappingList
                );
    }

    // Transform a MethodDTO  (exposition Layer) to a Method (Domain Layer)
    public static Process transformToProcess(ProcessDTO processDTO) {

        List<ProcessActivity> processActivities = transformToProcessActivityList(  processDTO.processActivityDTOList );
        return new Process(null, processDTO.displayName, processDTO.processType, processDTO.followUP, processActivities);
    }

    // Transform a MethodMappingDTO  (exposition Layer) to a MethodMapping (Domain Layer)
    public static MethodMapping transformToMethodMapping(MethodMappingDTO methodMappingDTO) {
        return  new MethodMapping(null,methodMappingDTO.name );
    }
    // Transform a list of MethodMappingDTO  (exposition Layer) to a list of MethodMapping (Domain Layer)
    public static List<MethodMapping> transformToMethodMappingsList(List<MethodMappingDTO> methodMappingDTOList) {

        if(methodMappingDTOList == null) {
            return new ArrayList<>();
        }
        return methodMappingDTOList.stream().map(MethodAdapter::transformToMethodMapping).collect(Collectors.toList());
    }

    // Transform a list of ProcessDTO  (exposition Layer) to a list of Process (Domain Layer)
    public static List<Process> transformToProcessList(List<ProcessDTO> processDTOList) {

        if(processDTOList == null) {
            return new ArrayList<>();
        }
        return processDTOList.stream().map(MethodAdapter::transformToProcess).collect(Collectors.toList());
    }

    // Transform a list of ProcessActivityDTO  (exposition Layer) to a list of ProcessActivity (Domain Layer)
    private static List<ProcessActivity> transformToProcessActivityList(List<ProcessActivityDTO> processActivityDTOList) {
         if(processActivityDTOList == null) {
            return new ArrayList<>();
        }
        return processActivityDTOList.stream().map(MethodAdapter::transformToProcessActivity).collect(Collectors.toList());
    }


    // Transform a   ProcessActivityDTO  (exposition Layer) to a   ProcessActivity (Domain Layer)
    public static ProcessActivity transformToProcessActivity(ProcessActivityDTO processActivityDTO) {
         Set<Artifact> artifacts = transformToArtifactset(processActivityDTO.artifactDTOList);
        return new ProcessActivity(null, processActivityDTO.name,artifacts);
    }

    // Transform a list of ArtifactDTO  (exposition Layer) to a Set of Artifact (Domain Layer)
    private static Set<Artifact> transformToArtifactset(List<ArtifactDTO> artifactDTOList) {
        if(artifactDTOList == null) {
            return  Collections.emptySet();
        }
       return artifactDTOList.stream().map(artifactDTO -> transformToArtifact(artifactDTO) ).collect(Collectors.toSet());
    }

    // Transform an ArtifactDTO  (exposition Layer) to an Artifact (Domain Layer)
    public static  Artifact transformToArtifact (ArtifactDTO artifactDTO) {

        return new Artifact(null, artifactDTO.tag, artifactDTO.name/*, null*/);
    }



}
