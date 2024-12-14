package mx.sgahc.security.service;


import lombok.AllArgsConstructor;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.repository.usuarios.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuthenticationProviderImpl implements AuthenticationProvider {
    private final UsuarioRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Optional<Usuario> userAdmin = Optional.ofNullable(userInfoRepository.findByCorreoElectronico(username))
                .orElseThrow(() -> new BadCredentialsException("User not found in database"));
        Usuario user = userAdmin.orElseThrow(() -> new BadCredentialsException("User not found in database"));
        if (passwordEncoder.matches(pwd, userAdmin.get().getContrasenia())) {
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRol().getRol()));
            return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
        } else {
            throw new BadCredentialsException("Invalid password!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
