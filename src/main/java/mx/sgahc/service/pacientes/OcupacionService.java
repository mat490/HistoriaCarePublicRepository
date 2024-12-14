package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.Ocupacion;

import java.util.List;

public interface OcupacionService {
    Ocupacion getOcupacionById(Integer id);
    List<Ocupacion> getOcupaciones();
}
