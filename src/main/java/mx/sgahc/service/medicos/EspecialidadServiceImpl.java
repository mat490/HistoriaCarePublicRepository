package mx.sgahc.service.medicos;

import mx.sgahc.model.medicos.Especialidad;
import mx.sgahc.repository.medicos.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {
    @Autowired
    EspecialidadRepository especialidadRepository;

    @Override
    public Especialidad getEspecialidadById(int id) {
        return especialidadRepository.findById(id).orElse(null);
    }

    @Override
    public List<Especialidad> getAllEspecialidades() {
        return especialidadRepository.findAll();
    }
}
