package mx.sgahc.service.tratamientos.dto;

import mx.sgahc.model.enfermedades.Medicamento;
import mx.sgahc.model.enfermedades.dto.MedicamentoDTOResponse;
import mx.sgahc.repository.enfermedades.MedicamentoRepository;
import mx.sgahc.service.tratamientos.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoDTOServiceImpl implements MedicamentoDTOService {
    private final MedicamentoRepository medicamentoRepository;
    private final MedicamentoService medicamentoService;

    @Autowired
    public MedicamentoDTOServiceImpl(MedicamentoRepository medicamentoRepository, MedicamentoService medicamentoService) {
        this.medicamentoRepository = medicamentoRepository;
        this.medicamentoService = medicamentoService;
    }

    @Override
    public MedicamentoDTOResponse toDTO(Medicamento medicamento) {
        if (medicamento != null) {
            MedicamentoDTOResponse medicamentoDTOResponse = new MedicamentoDTOResponse();
            if (medicamento.getId() != null) {
                medicamentoDTOResponse.setId(medicamento.getId());
            }
            if (medicamento.getNombreGenerico() != null)
                medicamentoDTOResponse.setNombreGenerico(medicamento.getNombreGenerico());
            if (medicamento.getDescripcion() != null)
                medicamentoDTOResponse.setDescripcion(medicamento.getDescripcion());
            if (medicamento.getAdvertencias() != null)
                medicamentoDTOResponse.setAdvertencias(medicamento.getAdvertencias());
            return medicamentoDTOResponse;
        }
        return null;
    }

    @Override
    public Medicamento toEntity(MedicamentoDTOResponse medicamentoDTOResponse) {
        if (medicamentoDTOResponse != null) {
            Medicamento medicamento = new Medicamento();
            if (medicamentoDTOResponse.getId() != null)
                medicamento.setId(medicamentoDTOResponse.getId());
            if (medicamentoDTOResponse.getNombreGenerico() != null)
                medicamento.setNombreGenerico(medicamentoDTOResponse.getNombreGenerico());
            if (medicamentoDTOResponse.getDescripcion() != null)
                medicamento.setDescripcion(medicamentoDTOResponse.getDescripcion());
            if (medicamentoDTOResponse.getAdvertencias() != null)
                medicamento.setAdvertencias(medicamentoDTOResponse.getAdvertencias());
            return medicamento;
        }
        return null;
    }

    @Override
    public List<MedicamentoDTOResponse> getAllMedicamentos() {
        return medicamentoService.getAllMedicamentos().stream().map(this::toDTO).toList();
    }

    @Override
    public MedicamentoDTOResponse getMedicamentoById(Integer id) {
        return this.toDTO(medicamentoService.getMedicamentoById(id));
    }
}
