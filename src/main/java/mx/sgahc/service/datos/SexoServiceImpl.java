package mx.sgahc.service.datos;

import mx.sgahc.model.datos.Sexo;
import mx.sgahc.repository.datos.SexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SexoServiceImpl implements SexoService{
    @Autowired
    SexoRepository sexoRepository;

    @Override
    public List<Sexo> getSexos() {
        return sexoRepository.findAll();
    }

    @Override
    public Sexo getSexo(int id) {
        return sexoRepository.findById(id).orElse(null);
    }
}
