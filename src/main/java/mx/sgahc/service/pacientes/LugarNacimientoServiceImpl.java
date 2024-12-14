package mx.sgahc.service.pacientes;

import mx.sgahc.model.datos.LugarNacimiento;
import mx.sgahc.repository.datos.LugarNacimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LugarNacimientoServiceImpl implements LugarNacimientoService {
    private final LugarNacimientoRepository lugarNacimientoRepository;

    @Autowired
    public LugarNacimientoServiceImpl(LugarNacimientoRepository lugarNacimientoRepository) {
        this.lugarNacimientoRepository = lugarNacimientoRepository;
    }

    @Override
    public LugarNacimiento getLugarNacimientoById(Integer id) {
        return lugarNacimientoRepository.findById(id).orElse(null);
    }

    @Override
    public List<LugarNacimiento> getAllLugarNacimiento() {
        return lugarNacimientoRepository.findAll();
    }

    @Override
    public LugarNacimiento saveLugarNacimiento(LugarNacimiento lugarNacimiento) {
        return lugarNacimientoRepository.save(lugarNacimiento);
    }
}
