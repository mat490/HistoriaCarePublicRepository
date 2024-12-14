package mx.sgahc.service.usuarios.dto;

import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import mx.sgahc.model.usuarios.dto.UsuarioDTORequest;
import mx.sgahc.service.usuarios.RolService;
import mx.sgahc.service.usuarios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDTOServiceImpl implements UsuarioDTOService {
    private final UsuarioService usuarioService;
    private final RolService rolService;

    @Autowired
    public UsuarioDTOServiceImpl(UsuarioService usuarioService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @Override
    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        if (usuario != null) {
            if (usuario.getId() != null)
                usuarioDTO.setId(usuario.getId());
            if (usuario.getUsuario() != null)
                usuarioDTO.setUsuario(usuario.getUsuario());
            if (usuario.getCorreoElectronico() != null)
                usuarioDTO.setCorreoElectronico(usuario.getCorreoElectronico());
            if (usuario.getFechaCreacion() != null)
                usuarioDTO.setFechaCreacion(usuario.getFechaCreacion());
            if (usuario.getRol() != null)
                usuarioDTO.setRol(usuario.getRol().getRol());
            return usuarioDTO;
        }
        return null;
    }

    @Override
    public UsuarioDTORequest requestToDTO(Usuario usuario) {
        UsuarioDTORequest usuarioDTORequest = new UsuarioDTORequest();
        if (usuario != null) {
            if (usuario.getId() != null)
                usuarioDTORequest.setId(usuario.getId());
            if (usuario.getUsuario() != null)
                usuarioDTORequest.setUsuario(usuario.getUsuario());
            if (usuario.getCorreoElectronico() != null)
                usuarioDTORequest.setCorreoElectronico(usuario.getCorreoElectronico());
            if (usuario.getFechaCreacion() != null)
                usuarioDTORequest.setFechaCreacion(usuario.getFechaCreacion());
            if (usuario.getRol() != null)
                usuarioDTORequest.setRolId(usuario.getRol().getId());
            if (usuario.getContrasenia() != null)
                usuarioDTORequest.setContrasenia(usuario.getContrasenia());
            return usuarioDTORequest;
        }
        return null;
    }

    @Override
    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        if (usuarioDTO != null) {
            Usuario usuario = new Usuario();
            if (usuarioDTO.getId() != null)
                usuario.setId(usuarioDTO.getId());
            if (usuarioDTO.getUsuario() != null)
                usuario.setUsuario(usuarioDTO.getUsuario());
            if (usuarioDTO.getCorreoElectronico() != null)
                usuario.setCorreoElectronico(usuarioDTO.getCorreoElectronico());
            if (usuarioDTO.getFechaCreacion() != null)
                usuario.setFechaCreacion(usuarioDTO.getFechaCreacion());
            if (usuarioDTO.getRol() != null)
                usuario.setRol(
                        rolService.getRole(usuarioDTO.getRol())
                );
            return usuario;
        }
        return null;
    }

    @Override
    public Usuario requestToEntity(UsuarioDTORequest usuarioDTO) {
        if (usuarioDTO != null) {
            Usuario usuario = new Usuario();
            if (usuarioDTO.getId() != null)
                usuario.setId(usuarioDTO.getId());
            if (usuarioDTO.getUsuario() != null)
                usuario.setUsuario(usuarioDTO.getUsuario());
            if (usuarioDTO.getCorreoElectronico() != null)
                usuario.setCorreoElectronico(usuarioDTO.getCorreoElectronico());
            if (usuarioDTO.getFechaCreacion() != null)
                usuario.setFechaCreacion(usuarioDTO.getFechaCreacion());
            if (usuarioDTO.getRolId() != null)
                usuario.setRol(
                        rolService.getRolById(usuarioDTO.getRolId())
                );
            if (usuarioDTO.getContrasenia() != null)
                usuario.setContrasenia(usuarioDTO.getContrasenia());
            return usuario;
        }
        return null;
    }

    @Override
    public UsuarioDTO findByEmail(String email) {
        Usuario usuario = usuarioService.findByEmail(email);

        return this.toDTO(usuario);
    }

    @Override
    public void saveUser(UsuarioDTO usuario) {
        Usuario usuarioNuevo = this.toEntity(usuario);
        usuarioService.saveUser(usuarioNuevo);
    }

    @Override
    public void saveUserRequest(UsuarioDTORequest usuarioDTO) {
        Usuario usuario = this.requestToEntity(usuarioDTO);
        usuarioService.saveUser(usuario);
    }

    @Override
    public boolean usuarioTieneDatosPersonales(Integer id) {
        return usuarioService.usuarioTieneDatosPersonales(id);
    }

    @Override
    public UsuarioDTO encontrarPorID(Integer id) {
        Usuario usuario = usuarioService.encontrarPorID(id);
        return this.toDTO(usuario);
    }
}
