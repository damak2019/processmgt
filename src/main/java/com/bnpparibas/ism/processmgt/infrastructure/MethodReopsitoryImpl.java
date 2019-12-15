package com.bnpparibas.ism.processmgt.infrastructure;

import com.bnpparibas.ism.processmgt.domain.Method;
import com.bnpparibas.ism.processmgt.domain.MethodRepository;
import com.bnpparibas.ism.processmgt.domain.exception.ErrorCodes;
import com.bnpparibas.ism.processmgt.domain.exception.MyProcessAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MethodReopsitoryImpl implements MethodRepository {

    @Autowired
    private MethodDAO methodDAO;

    @Override
    public Long save(Method method) {
        MethodJPA methodJPA = methodDAO.save(new MethodJPA(method));
        return methodJPA.getId();
    }

    @Override
    public Method get(Long id) {

        return methodDAO.findById(id).map(MethodJPA::toMethod).orElseThrow(() -> new MyProcessAppException(ErrorCodes.METHOD_NOT_FOUND));
    }

    @Override
    public List<Method> findAll() {

        return  methodDAO.findAll().stream().map(methodJPA -> methodJPA.toMethod()).collect(Collectors.toList());
      //  return  methodDAO.findAll().stream().map(MethodJPA::toMethod).collect(Collectors.toList());

    }

    @Override
    public void delete(Method method) {

        methodDAO.delete(new MethodJPA(method));


    }

    @Override
    public List<Method> findByName(String name) {
        return this.methodDAO.findByName(name);
    }
}

