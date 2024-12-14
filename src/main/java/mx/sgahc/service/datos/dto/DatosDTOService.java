package mx.sgahc.service.datos.dto;

import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.datos.dto.DatosPersonalesDTO;
import mx.sgahc.model.datos.dto.DatosPersonalesDTORequest;

import java.util.List;

public interface DatosDTOService {
    DatosPersonalesDTO toDTO(DatosPersonales datosPersonales);
    DatosPersonales toEntity(DatosPersonalesDTO datosPersonalesDTO);
    DatosPersonales requestToEntity(DatosPersonalesDTORequest datosPersonalesDTO);
    List<DatosPersonalesDTO> getDatosPersonales();
    DatosPersonalesDTO getDatosPersonales(Integer id);
    DatosPersonalesDTO getDatosPersonalesByUsuarioId(Integer id);
    void saveDatosPersonales(DatosPersonalesDTO datosPersonales);
    void saveDatosRequestPersonales(DatosPersonalesDTORequest datosPersonales);
    void deleteDatosPersonales(Integer id);
}
