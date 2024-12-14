package mx.sgahc.service.datos.dto;

import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.datos.dto.DatosPersonalesDTO;
import mx.sgahc.model.datos.dto.DatosPersonalesDTORequest;
import mx.sgahc.service.datos.DatosService;
import mx.sgahc.service.datos.DireccionService;
import mx.sgahc.service.datos.SexoService;
import mx.sgahc.service.usuarios.UsuarioService;
import mx.sgahc.service.usuarios.dto.UsuarioDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatosDTOServiceImpl implements DatosDTOService {
    private final DatosService datosService;
    private final DireccionDTOService direccionDTOService;
    private final DireccionService direccionService;
    private final UsuarioDTOService usuarioDTOService;
    private final UsuarioService usuarioService;
    private final SexoDTOService sexoDTOService;
    private final SexoService sexoService;

    @Autowired
    public DatosDTOServiceImpl(DatosService datosService, DireccionDTOService direccionService, DireccionService direccionService1, UsuarioDTOService usuarioService, UsuarioService usuarioService1, SexoDTOService sexoDTOService, SexoService sexoService) {
        this.datosService = datosService;
        this.direccionDTOService = direccionService;
        this.direccionService = direccionService1;
        this.usuarioDTOService = usuarioService;
        this.usuarioService = usuarioService1;
        this.sexoDTOService = sexoDTOService;
        this.sexoService = sexoService;
    }

    @Override
    public DatosPersonalesDTO toDTO(DatosPersonales datosPersonales) {
        DatosPersonalesDTO dto = new DatosPersonalesDTO();
        if (datosPersonales != null) {
            if (datosPersonales.getId() != null) {
                dto.setId(datosPersonales.getId());
            }
            if (datosPersonales.getNombre() != null) {
                dto.setNombre(datosPersonales.getNombre());
            }
            if (datosPersonales.getNombre2() != null)
                dto.setNombre2(datosPersonales.getNombre2());
            if (datosPersonales.getApellido1() != null)
                dto.setApellido1(datosPersonales.getApellido1());
            if (datosPersonales.getApellido2() != null)
                dto.setApellido2(datosPersonales.getApellido2());
            if (datosPersonales.getFechaNacimiento() != null)
                dto.setFechaNacimiento(datosPersonales.getFechaNacimiento());
            if (datosPersonales.getEdad() != null)
                dto.setEdad(datosPersonales.getEdad());
            if (datosPersonales.getTelefono() != null)
                dto.setTelefono(datosPersonales.getTelefono());
            if (datosPersonales.getDireccion() != null)
                dto.setDireccion(direccionDTOService.toDTO(datosPersonales.getDireccion()));
            if (datosPersonales.getUsuario() != null)
                dto.setUsuario(usuarioDTOService.toDTO(datosPersonales.getUsuario()));
            if (datosPersonales.getSexo() != null)
                dto.setSexo(sexoDTOService.toDto(datosPersonales.getSexo()));

            return dto;

        }
        return null;
    }

    @Override
    public DatosPersonales toEntity(DatosPersonalesDTO datosPersonalesDTO) {
        DatosPersonales entity = new DatosPersonales();
        if(datosPersonalesDTO != null) {
            if (datosPersonalesDTO.getId() != null)
                entity.setId(datosPersonalesDTO.getId());
            if (datosPersonalesDTO.getNombre() != null)
                entity.setNombre(datosPersonalesDTO.getNombre());
            if (datosPersonalesDTO.getNombre2() != null)
                entity.setNombre2(datosPersonalesDTO.getNombre2());
            if (datosPersonalesDTO.getApellido1() != null)
                entity.setApellido1(datosPersonalesDTO.getApellido1());
            if (datosPersonalesDTO.getApellido2() != null)
                entity.setApellido2(datosPersonalesDTO.getApellido2());
            if (datosPersonalesDTO.getFechaNacimiento() != null)
                entity.setFechaNacimiento(datosPersonalesDTO.getFechaNacimiento());
            if (datosPersonalesDTO.getEdad() != null)
                entity.setEdad(datosPersonalesDTO.getEdad());
            if (datosPersonalesDTO.getTelefono() != null)
                entity.setTelefono(datosPersonalesDTO.getTelefono());
            if (datosPersonalesDTO.getDireccion() != null)
                entity.setDireccion(direccionDTOService.toEntity(datosPersonalesDTO.getDireccion()));
            if (datosPersonalesDTO.getUsuario() != null)
                entity.setUsuario(usuarioDTOService.toEntity(datosPersonalesDTO.getUsuario()));
            if (datosPersonalesDTO.getSexo() != null)
                entity.setSexo(sexoDTOService.toEntity(datosPersonalesDTO.getSexo()));

            return entity;
        }
        return null;
    }

    @Override
    public DatosPersonales requestToEntity(DatosPersonalesDTORequest datosPersonalesDTO) {
        if (datosPersonalesDTO != null) {
            DatosPersonales datosPersonales = new DatosPersonales();
            if (datosPersonalesDTO.getId() != null)
                datosPersonales.setId(datosPersonalesDTO.getId());
            if (datosPersonalesDTO.getNombre() != null)
                datosPersonales.setNombre(datosPersonalesDTO.getNombre());
            if (datosPersonalesDTO.getNombre2() != null)
                datosPersonales.setNombre2(datosPersonalesDTO.getNombre2());
            if (datosPersonalesDTO.getApellido1() != null)
                datosPersonales.setApellido1(datosPersonalesDTO.getApellido1());
            if (datosPersonalesDTO.getApellido2() != null)
                datosPersonales.setApellido2(datosPersonalesDTO.getApellido2());
            if (datosPersonalesDTO.getFechaNacimiento() != null)
                datosPersonales.setFechaNacimiento(datosPersonalesDTO.getFechaNacimiento());
            if (datosPersonalesDTO.getEdad() != null)
                datosPersonales.setEdad(datosPersonalesDTO.getEdad());
            if (datosPersonalesDTO.getTelefono() != null)
                datosPersonales.setTelefono(datosPersonalesDTO.getTelefono());
            if (datosPersonalesDTO.getDireccionId() != null)
                datosPersonales.setDireccion(direccionService.getDireccion(datosPersonalesDTO.getSexoId()));
            if (datosPersonalesDTO.getUsuarioId() != null)
                datosPersonales.setUsuario(usuarioService.encontrarPorID(datosPersonalesDTO.getUsuarioId()));
            if (datosPersonalesDTO.getSexoId() != null)
                datosPersonales.setSexo(sexoService.getSexo(datosPersonalesDTO.getSexoId()));
            return datosPersonales;
        }
        return null;
    }

    @Override
    public List<DatosPersonalesDTO> getDatosPersonales() {
        return datosService.getDatosPersonales().stream().map(this::toDTO).toList();
    }

    @Override
    public DatosPersonalesDTO getDatosPersonales(Integer id) {
        return this.toDTO(datosService.getDatosPersonales(id));
    }

    @Override
    public DatosPersonalesDTO getDatosPersonalesByUsuarioId(Integer id) {
        return this.toDTO(datosService.getDatosPersonalesByUsuarioId(id));
    }

    @Override
    public void saveDatosPersonales(DatosPersonalesDTO datosPersonales) {
        DatosPersonales entity = toEntity(datosPersonales);
        datosService.saveDatosPersonales(entity);
    }

    @Override
    public void saveDatosRequestPersonales(DatosPersonalesDTORequest datosPersonales) {
        DatosPersonales entity = this.requestToEntity(datosPersonales);
        datosService.saveDatosPersonales(entity);
    }

    @Override
    public void deleteDatosPersonales(Integer id) {
        datosService.deleteDatosPersonales(id);
    }
}
