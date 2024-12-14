package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.familiares.Parentesco;
import mx.sgahc.repository.pacientes.familiares.ParentescoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentescoServiceImpl implements ParentescoService {
    private final ParentescoRepository parentescoRepository;

    @Autowired
    public ParentescoServiceImpl(ParentescoRepository parentescoRepository) {
        this.parentescoRepository = parentescoRepository;
    }

    @Override
    public Parentesco getParentescoById(Integer id) {
        return parentescoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Parentesco> getAllParentesco() {
        return parentescoRepository.findAll();
    }
}
