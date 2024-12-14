package mx.sgahc.controller.directorios;

import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.datos.Direccion;
import mx.sgahc.model.datos.dto.DatosPersonalesDTO;
import mx.sgahc.model.datos.dto.DatosPersonalesDTORequest;
import mx.sgahc.model.datos.dto.DireccionDTO;
import mx.sgahc.model.medicos.Especialidad;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.medicos.dto.EspecialidadDTO;
import mx.sgahc.model.medicos.dto.MedicoDTO;
import mx.sgahc.model.medicos.dto.MedicoDTOFiltroEstado;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.model.pacientes.dto.PacienteDTORequest;
import mx.sgahc.model.pacientes.dto.PacienteDTOResponse;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import mx.sgahc.model.util.RenderPagina;
import mx.sgahc.service.citas.CitaService;
import mx.sgahc.service.citas.dto.CitaDTOService;
import mx.sgahc.service.datos.DatosService;
import mx.sgahc.service.datos.DireccionService;
import mx.sgahc.service.datos.dto.DatosDTOService;
import mx.sgahc.service.datos.dto.DireccionDTOService;
import mx.sgahc.service.medicos.EspecialidadService;
import mx.sgahc.service.medicos.EspecialidadServiceImpl;
import mx.sgahc.service.medicos.MedicoService;
import mx.sgahc.service.medicos.dto.EspecialidadDTOService;
import mx.sgahc.service.medicos.dto.MedicoDTOService;
import mx.sgahc.service.pacientes.PacienteService;
import mx.sgahc.service.pacientes.dto.PacienteDTOService;
import mx.sgahc.service.usuarios.UsuarioService;
import mx.sgahc.service.usuarios.dto.UsuarioDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping("/directorios")
public class DirectoriosController {

    private final MedicoService medicoService;
    private final MedicoDTOService medicoDTOService;
    private final UsuarioService usuarioService;
    private final UsuarioDTOService usuarioDTOService;
    private final PacienteService pacienteService;
    private final PacienteDTOService pacienteDTOService;
    private final CitaService citaService;
    private final CitaDTOService citaDTOService;
    private final DatosService datosService;
    private final DatosDTOService datosDTOService;
    private final EspecialidadService especialidadService;
    private final EspecialidadDTOService especialidadDTOService;
    private final DireccionService direccionService;
    private final DireccionDTOService direccionDTOService;

    @Autowired
    public DirectoriosController(MedicoService medicoService, MedicoDTOService medicoDTOService, UsuarioService usuarioService, UsuarioDTOService usuarioDTOService,
                                 PacienteService pacienteService, PacienteDTOService pacienteDTOService, CitaService citaService, CitaDTOService citaDTOService, DatosService datosService, DatosDTOService datosDTOService,
                                 EspecialidadService especialidadService, EspecialidadDTOService especialidadDTOService, DireccionService direccionService, DireccionDTOService direccionDTOService) {
        this.medicoService = medicoService;
        this.medicoDTOService = medicoDTOService;
        this.usuarioService = usuarioService;
        this.usuarioDTOService = usuarioDTOService;
        this.pacienteService = pacienteService;
        this.pacienteDTOService = pacienteDTOService;
        this.citaService = citaService;
        this.citaDTOService = citaDTOService;
        this.datosService = datosService;
        this.datosDTOService = datosDTOService;
        this.especialidadService = especialidadService;
        this.especialidadDTOService = especialidadDTOService;
        this.direccionService = direccionService;
        this.direccionDTOService = direccionDTOService;
    }

    @GetMapping("/pacientes-medico")
    public String pacientesMedico(Model model,
                                  @RequestParam(name = "page", defaultValue = "0") int page) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);
        model.addAttribute("usuarioNombre", usuarioLog.getUsuario());

        Integer userId = usuarioDTOService.findByEmail(usuarioLog.getCorreoElectronico()).getId();
        Pageable pageable = PageRequest.of(page, 2, Sort.by("datosPersonales").descending());
        if (Objects.equals(usuarioLog.getRol(), "Medico")){
            MedicoDTO medico = medicoDTOService.getMedicoByUsuarioId(userId);

            Page<PacienteDTOResponse> pacientes;
            if (medico != null) {
                pacientes = pacienteDTOService.getPacientesByMedicoId(medico.getId(), pageable);
                model.addAttribute("medicoCompleto", medico);
                model.addAttribute("pacientes", pacientes);
                model.addAttribute("size", pacientes.getTotalElements());
                model.addAttribute("page", new RenderPagina<>("pacientes-medico", pacientes));
                model.addAttribute("tituloPagina", "Pacientes de " + usuarioLog.getUsuario());
            }
        }

        if (Objects.equals(usuarioLog.getRol(), "Paciente")){
            PacienteDTORequest paciente = pacienteDTOService.getPacienteDTORequestByUsuarioId(userId);

            Page<MedicoDTO> medicos;
            if (paciente != null) {
                medicos = medicoDTOService.getMedicosByPacienteId(paciente.getId(), pageable);
                if (medicos.getSize() > 0){
                    model.addAttribute("medicos", medicos);
                    model.addAttribute("size", medicos.getTotalElements());
                    model.addAttribute("tituloPagina", "Medicos de " + usuarioLog.getUsuario());
                    model.addAttribute("page", new RenderPagina<>("medicos-paciente", medicos));
                }

                model.addAttribute("pacienteCompleto",paciente);

            }

        }

        model.addAttribute("usuarioLog", usuarioLog);

        return "directorios/pacientes-medico";


    }
    @GetMapping("/buscar-medicos")
    public String buscarMedicos(Model model,
                                @ModelAttribute ("medicoFiltro") MedicoDTO medicoFiltro){
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);
        model.addAttribute("usuarioNombre", usuarioLog.getUsuario());

        MedicoDTOFiltroEstado medico = new MedicoDTOFiltroEstado();

        List<EspecialidadDTO> especialidades = especialidadDTOService.getAllEspecialidades();
        Set<DireccionDTO> direcciones = direccionDTOService.getSetDirecciones();
        Set<String> estados = new HashSet<>();
        direcciones.forEach(d -> estados.add(d.getEstado()));

        model.addAttribute("tituloPagina", "Buscar Medicos");
        model.addAttribute("medicoFiltro", medico);
        model.addAttribute("selectEsp", especialidades);
        model.addAttribute("selectDirecciones", estados);
        if (medicoFiltro != null){
            System.out.println("Hola");
        }

        return "directorios/buscar-medicos";

    }

    @GetMapping("/filtro-medicos")
    public String filtroMedico(Model model,
                               RedirectAttributes flash,
                               @ModelAttribute("medicoFiltro") MedicoDTOFiltroEstado medicoFiltro,
                               BindingResult result,
                               @RequestParam(name = "page", defaultValue = "0") int page){
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);
        model.addAttribute("usuarioNombre", usuarioLog.getUsuario());
        if (result.hasErrors()) {
            model.addAttribute("errors", "Error al buscar medicos");
            return "directorios/buscar-medicos";
        }

        Pageable pageable = PageRequest.of(page, 2, Sort.by("datosPersonales").descending());
        String estado = medicoFiltro.getEstado();
        Page<MedicoDTO> medicos = medicoDTOService.getMedicosEspecialidadYEstatod(medicoFiltro.getEspecialidadId(),
                estado, pageable);
        model.addAttribute("page", new RenderPagina<>("buscar-medicos", medicos));
        model.addAttribute("medicos", medicos);
        model.addAttribute("size", medicos.getTotalElements());
        model.addAttribute("medicoFiltro", medicoFiltro);
        model.addAttribute("tituloPagina", "Medicos especializados en " +
                especialidadDTOService.getEspecialidadDTOById(medicoFiltro.getEspecialidadId()) + " en " + estado);

        return "directorios/buscar-medicos";
    }
}