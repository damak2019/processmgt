package com.bnpparibas.ism.processmgt.application;

import com.bnpparibas.ism.processmgt.domain.Method;
import com.bnpparibas.ism.processmgt.exposition.MethodDTO;
import com.bnpparibas.ism.processmgt.infrastructure.MethodDAO;
import com.bnpparibas.ism.processmgt.infrastructure.MethodJPA;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing with Mockito and Spring without H2
 * */
@SpringBootTest
class MethodServiceTestWithMock {
    @Autowired
    MethodService methodService;

    @MockBean
    MethodDAO methodDAOMock;

    @Test
    void testingObtainMethodByIdWithMock(){

       Method method = new Method(1L,"myMethod", Collections.emptyList(),Collections.emptyList());
       MethodJPA methodJPA = new MethodJPA(method);


        // using Mockito (define the behavior needed from Mockito
        Mockito.when(methodDAOMock.findById(1L)).thenReturn(Optional.of(methodJPA));


        Method methodResult = methodService.obtain(1L);
        Assertions.assertThat(methodResult.getName()).isEqualTo("myMethod");

    }

}