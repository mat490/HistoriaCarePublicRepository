package mx.sgahc.service.historias;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.repository.citas.CitaRepository;
import mx.sgahc.repository.citas.DiagnosticoRepository;
import mx.sgahc.repository.enfermedades.TratamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HistoriasServiceImpl implements HistoriasService {
    private final CitaRepository citaRepository;
    private final TratamientoRepository tratamientoRepository;
    private final DiagnosticoRepository diagnosticoRepository;

    @Autowired
    public HistoriasServiceImpl(CitaRepository citaRepository, TratamientoRepository tratamientoRepository, DiagnosticoRepository diagnosticoRepository) {
        this.citaRepository = citaRepository;
        this.tratamientoRepository = tratamientoRepository;
        this.diagnosticoRepository = diagnosticoRepository;
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

    @Override
    public List<String> getMedicamentosUltimosSeisMeses(Integer id) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date fechaHaceSeisMeses = calendar.getTime();

        return tratamientoRepository.obtenerMedicamentosUltimosSeisMeses(fechaHaceSeisMeses, id);
    }

    @Override
    public List<Integer> getCantidadTratamientosUltimosSeisMeses(Integer id) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date fechaHaceSeisMeses = calendar.getTime();

        // Obtiene los resultados de la consulta
        List<Object[]> resultados = tratamientoRepository.obtenerCantidadMedicamentosUltimosSeisMeses(fechaHaceSeisMeses, id);

        // Lista para almacenar las cantidades de medicamentos recetados
        List<Integer> cantidades = new ArrayList<>();

        log.info("Cantidad de tratamientos: {}", resultados.toString());
        for (Object[] resultado : resultados) {
            // Recupera la cantidad recetada de cada medicamento
            long cantidadRecetada = ((Number) resultado[1]).longValue();
            cantidades.add((int) cantidadRecetada);
        }

        return cantidades;
}

    @Override
    public List<String> getEnfermedadesUltimoAnio(Integer id) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date fechaHaceSeisMeses = calendar.getTime();

        return diagnosticoRepository.findEnfermedadesPorFechaAnterior(id, fechaHaceSeisMeses);
    }

    @Override
    public List<Integer> getCantidadEnfermedadesUltimoAnio(Integer id) {
        // Fecha de inicio (un año atrás)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Date fechaInicio = calendar.getTime();

        // Ejecutar la consulta
        List<Object[]> resultados = diagnosticoRepository.obtenerCantidadDeDiagnosticosEnfermedadByPacienteId(fechaInicio, id);

        // Transformar resultados: extraer solo las cantidades
        List<Integer> cantidades = resultados.stream()
                .map(resultado -> ((Number) resultado[1]).intValue()) // Convertir el segundo campo (cantidad) a Integer
                .collect(Collectors.toList());

        return cantidades;
    }
}
