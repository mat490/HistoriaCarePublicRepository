package mx.sgahc.service.usuarios.dto;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.usuarios.Rol;
import mx.sgahc.model.usuarios.dto.RolDTO;
import mx.sgahc.service.usuarios.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RolDTOServiceImpl implements RolDTOService {
    private final RolService rolService;

    @Autowired
    public RolDTOServiceImpl(RolService rolService) {
        this.rolService = rolService;
    }

    @Override
    public RolDTO toDTO(Rol rol) {
        if (rol != null) {
            RolDTO rolDTO = new RolDTO();
            if (rol.getId() != null)
                rolDTO.setId(rol.getId());
            if (rol.getRol() != null)
                rolDTO.setRol(rol.getRol());
            if (rol.getDescripcion() != null)
                rolDTO.setDescripcion(rol.getDescripcion());
            return rolDTO;
        }
        return null;
    }

    @Override
    public Rol toEntity(RolDTO rolDTO) {
        if (rolDTO != null) {
            Rol rol = new Rol();
            if (rolDTO.getId() != null)
                rol.setId(rolDTO.getId());
            if (rolDTO.getRol() != null)
                rol.setRol(rolDTO.getRol());
            if (rolDTO.getDescripcion() != null)
                rol.setDescripcion(rolDTO.getDescripcion());
            return rol;
        }
        return null;
    }

    @Override
    public List<RolDTO> getRoles() {
        return rolService.getRoles().stream().map(this::toDTO).toList();
    }

    @Override
    public RolDTO getRole(String name) {
        return this.toDTO(rolService.getRole(name));
    }
}
