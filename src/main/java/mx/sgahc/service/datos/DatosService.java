package mx.sgahc.service.datos;

import mx.sgahc.model.datos.DatosPersonales;

import java.util.List;

public interface DatosService {
    List<DatosPersonales> getDatosPersonales();
    DatosPersonales getDatosPersonales(Integer id);
    DatosPersonales getDatosPersonalesByUsuarioId(Integer id);
    void saveDatosPersonales(DatosPersonales datosPersonales);
    void deleteDatosPersonales(Integer id);
}
