package mx.sgahc.service.datos.dto;

import mx.sgahc.model.datos.Sexo;
import mx.sgahc.model.datos.dto.SexoDTO;
import mx.sgahc.service.datos.SexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SexoDTOServiceImpl implements SexoDTOService {
    private final SexoService sexoService;

    @Autowired
    public SexoDTOServiceImpl(SexoService sexoService) {
        this.sexoService = sexoService;
    }

    @Override
    public SexoDTO toDto(Sexo sexo) {
        if (sexo != null) {
            SexoDTO sexoDTO = new SexoDTO();
            if (sexo.getId() != null)
                sexoDTO.setId(sexo.getId());
            if (sexo.getSexo() != null)
                sexoDTO.setSexo(sexo.getSexo());
            return sexoDTO;
        }
        return null;
    }

    @Override
    public Sexo toEntity(SexoDTO sexoDTO) {
        if (sexoDTO != null) {
            Sexo sexo = new Sexo();
            if (sexoDTO.getId() != null)
                sexo.setId(sexoDTO.getId());
            if (sexoDTO.getSexo() != null)
                sexo.setSexo(sexoDTO.getSexo());
            return sexo;

        }
        return null;
    }

    @Override
    public List<SexoDTO> getSexos() {
        return sexoService.getSexos().stream().map(this::toDto).toList();
    }
}
