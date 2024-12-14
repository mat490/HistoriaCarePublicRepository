package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.dto.ParentescoDTOResponse;
import mx.sgahc.model.pacientes.familiares.Parentesco;
import mx.sgahc.service.pacientes.ParentescoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentescoDTOServiceImpl implements ParentescoDTOService {
    private final ParentescoService parentescoService;

    @Autowired
    public ParentescoDTOServiceImpl(ParentescoService parentescoService) {
        this.parentescoService = parentescoService;
    }

    @Override
    public ParentescoDTOResponse toDTO(Parentesco parentesco) {
        if (parentesco != null) {
            ParentescoDTOResponse dto = new ParentescoDTOResponse();
            if (parentesco.getId() != null)
                dto.setId(parentesco.getId());
            if (parentesco.getParentesco() != null)
                dto.setParentesco(parentesco.getParentesco());
            return dto;
        }
        return null;
    }

    @Override
    public Parentesco toEntity(ParentescoDTOResponse parentescoDTOResponse) {
        if (parentescoDTOResponse != null) {
            Parentesco parentesco = new Parentesco();
            if (parentescoDTOResponse.getId() != null)
                parentesco.setId(parentescoDTOResponse.getId());
            if (parentescoDTOResponse.getParentesco() != null)
                parentesco.setParentesco(parentescoDTOResponse.getParentesco());
            return parentesco;
        }
        return null;
    }

    @Override
    public ParentescoDTOResponse getParentescoById(Integer id) {
        return this.toDTO(parentescoService.getParentescoById(id));
    }

    @Override
    public List<ParentescoDTOResponse> getAllParentesco() {
        return parentescoService.getAllParentesco().stream().map(this::toDTO).toList();
    }
}
