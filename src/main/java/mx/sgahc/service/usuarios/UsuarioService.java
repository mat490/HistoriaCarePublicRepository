package mx.sgahc.service.usuarios;

import mx.sgahc.model.excepciones.usuarios.UsuarioAlreadyExistsException;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.repository.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public Usuario findByEmail(String email) {
        Optional<Usuario> optionalEmail =  usuarioRepository.findByCorreoElectronico(email);
        return optionalEmail.orElse(null);
    }
    public void saveUser(Usuario usuario) {
        Optional<Usuario> usarioExiste = usuarioRepository.findByCorreoElectronico(usuario.getCorreoElectronico());
        if (usarioExiste.isEmpty()) {
            usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
            usuarioRepository.save(usuario);
        } else {
            throw new UsuarioAlreadyExistsException(usuario.getCorreoElectronico());
        }
    }

    public boolean usuarioTieneDatosPersonales(Integer id) {
        int datos = usuarioRepository.buscarDatosPorUsuarioId(id).size();
        if (datos > 0) {
            return true;
        }
        return false;
    }

    public Usuario encontrarPorID(Integer id) {
        Optional<Usuario> usuarioExiste = usuarioRepository.findById(id);
        return usuarioExiste.orElse(null);
    }




}
