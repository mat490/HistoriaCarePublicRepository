package mx.sgahc.service.datos;

import mx.sgahc.model.datos.Direccion;
import mx.sgahc.repository.datos.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DireccionServiceImpl implements DireccionService {
    @Autowired
    DireccionRepository direccionRepository;


    @Override
    public List<Direccion> getDirecciones() {
        return direccionRepository.findAll();
    }

    @Override
    public Set<Direccion> getSetDirecciones() {
        return new HashSet<>(direccionRepository.findAll());
    }

    @Override
    public Direccion getDireccion(Integer id) {
        return direccionRepository.findById(id).orElse(null);
    }

    @Override
    public Direccion saveDireccion(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    @Override
    public void deleteDireccion(Integer id) {
        direccionRepository.deleteById(id);

    }
}
