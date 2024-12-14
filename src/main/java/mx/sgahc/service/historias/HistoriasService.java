package mx.sgahc.service.historias;

import java.util.List;

public interface HistoriasService {
    List<Integer> getNumeroCitasPorMesByPacienteId(Integer id);
    List<String> getMedicamentosUltimosSeisMeses(Integer id);
    List<Integer> getCantidadTratamientosUltimosSeisMeses(Integer id);
    List<String> getEnfermedadesUltimoAnio(Integer id);
    List<Integer> getCantidadEnfermedadesUltimoAnio(Integer id);
}
