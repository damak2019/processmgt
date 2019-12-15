package com.bnpparibas.ism.processmgt.application;

import com.bnpparibas.ism.processmgt.domain.FollowUP;
import com.bnpparibas.ism.processmgt.domain.Method;
import com.bnpparibas.ism.processmgt.domain.MethodRepository;
import com.bnpparibas.ism.processmgt.domain.Process;
import com.bnpparibas.ism.processmgt.domain.ProcessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class MethodService {

    @Autowired
    private MethodRepository methodRepository;

    public Long create(Method method) {
        return this.methodRepository.save(method);
    }

    public Method obtain(Long id) {
        return this.methodRepository.get(id);
    }

    public List<Method> listAll() {
        return this.methodRepository.findAll();
    }

    public List<Method> listAllByName(String name) {
        return this.methodRepository.findByName(name);

    }
    public List<Process> listAllByNameAndProcess(String name,   String displayName,   String processType,  String followUP) {
        List<Method> allMethods =   this.methodRepository.findByName(name);
        List<Process> processList = new ArrayList<Process>();

        for (Method method : allMethods) {

            processList.addAll(method.getProcesses());
        }

        return  getFilterdProcess(  processList,   displayName,     processType,    followUP );



    }

    private List<Process> getFilterdProcess(List<Process> processList, String displayName,   String processType,  String followUP ){

        List<Process> filterdProcess  =   processList.
                                            stream().
                                            filter(process -> {  return ( (process.getDisplayName().equals(displayName))
                                                                && (process.getProcessType().toString().equals(processType))
                                                                && (process.getFollowUP().toString().equals(followUP)) ); }).
                                            collect(Collectors.toList());

        return  filterdProcess;

    }



    public void update(Long id, Method methodWithNewInformations) {
        Method method = obtain(id);
        method.update(methodWithNewInformations);
        this.methodRepository.save(method);
    }

    public void remove(Long id) {
        Method method = obtain(id);
        this.methodRepository.delete(method);
    }


}

