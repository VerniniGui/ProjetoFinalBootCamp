package br.gama.itau.projetofinal.exception;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public class MyNotFoundException extends RuntimeException {

    public MyNotFoundException(String msg) {
        super(msg);
    }

}
