package com.bnpparibas.ism.processmgt.exposition;

import com.bnpparibas.ism.processmgt.domain.Method;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MethodAdapterTest {

    // Un test Unitaire (without Spring) : No dependency
    @Test
    void testingTransformationMethodDtoToMethod(){
        MethodDTO methodDTO;
        methodDTO = new MethodDTO(1L,"myMethod", null,null);
        Method method;
        method = MethodAdapter.transformToMethod(methodDTO);

        //AssetJ Way
        Assertions.assertThat(method.getId()).isEqualTo(methodDTO.id);
        //Junit Way
        org.junit.jupiter.api.Assertions.assertEquals(methodDTO.id,method.getId());

    }



}