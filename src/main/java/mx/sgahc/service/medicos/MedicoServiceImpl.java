package mx.sgahc.service.medicos;

import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.medicos.Especialidad;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.repository.medicos.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService{
    @Autowired
    MedicoRepository medicoRepository;


    @Override
    public List<Medico> getMedicos() {
        return List.of();
    }

    @Override
    public Medico getMedico(int id) {

        return medicoRepository.findById(id).orElse(null);
    }

    @Override
    public void saveMedico(Medico medico) {
        medicoRepository.save(medico);
    }

    @Override
    public void updateMedico(Medico medico) {

    }

    @Override
    public void deleteMedico(int id) {

    }

    @Override
    public Medico getMedicoByDatosPersonales(Integer id) {
        return medicoRepository.findByDatosPersonalesId(id).orElse(null);
    }

    @Override
    public Medico getMedicoByUsuarioId(Integer id) {
        return medicoRepository.findByUsuarioId(id).orElse(null);
    }

    @Override
    public Page<Cita> getCitas(Integer id, Pageable pageable) {
        return medicoRepository.buscarCitasPorMedicoId(id, pageable);
    }

    @Override
    public Page<Paciente> getPacientes(Integer id, Pageable pageable) {

        return medicoRepository.buscarPacientesPorMedicoId(id, pageable);
    }

    @Override
    public int getCantidadPacientesMesByMedicoId(Integer id) {
        // Calcular las fechas de inicio y fin usando java.util.Date
        Date fechaFin = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaFin);
        calendar.add(Calendar.MONTH, -1);
        Date fechaInicio = calendar.getTime();

        Integer cantidadPacientes = medicoRepository.obtenerPacientesAtendidosUltimoMesPorMedicoId(id, fechaInicio, fechaFin);
        return cantidadPacientes != null ? cantidadPacientes : 0; // Maneja el caso de resultados nulos
    }

    @Override
    public double getIncrementoUltimoMesByMedicoId(Integer id) {
        // Calcular las fechas de inicio y fin usando java.util.Date
        Date fechaHoy = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaHoy);
        calendar.add(Calendar.MONTH, -1);
        Date mesPasado = calendar.getTime();

        calendar.setTime(fechaHoy);
        calendar.add(Calendar.MONTH, -2);
        Date dosMesPasado = calendar.getTime();

        Integer cantidadPacientesEsteMes =
                medicoRepository.obtenerPacientesAtendidosUltimoMesPorMedicoId(id, mesPasado, fechaHoy);
        Integer cantidadPacientesDosMes =
                medicoRepository.obtenerPacientesAtendidosUltimoMesPorMedicoId(id, dosMesPasado, mesPasado);

        cantidadPacientesEsteMes = cantidadPacientesEsteMes != null ? cantidadPacientesEsteMes : 0;
        cantidadPacientesDosMes = cantidadPacientesDosMes != null ? cantidadPacientesDosMes : 0;

        if (cantidadPacientesDosMes == 0) {
            return cantidadPacientesEsteMes * 100.0; // Si no había pacientes, toda la cantidad es un incremento.
        } else {
            return ((double) (cantidadPacientesEsteMes - cantidadPacientesDosMes) / cantidadPacientesDosMes) * 100;
        }
    }

    @Override
    public Page<Medico> getMedicosEspecialidadYEstatod(Especialidad especialidad, String estado, Pageable pageable) {
        return medicoRepository.buscarMedicosPorEspecialidadYEstado(especialidad, estado, pageable);
    }
}
