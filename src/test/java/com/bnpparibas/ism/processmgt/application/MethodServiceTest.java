package com.bnpparibas.ism.processmgt.application;

import com.bnpparibas.ism.processmgt.domain.Method;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** testing using H2 data base and Spring*/

@SpringBootTest
class MethodServiceTest {

    @Autowired
    MethodService methodService;
    @Test
    void testingObtainMethodById(){
        Method method;
        method = methodService.obtain(1L);
        Assertions.assertThat(method.getName()).isEqualTo("Traditional 2.0");

    }


}