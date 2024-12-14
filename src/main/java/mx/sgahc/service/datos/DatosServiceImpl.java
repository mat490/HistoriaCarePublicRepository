package mx.sgahc.service.datos;

import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.repository.datos.DatosPersonalesRepository;
import mx.sgahc.repository.datos.DireccionRepository;
import mx.sgahc.repository.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatosServiceImpl implements DatosService{
    @Autowired
    DatosPersonalesRepository datosPersonalesRepository;
    @Autowired
    DireccionRepository direccionRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<DatosPersonales> getDatosPersonales() {
        return datosPersonalesRepository.findAll();
    }

    @Override
    public DatosPersonales getDatosPersonales(Integer id) {
        return datosPersonalesRepository.findById(id).orElse(null);
    }

    @Override
    public DatosPersonales getDatosPersonalesByUsuarioId(Integer id) {

        return datosPersonalesRepository.findDatosPersonalesByUsuarioId(id);
    }

    @Override
    public void saveDatosPersonales(DatosPersonales datosPersonales) {
        datosPersonalesRepository.save(datosPersonales);
    }

    @Override
    public void deleteDatosPersonales(Integer id) {
        datosPersonalesRepository.deleteById(id);
    }
}
