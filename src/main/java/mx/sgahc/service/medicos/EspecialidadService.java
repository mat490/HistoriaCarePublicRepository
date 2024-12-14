package mx.sgahc.service.medicos;

import mx.sgahc.model.medicos.Especialidad;

import java.util.List;

public interface EspecialidadService {
    Especialidad getEspecialidadById(int id);
    List<Especialidad> getAllEspecialidades();
}
