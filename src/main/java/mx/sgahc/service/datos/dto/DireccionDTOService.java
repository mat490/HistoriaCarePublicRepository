package mx.sgahc.service.datos.dto;

import mx.sgahc.model.datos.Direccion;
import mx.sgahc.model.datos.dto.DireccionDTO;

import java.util.List;
import java.util.Set;

public interface DireccionDTOService {
    DireccionDTO toDTO(Direccion direccion);
    Direccion toEntity(DireccionDTO direccionDTO);
    List<DireccionDTO> getDirecciones();
    Set<DireccionDTO> getSetDirecciones();
    DireccionDTO getDireccion(Integer id);
    DireccionDTO saveDireccion(DireccionDTO direccion);
    void deleteDireccion(Integer id);
}
