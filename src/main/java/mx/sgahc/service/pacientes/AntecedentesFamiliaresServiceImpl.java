package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.familiares.AntecedenteFamiliar;
import mx.sgahc.repository.pacientes.familiares.AntecedenteFamiliarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AntecedentesFamiliaresServiceImpl implements AntecedenteFamiliarService{
    private final AntecedenteFamiliarRepository antecedenteFamiliarRepository;

    @Autowired
    public AntecedentesFamiliaresServiceImpl(AntecedenteFamiliarRepository antecedenteFamiliarRepository) {
        this.antecedenteFamiliarRepository = antecedenteFamiliarRepository;
    }

    @Override
    public AntecedenteFamiliar findAntecedenteFamiliarById(Integer id) {
        return antecedenteFamiliarRepository.findById(id).orElse(null);
    }

    @Override
    public List<AntecedenteFamiliar> findAllAntecedenteFamiliar() {
        return antecedenteFamiliarRepository.findAll();
    }

    @Override
    public Page<AntecedenteFamiliar> findAntecedentesByPacienteId(Integer id, Pageable pageable) {
        return antecedenteFamiliarRepository.findAntecedenteFamiliarByPaciente_Id(id, pageable);
    }

    @Override
    public AntecedenteFamiliar updateAntecedenteFamiliar(Integer antecedenteid, AntecedenteFamiliar antecedenteFamiliar) {
        AntecedenteFamiliar antecedenteExistente = antecedenteFamiliarRepository.findById(antecedenteid).orElse(null);
        if (antecedenteExistente != null) {
            antecedenteFamiliar.setId(antecedenteid);
            antecedenteFamiliarRepository.save(antecedenteFamiliar);
            return antecedenteFamiliar;
        }
        return null;
    }

    @Override
    public void deleteAntecedenteFamiliar(Integer antecedenteid) {
        antecedenteFamiliarRepository.deleteById(antecedenteid);
    }

    @Override
    public void saveAntecedenteFamiliar(AntecedenteFamiliar antecedenteFamiliar) {
        antecedenteFamiliarRepository.save(antecedenteFamiliar);
    }
}
