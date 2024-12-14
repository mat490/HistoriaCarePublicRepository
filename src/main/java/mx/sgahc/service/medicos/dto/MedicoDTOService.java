package mx.sgahc.service.medicos.dto;

import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.medicos.Especialidad;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.medicos.dto.MedicoDTO;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.model.pacientes.dto.PacienteDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicoDTOService {
    MedicoDTO toDTO(Medico medico);
    Medico toEntity(MedicoDTO medicoDTO);
    void saveMedico(MedicoDTO medico);
    MedicoDTO getMedicoByUsuarioId(Integer id);
    Page<Cita> getCitas(Integer id, Pageable pageable);
    Page<MedicoDTO> getMedicosEspecialidadYEstatod(Integer especialidad, String estado, Pageable pageable);
    Page<MedicoDTO> getMedicosByPacienteId(Integer id, Pageable pageable);
    List<MedicoDTO> getMedicosByPacienteIdList(Integer id);

}
