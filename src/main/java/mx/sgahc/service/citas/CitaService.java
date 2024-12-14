package mx.sgahc.service.citas;

import mx.sgahc.model.citas.Cita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface CitaService {
    Cita getCita(int id);
    List<Cita> getCitasByMedicoAndFecha(Integer id, Date fecha);
    List<Cita> getCitasByPacienteAndFecha(Integer id, Date fecha);
    List<Cita> getCitasSinDiagnosticoByMedicoId(Integer id);
    Page<Cita> getCitasPendientesByMedicoId(Integer id, Pageable pageable);
    Page<Cita> getCitasPendientesByPacienteId(Integer id, Pageable pageable);
    void saveCita(Cita cita);
    void deleteCita(int id);
    List<Integer> getNumeroCitasPorMesByPacienteId(Integer id);
}
