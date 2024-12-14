package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.datos.LugarNacimiento;
import mx.sgahc.model.datos.dto.LugarNacimientoDTO;

import java.util.List;

public interface LugarNacimientoDTOService {
    LugarNacimientoDTO toDto(LugarNacimiento lugarNacimiento);
    LugarNacimiento toEntity(LugarNacimientoDTO lugarNacimientoDTO);
    LugarNacimientoDTO getLugarNacimientoById(Integer id);
    List<LugarNacimientoDTO> getAllLugarNacimiento();
    LugarNacimientoDTO saveLugarNacimiento(LugarNacimientoDTO lugarNacimiento);
}
