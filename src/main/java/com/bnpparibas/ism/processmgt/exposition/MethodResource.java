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
