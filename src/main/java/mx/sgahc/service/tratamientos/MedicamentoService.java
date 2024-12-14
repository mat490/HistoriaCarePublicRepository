package mx.sgahc.service.tratamientos;

import mx.sgahc.model.enfermedades.Medicamento;

import java.util.List;

public interface MedicamentoService {
    Medicamento getMedicamentoById(Integer id);
    List<Medicamento> getAllMedicamentos();
    Medicamento saveMedicamento(Medicamento medicamento);
}
