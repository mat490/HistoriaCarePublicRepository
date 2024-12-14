package mx.sgahc.controller.recetas;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.citas.Diagnostico;
import mx.sgahc.model.citas.dto.DiagnosticoDTORequest;
import mx.sgahc.model.citas.dto.DiagnosticoDTOResponse;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.medicos.dto.MedicoDTO;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.model.pacientes.dto.PacienteDTORequest;
import mx.sgahc.model.pacientes.dto.PacienteDTOResponse;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import mx.sgahc.model.util.RenderPagina;
import mx.sgahc.service.citas.CitaService;
import mx.sgahc.service.citas.DiagnosticoService;
import mx.sgahc.service.citas.dto.CitaDTOService;
import mx.sgahc.service.citas.dto.DiagnosticoDTOService;
import mx.sgahc.service.medicos.MedicoService;
import mx.sgahc.service.medicos.dto.MedicoDTOService;
import mx.sgahc.service.pacientes.PacienteService;
import mx.sgahc.service.pacientes.dto.PacienteDTOService;
import mx.sgahc.service.tratamientos.EnfermedadService;
import mx.sgahc.service.tratamientos.dto.EnfermedadDTOService;
import mx.sgahc.service.usuarios.UsuarioService;
import mx.sgahc.service.usuarios.dto.UsuarioDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    private final DiagnosticoDTOService diagnosticoDTOService;
    private final CitaService citaService;
    private final CitaDTOService citaDTOService;
    private final EnfermedadService enfermedadService;
    private final EnfermedadDTOService enfermedadDTOService;
    private final UsuarioDTOService usuarioDTOService;
    private final MedicoDTOService medicoDTOService;
    private final PacienteDTOService pacienteDTOService;

    @Autowired
    public DiagnosticoController(DiagnosticoDTOService diagnosticoDTOService,
                                 CitaService citaService, CitaDTOService citaDTOService,
                                 EnfermedadService enfermedadService, EnfermedadDTOService enfermedadDTOService,
                                 UsuarioDTOService usuarioDTOService, MedicoDTOService medicoDTOService,
                                 PacienteDTOService pacienteDTOService) {
        this.diagnosticoDTOService = diagnosticoDTOService;
        this.citaService = citaService;
        this.citaDTOService = citaDTOService;
        this.enfermedadService = enfermedadService;
        this.enfermedadDTOService = enfermedadDTOService;
        this.usuarioDTOService = usuarioDTOService;
        this.medicoDTOService = medicoDTOService;
        this.pacienteDTOService = pacienteDTOService;
    }

    @GetMapping("/diagnosticos-pacientes")
    @PreAuthorize("hasAnyAuthority('Medico', 'Pacientes')")
    public String diagnosticosPacientes(Model model,
                                        @RequestParam(name = "page", defaultValue = "0") int page) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);
        Pageable pageable = PageRequest.of(page, 6, Sort.by("fecha").descending());
        if (usuarioLog.getRol().equals("Medico")) {
            MedicoDTO medico = medicoDTOService.getMedicoByUsuarioId(usuarioLog.getId());
            if (medico == null) {
                model.addAttribute("medico", null);
                model.addAttribute("tituloPagina", "Registrar Diagnóstico");
                model.addAttribute("usuarioNombre", usuarioLog.getUsuario());
                return "diagnosticos/diagnosticos-pacientes";
            }
            Page<DiagnosticoDTOResponse> diagnosticos = diagnosticoDTOService.getDiagnosticosResponseByMedicoId(medico.getId(), pageable);
            model.addAttribute("medicoCompleto", medico);
            model.addAttribute("diagnosticos", diagnosticos);
            model.addAttribute("tituloPagina", "Diagnosticos");
            model.addAttribute("usuarioNombre", usuarioLog.getUsuario());
            model.addAttribute("diagnosticosSize", diagnosticos.getTotalElements());
            model.addAttribute("page", new RenderPagina<>("diagnosticos-pacientes", diagnosticos));
            return "diagnosticos/diagnosticos-pacientes";

        }
        if (usuarioLog.getRol().equals("Paciente")) {
            PacienteDTOResponse paciente = pacienteDTOService.getPacienteDTOResponseByUsuarioId(usuarioLog.getId());
            if (paciente == null) {
                model.addAttribute("pacienteCompleto", null);
                model.addAttribute("tituloPagina", "Registrar Diagnóstico");
                model.addAttribute("usuarioNombre", usuarioLog.getUsuario());
                return "diagnosticos/diagnosticos-pacientes";
            }
            Page<DiagnosticoDTOResponse> diagnosticos =
                    diagnosticoDTOService.getDiagnosticosResponseByPacienteId(paciente.getId(), pageable);
            model.addAttribute("pacienteCompleto", paciente);
            model.addAttribute("diagnosticos", diagnosticos);
            model.addAttribute("tituloPagina", "Registrar Diagnostico");
            model.addAttribute("usuarioNombre", usuarioLog.getUsuario());
            model.addAttribute("diagnosticosSize", diagnosticos.getTotalElements());
            model.addAttribute("page", new RenderPagina<>("diagnosticos-pacientes", diagnosticos));
            return "diagnosticos/diagnosticos-pacientes";

        }

        return "diagnosticos/diagnosticos-pacientes";

    }

    @GetMapping("/diagnosticos-de-paciente/{id}")
    public String diagnosticosPaciente(@PathVariable("id") Integer id, Model model,
                                       @RequestParam(name = "page", defaultValue = "0") int page) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);

        Pageable pageable = PageRequest.of(page, 6, Sort.by("fecha").descending());
        log.info("ID path: "+id.toString());
        PacienteDTORequest paciente = pacienteDTOService.getPacienteDTORequest(id);
        log.info("ID: "+paciente.getId().toString());
        boolean autorizacion = pacienteDTOService.validarAccesoPacienteDatos(usuarioLog.getId(), paciente);

        if (autorizacion) {
            Page<DiagnosticoDTOResponse> diagnosticos =
                    diagnosticoDTOService.getDiagnosticosResponseByPacienteId(id, pageable);
            model.addAttribute("diagnosticosSize", diagnosticos.getTotalElements());
            model.addAttribute("page", new RenderPagina<>("diagnosticos-de-paciente/", diagnosticos));
            log.info("Diagnostico "+diagnosticos.toString());
            model.addAttribute("diagnosticos", diagnosticos);

            if (usuarioLog.getRol().equals("Medico")) {
                MedicoDTO medico = medicoDTOService.getMedicoByUsuarioId(usuarioLog.getId());
                if (medico != null) {
                    model.addAttribute("medicoCompleto", medico);

                }
                model.addAttribute("pacienteCompleto", true);
            }
            if (usuarioLog.getRol().equals("Paciente")) {
                PacienteDTOResponse pacientePaciente = pacienteDTOService.getPacienteDTOResponseByUsuarioId(usuarioLog.getId());
                if (pacientePaciente != null) {
                    model.addAttribute("pacienteCompleto", true);
                    model.addAttribute("tituloPagina", "Diagnosticos de "+pacientePaciente.getDatosPersonales());
                }
                model.addAttribute("medicoCompleto", true);
            }
            model.addAttribute("usuarioNombre", usuarioLog.getUsuario());
            return "diagnosticos/diagnosticos-de-paciente";

        }

        return "redirect:/inicio";
    }

    @GetMapping("/diagnostico-paciente/{id}")
    public String diagnosticoPaciente(@PathVariable("id") Integer id, Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);

        DiagnosticoDTOResponse diagnostico = diagnosticoDTOService.getDiagnosticoResponseById(id);
        log.info("Diagnostico "+diagnostico.toString());
        model.addAttribute("diagnostico", diagnostico);
        model.addAttribute("tituloPagina", "Registrar Diagnostico");
        model.addAttribute("usuarioNombre", usuarioLog.getUsuario());

        return "diagnosticos/diagnostico-paciente";

    }



    @GetMapping("/crear-diagnostico")
    @PreAuthorize("hasAuthority('Medico')")
    public String crearDiagnostico(Model model) {
        log.info("Creando Diagnostico");
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        MedicoDTO medico = medicoDTOService.getMedicoByUsuarioId(usuarioLog.getId());
        if (medico == null) {
            model.addAttribute("medico", null);
            model.addAttribute("tituloPagina", "Registrar Diagnóstico");
            model.addAttribute("usuarioNombre", usuarioLog.getUsuario());
            return "diagnosticos/crear-diagnostico";
        }
        DiagnosticoDTORequest diagnostico = new DiagnosticoDTORequest();
        model.addAttribute("medico", medico);
        model.addAttribute("diagnosticoNuevo", diagnostico);
        model.addAttribute("listaCitas", citaDTOService.getCitasResponseSinDiagnosticoByMedicoId(medico.getId()));
        model.addAttribute("listaEnfermedades", enfermedadDTOService.getEnfermedades());
        model.addAttribute("tituloPagina", "Registrar Diagnóstico");
        return "diagnosticos/crear-diagnostico";
    }

    @PostMapping("/diagnostico-save")
    @PreAuthorize("hasAuthority('Medico')")
    public String guardarDiagnostico(@ModelAttribute("diagnosticoNuevo") DiagnosticoDTORequest diagnostico,
                                     @ModelAttribute("medico") MedicoDTO medico,
                                     BindingResult result,
                                     RedirectAttributes flash,
                                     Model model) {
        log.info("Guardando Diagnostico");
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        MedicoDTO medico2 = medicoDTOService.getMedicoByUsuarioId(usuarioLog.getId());

        if (result.hasErrors()) {
            model.addAttribute("medico", medico2);
            model.addAttribute("diagnosticoNuevo", diagnostico);
            model.addAttribute("listaCitas", citaService.getCitasSinDiagnosticoByMedicoId(medico2.getId()));
            model.addAttribute("listaEnfermedades", enfermedadService.getEnfermedades());
            model.addAttribute("tituloPagina", "Registrar Diagnóstico");
            model.addAttribute("error", "Error al envíar el formulario");
            return "diagnosticos/crear-diagnostico";
        }

        diagnosticoDTOService.saveDiagnostico(diagnostico);
        flash.addFlashAttribute("success", "Diagnóstico registrado exitosamente.");
        return "redirect:/diagnosticos/diagnosticos-pacientes";
    }

    @GetMapping("/eliminar-diagnostico/{id}")
    public String eliminarDiagnostico(@PathVariable Integer id, RedirectAttributes flash) {
        log.info("Eliminando Diagnostico");
        diagnosticoDTOService.deleteDiagnostico(id);

        flash.addFlashAttribute("success", "Diagnostico eliminado exitosamente.");
        return "redirect:/diagnosticos/diagnosticos-pacientes";
    }
}
