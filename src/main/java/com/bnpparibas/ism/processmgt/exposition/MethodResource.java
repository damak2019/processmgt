package com.bnpparibas.ism.processmgt.exposition;

import com.bnpparibas.ism.processmgt.application.MethodService;
import com.bnpparibas.ism.processmgt.domain.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class MethodResource {
    @Autowired
    private MethodService methodService;

    // Resource to create a Method
    @RequestMapping(method = RequestMethod.POST, path = {"/methods/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Long createMethod(@RequestBody MethodDTO methodDTO) {
        return this.methodService.create(MethodAdapter.transformToMethod(methodDTO));
    }

    // Resource to obtain the details of a Method
    @RequestMapping(method = RequestMethod.GET, path = {"/methods/{methodId}"})
    public MethodDTO detailMethod(@PathVariable("methodId") Long methodId) {

         return MethodAdapter.adaptToMethodDTO(this.methodService.obtain(methodId));
    }

    //Resource to list all Methods
    @RequestMapping(method = RequestMethod.GET, path = {"/methods/"})
    public List<MethodDTO> listAllMethods() {
        return MethodAdapter.adaptToMethodDTOList(this.methodService.listAll());
    }

    //Resource to obtain methods by a name
    @RequestMapping(method = RequestMethod.GET, path = {"/methods/name/{name}"})
    public List<MethodDTO> listAllMethodsByName(@PathVariable("name") String name) {
        return MethodAdapter.adaptToMethodDTOList(this.methodService.listAllByName(name));
    }

    //Resource to obtain all Process  by a method mapping name, process type and process follow up
    //THIS METHOD IS CALLED BY THE PROJECT MANAGEMENT APPLICATION
    @RequestMapping(method = RequestMethod.GET, path = {"/methods/{name}/pname/{pname}/ptype/{ptype}/pfollow/{pfollow}"})
    public List<ProcessDTO> listProcessByMappingNameAndProjectProcess(@PathVariable("name") String name,
                                                        @PathVariable("pname") String pname,
                                                        @PathVariable("ptype") String ptype,
                                                        @PathVariable("pfollow") String pfollow) {
        return MethodAdapter.adaptToProcessListDTO(this.methodService.listAllProccessByMappedNameAndProcess(name,pname,ptype,pfollow));

    }
    //Resource to obtain all Process  by a method  name (not a mapping name)  , process type and process follow up
    // call in front : return this.http.get(`${this.baseUrl}/methods/${name}/ptype/${ptype}/pfollow/${pfollow}`)
    @RequestMapping(method = RequestMethod.GET, path = {"/methods/{name}/ptype/{ptype}/pfollow/{pfollow}"})
    public List<ProcessDTO> listProccessByMappedNameAndProcessTypeFollow  (@PathVariable("name") String name,
                                                                    //  @PathVariable("pname") String pname,
                                                                      @PathVariable("ptype") String ptype,
                                                                      @PathVariable("pfollow") String pfollow) {
        return MethodAdapter.adaptToProcessListDTO(this.methodService.listAllProccessByMappedNameAndProcessTypeFollow(name,ptype,pfollow));

    }

    //Resource to update a Method
    @RequestMapping(method = RequestMethod.PUT, path = {"/methods/{methodId}"})
    @ResponseStatus(HttpStatus.OK)
    public void updateMethod(@PathVariable("methodId") Long methodId, @RequestBody MethodDTO methodDTO) {

        this.methodService.update(methodId, MethodAdapter.transformToMethod(methodDTO));
    }

    //Ressource to remove a method
    @RequestMapping(method = RequestMethod.DELETE, path = {"/methods/{methodId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMethod(@PathVariable("methodId") Long methodId) {
        this.methodService.remove(methodId);
    }

    // Resource to add a MappingName to a method
    @RequestMapping(method = RequestMethod.POST, path = {"/methods/{methodId}/methodMappings"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addMappingToMethod(@PathVariable("methodId") Long methodId, @RequestBody  MethodMappingDTO methodMappingDTO) {
        this.methodService.addMethodMapping(methodId, MethodAdapter.transformToMethodMapping(methodMappingDTO));

    }

    // Resource to add a Process to a method
    // call in front: return  this.http.post<void>(`${this.baseUrl}/methods/${methodID}/addProcess`, processDTO);
    @RequestMapping(method = RequestMethod.POST, path = {"/methods/{methodId}/addProcess"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addProcessToMethod(@PathVariable("methodId") Long methodId, @RequestBody  ProcessDTO processDTO) {
        this.methodService.addProcess(methodId, MethodAdapter.transformToProcess(processDTO));

    }
    // Resource to add an Activity to a Process in a Method
    //call in front:  return  this.http.post<void>(`${this.baseUrl}/methods/${methodID}/process/${processId}/addProcessActivity`, processActivityDTO);
    @RequestMapping(method = RequestMethod.POST, path = {"/methods/{methodId}/process/{processId}/addProcessActivity"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addProcessActivityToMethod(@PathVariable("methodId") Long methodId, @PathVariable("processId") Long processId, @RequestBody  ProcessActivityDTO processActivityDTO) {
        this.methodService.addProcessActivity(methodId, processId, MethodAdapter.transformToProcessActivity(processActivityDTO));
        System.out.println("calling addProcessActivityToMethod");

    }
    // Resource to add an Artifact to an Activity in a Process
    //call in front:  return  this.http.post<void>(`${this.baseUrl}/methods/${methodID}/process/${processID}/activity/${activityID}/addArtifact`, artifactDTO);
    @RequestMapping(method = RequestMethod.POST, path = {"/methods/{methodId}/process/{processId}/activity/{activityID}/addArtifact"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addArtifactToMethod(@PathVariable("methodId") Long methodId, @PathVariable("processId") Long processId, @PathVariable("activityID") Long activityID, @RequestBody  ArtifactDTO artifactDTO) {
        this.methodService.addArtifactToActivity(methodId, processId, activityID, MethodAdapter.transformToArtifact(artifactDTO));
        System.out.println("calling addProcessActivityToMethod");

    }

       /* this commented code was used to test calling methodes from  other applications Ressources
        RestTemplate restTemplate = new RestTemplate();
        Long libraryId = methodId;

        String fooResourceUrl    = "http://localhost:8080/libraries/"+ libraryId;
        //
        ResponseEntity<String> response   = restTemplate.getForEntity(fooResourceUrl , String.class);
        //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println(response); */
}


