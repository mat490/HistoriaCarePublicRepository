package mx.sgahc.service.pacientes;

import lombok.AllArgsConstructor;
import mx.sgahc.model.pacientes.GrupoSanguineo;
import mx.sgahc.repository.pacientes.GrupoSanguineoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoSanguineoImpl implements GrupoSanguineoService{
    private final GrupoSanguineoRepository grupoSanguineoRepository;

    @Autowired
    public GrupoSanguineoImpl(GrupoSanguineoRepository grupoSanguineoRepository) {
        this.grupoSanguineoRepository = grupoSanguineoRepository;
    }

    @Override
    public GrupoSanguineo getGrupoSanguineoById(Integer id) {
        return grupoSanguineoRepository.findById(id).orElse(null);
    }

    @Override
    public List<GrupoSanguineo> getAllGrupoSanguineo() {
        return grupoSanguineoRepository.findAll();
    }
}
