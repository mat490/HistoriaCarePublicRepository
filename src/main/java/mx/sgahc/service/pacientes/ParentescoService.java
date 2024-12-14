package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.familiares.Parentesco;

import java.util.List;

public interface ParentescoService {
    Parentesco getParentescoById(Integer id);
    List<Parentesco> getAllParentesco();
}
