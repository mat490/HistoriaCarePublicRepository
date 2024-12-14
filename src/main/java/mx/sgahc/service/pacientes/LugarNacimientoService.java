package mx.sgahc.service.pacientes;

import mx.sgahc.model.datos.LugarNacimiento;
import mx.sgahc.model.datos.dto.LugarNacimientoDTO;

import java.util.List;

public interface LugarNacimientoService {
    LugarNacimiento getLugarNacimientoById(Integer id);
    List<LugarNacimiento> getAllLugarNacimiento();
    LugarNacimiento saveLugarNacimiento(LugarNacimiento lugarNacimiento);
}
