package mx.sgahc.service.usuarios.dto;

import mx.sgahc.model.usuarios.Rol;
import mx.sgahc.model.usuarios.dto.RolDTO;

import java.util.List;

public interface RolDTOService {
    RolDTO toDTO(Rol rol);
    Rol toEntity(RolDTO rolDTO);
    List<RolDTO> getRoles();
    RolDTO getRole(String name);
}
