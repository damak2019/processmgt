package com.bnpparibas.ism.processmgt.domain.exception;

import java.util.HashSet;
import java.util.Set;

public class MyProcessAppException extends RuntimeException {
    private Set<String> codeErreurs = new HashSet<>();

    public MyProcessAppException(String codeErreur) {
        this.codeErreurs.add(codeErreur);
    }

    public MyProcessAppException(Set<String> codeErreurs) {
        this.codeErreurs = codeErreurs;
    }

    public Set<String> getCodeErreurs() {
        return codeErreurs;
    }
}
