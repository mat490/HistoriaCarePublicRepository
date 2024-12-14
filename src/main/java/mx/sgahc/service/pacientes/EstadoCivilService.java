package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.EstadoCivil;

import java.util.List;

public interface EstadoCivilService {
    EstadoCivil getEstadoCivilById(Integer id);
    List<EstadoCivil> getAllEstadoCivil();
}
