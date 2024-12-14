package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.Combe;

import java.util.List;

public interface CombeService {
    Combe getCombeById(Integer id);
    List<Combe> getAllCombe();

}
