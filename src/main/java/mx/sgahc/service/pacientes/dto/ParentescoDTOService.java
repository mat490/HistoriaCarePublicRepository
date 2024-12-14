package mx.sgahc.service.pacientes.dto;

import mx.sgahc.model.pacientes.dto.ParentescoDTOResponse;
import mx.sgahc.model.pacientes.familiares.Parentesco;

import java.util.List;

public interface ParentescoDTOService {
    ParentescoDTOResponse toDTO(Parentesco parentesco);
    Parentesco toEntity(ParentescoDTOResponse parentescoDTOResponse);
    ParentescoDTOResponse getParentescoById(Integer id);
    List<ParentescoDTOResponse> getAllParentesco();
}
