package mx.sgahc.service.tratamientos;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.enfermedades.Enfermedad;
import mx.sgahc.repository.enfermedades.EnferemdadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EnfermedadServiceImpl implements EnfermedadService {
    private final EnferemdadRepository enferemdadRepository;

    @Autowired
    public EnfermedadServiceImpl(EnferemdadRepository enferemdadRepository) {
        this.enferemdadRepository = enferemdadRepository;
    }

    @Override
    public Enfermedad getEnfermedadById(Integer id) {
        return enferemdadRepository.findById(id).orElse(null);
    }

    @Override
    public Enfermedad getEnfermedadByNombre(String nombre) {
        return enferemdadRepository.findByEnfermedad(nombre);
    }

    @Override
    public List<Enfermedad> getEnfermedades() {
        return enferemdadRepository.findAll();
    }

    @Override
    public Enfermedad saveEnfermedad(Enfermedad enfermedad) {
        return enferemdadRepository.save(enfermedad);
    }

    @Override
    public void deleteEnfermedadById(Enfermedad enfermedad) {
        enferemdadRepository.delete(enfermedad);
    }

    @Override
    public void updateEnfermedadById(Enfermedad enfermedad, Integer id) {
        enferemdadRepository.findById(id).ifPresent(enfemedadExistente -> enferemdadRepository.save(enfermedad));
    }
}
