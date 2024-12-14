package mx.sgahc.service.usuarios.dto;

import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import mx.sgahc.model.usuarios.dto.UsuarioDTORequest;

public interface UsuarioDTOService {
    UsuarioDTO toDTO(Usuario usuario);
    UsuarioDTORequest requestToDTO(Usuario usuario);
    Usuario toEntity(UsuarioDTO usuarioDTO);
    Usuario requestToEntity(UsuarioDTORequest usuarioDTO);
    UsuarioDTO findByEmail(String email);
    void saveUser(UsuarioDTO usuario);
    void saveUserRequest(UsuarioDTORequest usuarioDTO);
    boolean usuarioTieneDatosPersonales(Integer id);
    public UsuarioDTO encontrarPorID(Integer id);
}
