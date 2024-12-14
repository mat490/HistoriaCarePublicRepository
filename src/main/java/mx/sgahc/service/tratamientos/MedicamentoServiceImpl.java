package mx.sgahc.service.tratamientos;

import mx.sgahc.model.enfermedades.Medicamento;
import mx.sgahc.repository.enfermedades.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {
    private final MedicamentoRepository medicamentoRepository;

    @Autowired
    public MedicamentoServiceImpl(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public Medicamento getMedicamentoById(Integer id) {
        return medicamentoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Medicamento> getAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    @Override
    public Medicamento saveMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }
}
