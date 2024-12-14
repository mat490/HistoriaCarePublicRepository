package mx.sgahc.service.datos;

import mx.sgahc.model.datos.Sexo;

import java.util.List;

public interface SexoService {
    List<Sexo> getSexos();
    Sexo getSexo(int id);
}
