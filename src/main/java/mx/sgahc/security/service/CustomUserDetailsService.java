package mx.sgahc.security.service;

import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.repository.usuarios.UsuarioRepository;
import mx.sgahc.service.usuarios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioService;

    @Autowired
    public CustomUserDetailsService(UsuarioRepository usuarioService) {
        this.usuarioService = usuarioService;
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByCorreoElectronico(email).orElse(null);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + email);
        }
        String userName = usuario.getCorreoElectronico();
        String password = usuario.getContrasenia();

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(usuario.getRol().getRol()));


        return new User(userName, password, authorities);
    }
}