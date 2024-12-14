package mx.sgahc.security.model;


import mx.sgahc.model.usuarios.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImpl implements UserDetails {
    private Integer id;
    private String name;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;
    private Usuario userInfo;

    public UserDetailsImpl(Usuario userInfo) {
        this.userInfo = userInfo;
    }

    public UserDetailsImpl(Integer id, String name, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Usuario user) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRol().getRol()));
        return new UserDetailsImpl(
                user.getId(),
                user.getUsuario(),
                user.getCorreoElectronico(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (null == userInfo.getRol()) {
            return Collections.emptySet();
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(userInfo.getRol().getRol()));

        return grantedAuthorities;
    }

    /**
     * getUsername
     * @return username
     */
    @Override
    public String getUsername() {
        return userInfo.getCorreoElectronico();
    }

    /**
     * getPassword (OTP)
     * @return password
     */
    @Override
    public String getPassword() {
        return userInfo.getContrasenia();
    }

    /**
     * getName
     * @return name
     */
    public String getName() {
        return userInfo.getUsuario();
    }

    /**
     * getEmail
     * @return email
     */
    public String getEmail() {
        return userInfo.getCorreoElectronico();
    }

    /**
     * isEnabled
     * @return if user is enabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * isAccountNonLocked
     * @return if user is locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * isAccountNonExpired
     * @return if account is not expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * isCredentialsNonExpired
     * @return if credential is not expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
