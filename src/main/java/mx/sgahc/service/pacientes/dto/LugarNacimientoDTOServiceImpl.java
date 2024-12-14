package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.datos.LugarNacimiento;
import mx.sgahc.model.datos.dto.LugarNacimientoDTO;
import mx.sgahc.service.pacientes.LugarNacimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LugarNacimientoDTOServiceImpl implements LugarNacimientoDTOService {
    private final LugarNacimientoService lugarNacimientoService;
    @Autowired
    public LugarNacimientoDTOServiceImpl(LugarNacimientoService lugarNacimientoService) {
        this.lugarNacimientoService = lugarNacimientoService;
    }

    @Override
    public LugarNacimientoDTO toDto(LugarNacimiento lugarNacimiento) {
        LugarNacimientoDTO dto = new LugarNacimientoDTO();
        if (lugarNacimiento != null) {
            if (lugarNacimiento.getId() != null) {
                dto.setId(lugarNacimiento.getId());
            }
            if (lugarNacimiento.getPais() != null)
                dto.setPais(lugarNacimiento.getPais());
            if (lugarNacimiento.getEstado() != null)
                dto.setEstado(lugarNacimiento.getEstado());
            if (lugarNacimiento.getMunicipio() != null)
                dto.setMunicipio(lugarNacimiento.getMunicipio());
            return dto;
        }
        return null;
    }

    @Override
    public LugarNacimiento toEntity(LugarNacimientoDTO lugarNacimientoDTO) {
        LugarNacimiento entity = new LugarNacimiento();
        if (lugarNacimientoDTO != null) {
            if (lugarNacimientoDTO.getId() != null) {
                entity.setId(lugarNacimientoDTO.getId());
            }
            if (lugarNacimientoDTO.getPais() != null)
                entity.setPais(lugarNacimientoDTO.getPais());
            if (lugarNacimientoDTO.getEstado() != null)
                entity.setEstado(lugarNacimientoDTO.getEstado());
            if (lugarNacimientoDTO.getMunicipio() != null)
                entity.setMunicipio(lugarNacimientoDTO.getMunicipio());
            return entity;
        }
        return null;
    }

    @Override
    public LugarNacimientoDTO getLugarNacimientoById(Integer id) {
        return this.toDto(lugarNacimientoService.getLugarNacimientoById(id));
    }

    @Override
    public List<LugarNacimientoDTO> getAllLugarNacimiento() {
        return lugarNacimientoService.getAllLugarNacimiento().stream().map(this::toDto).toList();
    }

    @Override
    public LugarNacimientoDTO saveLugarNacimiento(LugarNacimientoDTO lugarNacimiento) {
            LugarNacimiento entity = this.toEntity(lugarNacimiento);
            return this.toDto( lugarNacimientoService.saveLugarNacimiento(entity));

    }
}
