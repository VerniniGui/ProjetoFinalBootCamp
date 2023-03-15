package br.gama.itau.projetofinal.exception;

public class DataIntegrityViolationException extends RuntimeException  {

    public DataIntegrityViolationException(String msg) {
        super(msg);
    }
    
}
