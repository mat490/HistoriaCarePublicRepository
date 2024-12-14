package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.Rh;

import java.util.List;

public interface RhService {
    Rh getRhById(Integer id);
    List<Rh> getAllRh();

}
