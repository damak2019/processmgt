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

    @RequestMapping(method = RequestMethod.POST, path = {"/methods/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Long createMethod(@RequestBody MethodDTO methodDTO) {
        return this.methodService.create(MethodAdapter.transformToMethod(methodDTO));
    }
    @RequestMapping(method = RequestMethod.GET, path = {"/methods/{methodId}"})
    public MethodDTO detailMethod(@PathVariable("methodId") Long methodId) {
       // return MethodAdapter.adaptToMethodDTO(this.methodService.obtain(methodId));

       /* RestTemplate restTemplate = new RestTemplate();

        Long libraryId = methodId;

        String fooResourceUrl    = "http://localhost:8080/libraries/"+ libraryId;
        //
        ResponseEntity<String> response   = restTemplate.getForEntity(fooResourceUrl , String.class);
        //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        System.out.println(response); */
         return MethodAdapter.adaptToMethodDTO(this.methodService.obtain(methodId));
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/methods/"})
    public List<MethodDTO> listAllMethods() {
        return MethodAdapter.adaptToMethodDTOList(this.methodService.listAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/methods/name/{name}"})
    public List<MethodDTO> listAllMethodsByName(@PathVariable("name") String name) {
        return MethodAdapter.adaptToMethodDTOList(this.methodService.listAllByName(name));
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/methods/{name}/pname/{pname}/ptype/{ptype}/pfollow/{pfollow}"})

    public List<ProcessDTO> listProcessByMappingNameAndProjectProcess(@PathVariable("name") String name,
                                                        @PathVariable("pname") String pname,
                                                        @PathVariable("ptype") String ptype,
                                                        @PathVariable("pfollow") String pfollow) {
        return MethodAdapter.adaptToProcessListDTO(this.methodService.listAllProccessByMappedNameAndProcess(name,pname,ptype,pfollow));

    }
//  call in front return this.http.get(`${this.baseUrl}/methods/${name}/ptype/${ptype}/pfollow/${pfollow}`)
    @RequestMapping(method = RequestMethod.GET, path = {"/methods/{name}/ptype/{ptype}/pfollow/{pfollow}"})

    public List<ProcessDTO> listProccessByMappedNameAndProcessTypeFollow  (@PathVariable("name") String name,
                                                                    //  @PathVariable("pname") String pname,
                                                                      @PathVariable("ptype") String ptype,
                                                                      @PathVariable("pfollow") String pfollow) {
        return MethodAdapter.adaptToProcessListDTO(this.methodService.listAllProccessByMappedNameAndProcessTypeFollow(name,ptype,pfollow));

    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/methods/{methodId}"})
    @ResponseStatus(HttpStatus.OK)
    public void updateMethod(@PathVariable("methodId") Long methodId, @RequestBody MethodDTO methodDTO) {

        this.methodService.update(methodId, MethodAdapter.transformToMethod(methodDTO));
    }
    @RequestMapping(method = RequestMethod.DELETE, path = {"/methods/{methodId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMethod(@PathVariable("methodId") Long methodId) {
        this.methodService.remove(methodId);
    }


    @RequestMapping(method = RequestMethod.POST, path = {"/methods/{methodId}/methodMappings"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addMappingToMethod(@PathVariable("methodId") Long methodId, @RequestBody  MethodMappingDTO methodMappingDTO) {
        this.methodService.addMethodMapping(methodId, MethodAdapter.transformToMethodMapping(methodMappingDTO));

    }


// call in front  return  this.http.post<void>(`${this.baseUrl}/methods/${methodID}/addProcess`, processDTO);
    @RequestMapping(method = RequestMethod.POST, path = {"/methods/{methodId}/addProcess"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addProcessToMethod(@PathVariable("methodId") Long methodId, @RequestBody  ProcessDTO processDTO) {
        this.methodService.addProcess(methodId, MethodAdapter.transformToProcess(processDTO));

    }
// call in front return  this.http.post<void>(`${this.baseUrl}/methods/${methodID}/process/${processId}/addProcessActivity`, processActivityDTO);
    @RequestMapping(method = RequestMethod.POST, path = {"/methods/{methodId}/process/{processId}/addProcessActivity"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addProcessActivityToMethod(@PathVariable("methodId") Long methodId, @PathVariable("processId") Long processId, @RequestBody  ProcessActivityDTO processActivityDTO) {
        this.methodService.addProcessActivity(methodId, processId, MethodAdapter.transformToProcessActivity(processActivityDTO));
        System.out.println("calling addProcessActivityToMethod");

    }
    // call in front return  this.http.post<void>(`${this.baseUrl}/methods/${methodID}/process/${processID}/activity/${activityID}/addArtifact`, artifactDTO);

    @RequestMapping(method = RequestMethod.POST, path = {"/methods/{methodId}/process/{processId}/activity/{activityID}/addArtifact"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addArtifactToMethod(@PathVariable("methodId") Long methodId, @PathVariable("processId") Long processId, @PathVariable("activityID") Long activityID, @RequestBody  ArtifactDTO artifactDTO) {
        this.methodService.addArtifactToActivity(methodId, processId, activityID, MethodAdapter.transformToArtifact(artifactDTO));
        System.out.println("calling addProcessActivityToMethod");

    }


}

/*
@RestController
public class MethodResource {

    @Autowired
    private LibraryService libraryService;

    @RequestMapping(method = RequestMethod.POST, path = {"/libraries/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Long createLibrary(@RequestBody LibraryDTO libraryDTO) {
        return this.libraryService.create(LibraryAdapter.transformToLibrary(libraryDTO));
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/libraries/{libraryId}"})
    public LibraryDTO detailLibrary(@PathVariable("libraryId") Long libraryId) {
        return LibraryAdapter.adaptToBookDTO(this.libraryService.obtain(libraryId));
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/libraries/"})
    public List<LibraryDTO> listAllLibrairies() {
        return LibraryAdapter.adaptToLibraryDTO(this.libraryService.listAll());
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/libraries/{libraryId}"})
    @ResponseStatus(HttpStatus.OK)
    public void updateLibrary(@PathVariable("libraryId") Long libraryId, @RequestBody Library library) {
        this.libraryService.update(libraryId, library);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/libraries/{libraryId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBook(@PathVariable("libraryId") Long libraryId) {
        this.libraryService.remove(libraryId);
    }

}
 */
