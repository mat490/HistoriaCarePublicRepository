package mx.sgahc.service.citas;

import mx.sgahc.model.citas.Cita;
import mx.sgahc.repository.citas.CitaRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {
    @Autowired
    CitaRepository citaRepository;
    @Autowired
    CitaPublisher citaPublisher;


    @Override
    public Cita getCita(int id) {
        return citaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cita> getCitasByMedicoAndFecha(Integer id, Date fecha) {
        return citaRepository.findCitasByMedicoAndFecha(id, fecha);
    }

    @Override
    public List<Cita> getCitasByPacienteAndFecha(Integer id, Date fecha) {

        return citaRepository.findCitasByPacienteAndFecha(id, fecha);
    }

    @Override
    public List<Cita> getCitasSinDiagnosticoByMedicoId(Integer id) {
        return citaRepository.findCitasPorMedicoSinDiagnostico(id);
    }

    @Override
    public Page<Cita> getCitasPendientesByMedicoId(Integer id, Pageable pageable) {
        return citaRepository.findCitasPendientesByMedicoId(id, pageable);
    }

    @Override
    public Page<Cita> getCitasPendientesByPacienteId(Integer id, Pageable pageable) {
        return citaRepository.findCitasPendientesByPacienteId(id, pageable);
    }

    @Override
    public void saveCita(Cita cita) {
        citaRepository.save(cita);
        citaPublisher.alert("Se agendó una nueva cita", cita);
    }

    @Override
    public void deleteCita(int id) {
        Cita cita = citaRepository.findById(id).orElse(null);
        if (cita != null){
            citaRepository.deleteById(id);
            citaPublisher.alert("Se canceló su cita", cita);
        }


    }

    @Override
    public List<Integer> getNumeroCitasPorMesByPacienteId(Integer id) {
        Integer anioActual = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        List<Object[]> resultados =  citaRepository.contarCitasPorMesPorPaciente(id, anioActual);
        Integer[] citasPorMes = new Integer[12];

        Arrays.fill(citasPorMes, 0);

        for (Object[] resultado : resultados) {
            int mes = ((Number) resultado[0]).intValue() - 1;
            long numeroCitas = ((Number) resultado[1]).longValue();
            citasPorMes[mes] = (int) numeroCitas;
        }

        return Arrays.asList(citasPorMes);
    }
}
