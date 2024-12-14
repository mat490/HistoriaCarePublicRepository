package mx.sgahc.model.excepciones.usuarios;

public class UsuarioAlreadyExistsException extends RuntimeException {
    public UsuarioAlreadyExistsException(String email) {
        super("El usuario ya existe: " + email);

    }
}
