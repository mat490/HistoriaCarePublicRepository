package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.Rh;
import mx.sgahc.model.pacientes.dto.RhDTO;
import mx.sgahc.service.pacientes.RhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RhDTOServiceImpl implements RhDTOService {
    private final RhService rhService;

    @Autowired
    public RhDTOServiceImpl(RhService rhService) {
        this.rhService = rhService;
    }

    @Override
    public RhDTO toDTO(Rh rh) {
        if (rh != null) {
            RhDTO rhDTO = new RhDTO();
            if (rh.getId() != null)
                rhDTO.setId(rh.getId());
            if (rh.getRh() != null)
                rhDTO.setRh(rh.getRh());
            return rhDTO;
        }
        return null;
    }

    @Override
    public Rh toEntity(RhDTO rhDTO) {
        if (rhDTO != null) {
            Rh rh = new Rh();
            if (rhDTO.getId() != null)
                rh.setId(rhDTO.getId());
            if (rhDTO.getRh() != null)
                rh.setRh(rhDTO.getRh());
            return rh;
        }
        return null;
    }

    @Override
    public RhDTO getRhById(Integer id) {
        return this.toDTO(rhService.getRhById(id));
    }

    @Override
    public List<RhDTO> getAllRh() {
        return rhService.getAllRh().stream().map(this::toDTO).toList();
    }
}
