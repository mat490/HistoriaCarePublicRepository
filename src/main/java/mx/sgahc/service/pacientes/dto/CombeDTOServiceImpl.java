package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.Combe;
import mx.sgahc.model.pacientes.dto.CombeDTO;
import mx.sgahc.service.pacientes.CombeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombeDTOServiceImpl implements CombeDTOService {
    private final CombeService combeService;
    @Autowired
    public CombeDTOServiceImpl(CombeService combeService) {
        this.combeService = combeService;
    }

    @Override
    public CombeDTO toDto(Combe combe) {
        if (combe != null) {
            CombeDTO combeDTO = new CombeDTO();
            if (combe.getId() != null) {
                combeDTO.setId(combe.getId());
            }
            if (combe.getCombe() != null)
                combeDTO.setCombe(combe.getCombe());
            return combeDTO;
        }
        return null;
    }

    @Override
    public Combe toEntity(CombeDTO combeDTO) {
        if (combeDTO != null) {
            Combe combe = new Combe();
            if (combeDTO.getId() != null) {
                combe.setId(combeDTO.getId());
            }
            if (combeDTO.getCombe() != null)
                combe.setCombe(combeDTO.getCombe());
            return combe;
        }
        return null;
    }

    @Override
    public CombeDTO getCombeById(Integer id) {
        return this.toDto(combeService.getCombeById(id));
    }

    @Override
    public List<CombeDTO> getAllCombe() {
        return combeService.getAllCombe().stream().map(this::toDto).toList();
    }
}
