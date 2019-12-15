package com.bnpparibas.ism.processmgt.infrastructure;

import com.bnpparibas.ism.processmgt.domain.Method;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MethodDAO extends JpaRepository<MethodJPA, Long> {

    List<Method> findByName(String name);
}
