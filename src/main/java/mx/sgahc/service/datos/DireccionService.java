package mx.sgahc.service.datos;

import mx.sgahc.model.datos.Direccion;

import java.util.List;
import java.util.Set;

public interface DireccionService {
    List<Direccion> getDirecciones();
    Set<Direccion> getSetDirecciones();
    Direccion getDireccion(Integer id);
    Direccion saveDireccion(Direccion direccion);
    void deleteDireccion(Integer id);

}
