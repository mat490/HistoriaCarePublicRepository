package mx.sgahc.service.datos.dto;

import mx.sgahc.model.datos.Direccion;
import mx.sgahc.model.datos.dto.DireccionDTO;
import mx.sgahc.service.datos.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DireccionDTOServiceImpl implements DireccionDTOService {
    private final DireccionService direccionService;

    @Autowired
    public DireccionDTOServiceImpl(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @Override
    public DireccionDTO toDTO(Direccion direccion) {
        DireccionDTO dto = new DireccionDTO();
        if (direccion != null) {
            if (direccion.getId() != null)
                dto.setId(direccion.getId());
            if (direccion.getPais() != null)
                dto.setPais(direccion.getPais());
            if (direccion.getEstado() != null)
                dto.setEstado(direccion.getEstado());
            if (direccion.getMunicipio() != null)
                dto.setMunicipio(direccion.getMunicipio());
            if (direccion.getColonia() != null)
                dto.setColonia(direccion.getColonia());
            if (direccion.getCalle() != null)
                dto.setCalle(direccion.getCalle());
            if (direccion.getCodigoPostal() != null)
                dto.setCodigoPostal(direccion.getCodigoPostal());
            if (direccion.getNumeroCasa() != null)
                dto.setNumeroCasa(direccion.getNumeroCasa());
            return dto;

        }
        return null;
    }

    @Override
    public Direccion toEntity(DireccionDTO direccionDTO) {
        Direccion entity = new Direccion();
        if (direccionDTO != null) {
            if (direccionDTO.getId() != null)
                entity.setId(direccionDTO.getId());
            if (direccionDTO.getPais() != null)
                entity.setPais(direccionDTO.getPais());
            if (direccionDTO.getEstado() != null)
                entity.setEstado(direccionDTO.getEstado());
            if (direccionDTO.getMunicipio() != null)
                entity.setMunicipio(direccionDTO.getMunicipio());
            if (direccionDTO.getColonia() != null)
                entity.setColonia(direccionDTO.getColonia());
            if (direccionDTO.getCalle() != null)
                entity.setCalle(direccionDTO.getCalle());
            if (direccionDTO.getCodigoPostal() != null)
                entity.setCodigoPostal(direccionDTO.getCodigoPostal());
            if (direccionDTO.getNumeroCasa() != null)
                entity.setNumeroCasa(direccionDTO.getNumeroCasa());
            return entity;
        }
        return null;
    }

    @Override
    public List<DireccionDTO> getDirecciones() {
        return direccionService.getDirecciones().stream().map(this::toDTO).toList();
    }

    @Override
    public Set<DireccionDTO> getSetDirecciones() {
        return direccionService.getSetDirecciones().stream().map(this::toDTO).collect(Collectors.toSet());
    }

    @Override
    public DireccionDTO getDireccion(Integer id) {
        return this.toDTO(direccionService.getDireccion(id));
    }

    @Override
    public DireccionDTO saveDireccion(DireccionDTO direccion) {
        Direccion entity = this.toEntity(direccion);
        return toDTO(direccionService.saveDireccion(entity));
    }

    @Override
    public void deleteDireccion(Integer id) {
        direccionService.deleteDireccion(id);
    }
}
