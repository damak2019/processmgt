package com.bnpparibas.ism.processmgt.domain;

import java.util.List;

public interface MethodRepository {
    Long save(Method method);
    Method get(Long id);
    List<Method> findAll();
    void delete(Method method);

    List<Method> findByName(String name);

   // List<Process> findProcessByDisplayName(String displayName);
}
