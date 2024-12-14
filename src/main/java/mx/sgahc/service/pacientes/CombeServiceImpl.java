package mx.sgahc.service.pacientes;

import mx.sgahc.model.pacientes.Combe;
import mx.sgahc.repository.pacientes.CombeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombeServiceImpl implements CombeService {
    private final CombeRepository combeRepository;

    @Autowired
    public CombeServiceImpl(CombeRepository combeRepository) {
        this.combeRepository = combeRepository;
    }

    @Override
    public Combe getCombeById(Integer id) {
        return combeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Combe> getAllCombe() {
        return combeRepository.findAll();
    }
}
