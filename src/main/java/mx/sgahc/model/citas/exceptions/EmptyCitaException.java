package mx.sgahc.model.citas.exceptions;

public class EmptyCitaException extends RuntimeException{
    public EmptyCitaException (String error){
        super(error);
    }
}