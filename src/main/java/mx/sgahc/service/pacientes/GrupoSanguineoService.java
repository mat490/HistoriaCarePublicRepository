package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.EstadoCivil;
import mx.sgahc.model.pacientes.GrupoSanguineo;

import java.util.List;

public interface GrupoSanguineoService {
     GrupoSanguineo getGrupoSanguineoById(Integer id);
     List<GrupoSanguineo> getAllGrupoSanguineo();
}
