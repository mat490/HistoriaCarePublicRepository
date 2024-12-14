package mx.sgahc.controller.citas;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.citas.dto.CitaDTORequest;
import mx.sgahc.model.citas.dto.CitaDTOResponse;
import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.medicos.dto.MedicoDTO;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.model.pacientes.dto.PacienteDTORequest;
import mx.sgahc.model.pacientes.dto.PacienteDTOResponse;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import mx.sgahc.model.util.RenderPagina;
import mx.sgahc.service.citas.CitaService;
import mx.sgahc.service.citas.dto.CitaDTOService;
import mx.sgahc.service.datos.DatosService;
import mx.sgahc.service.datos.DatosServiceImpl;
import mx.sgahc.service.datos.dto.DatosDTOService;
import mx.sgahc.service.medicos.MedicoService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;
@Slf4j
@Controller
@RequestMapping("/citas")
public class CitasController {

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

    @Autowired
    public CitasController(MedicoService medicoService, MedicoDTOService medicoDTOService, UsuarioService usuarioService, UsuarioDTOService usuarioDTOService, PacienteService pacienteService, PacienteDTOService pacienteDTOService,
                           CitaService citaService, CitaDTOService citaDTOService, DatosService datosService, DatosDTOService datosDTOService) {
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
    }

    private boolean verificarUsuarioRolAttributes(Model model, UsuarioDTO usuarioLog, Integer userId, CitaDTORequest cita) {


        if (Objects.equals(usuarioLog.getRol(), "Medico")) {

            MedicoDTO medico = medicoDTOService.getMedicoByUsuarioId(userId);
            if (medico != null) {
                cita.setMedicoId(medico.getId());
                model.addAttribute("selPacientes", pacienteDTOService.getPacienteDTOResponsesByMedicoId(medico.getId()));
                model.addAttribute("medicoCompleto", medico);
                return true;
            }

        }

        if (Objects.equals(usuarioLog.getRol(), "Paciente")) {
            PacienteDTOResponse paciente = pacienteDTOService.getPacienteDTOResponseByUsuarioId(userId);
            if (paciente != null) {
                cita.setPacienteId(paciente.getId());
                model.addAttribute("selMedicos", medicoDTOService.getMedicosByPacienteIdList(paciente.getId()));
                model.addAttribute("pacienteCompleto", paciente);
                return true;
            }
        }
        return false;
    }

    @GetMapping("/cita-main")
    public String citasMain(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {

        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);

        Pageable pageable = PageRequest.of(page, 6, Sort.by("fecha").descending());
        Integer userId = usuarioLog.getId();
        model.addAttribute("usuarioNombre", usuarioLog.getUsuario());

        CitaDTORequest cita = new CitaDTORequest();
        System.out.println(verificarUsuarioRolAttributes(model, usuarioLog, userId, cita));
        if (verificarUsuarioRolAttributes(model, usuarioLog, userId, cita)){
            Page<CitaDTOResponse> citas = null;

            if (Objects.equals(usuarioLog.getRol(), "Medico")) {
                MedicoDTO medico = medicoDTOService
                        .getMedicoByUsuarioId(userId);
                if (medico != null) {
                citas = citaDTOService.getCitasResponsePendientesByMedicoId(medico.getId(), pageable);
                model.addAttribute("medicoCompleto", true);
                model.addAttribute("pacienteCompleto", true);
                } else {
                    model.addAttribute("medicoCompleto", null);
                    model.addAttribute("pacienteCompleto", true);
                }
            }

            if (Objects.equals(usuarioLog.getRol(), "Paciente")) {
                PacienteDTORequest paciente = pacienteDTOService.getPacienteDTORequestByUsuarioId(userId);
                if (paciente != null) {
                citas = citaDTOService.getCitasResponsePendientesByPacienteId(paciente.getId(), pageable);
                model.addAttribute("medicoCompleto", true);

                } else {
                    model.addAttribute("medicoCompleto", true);
                    model.addAttribute("pacienteCompleto", null);
                }
            }

            model.addAttribute("citas", citas);

            model.addAttribute("citasSize", citas.getTotalElements());
            model.addAttribute("page", new RenderPagina<>("cita-main", citas));
        }

        model.addAttribute("usuarioLog", usuarioLog);
        model.addAttribute("tituloPagina", "Citas de " + usuarioLog.getUsuario());

        return "citas/cita-main";
    }

    @GetMapping("/citas-historial")
    public String citasHistorial(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {

        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);

        Pageable pageable = PageRequest.of(page, 6, Sort.by("fecha").descending());
        Integer userId = usuarioLog.getId();
        model.addAttribute("usuarioNombre", usuarioLog.getUsuario());

        CitaDTORequest cita = new CitaDTORequest();
        System.out.println(verificarUsuarioRolAttributes(model, usuarioLog, userId, cita));
        if (verificarUsuarioRolAttributes(model, usuarioLog, userId, cita)){
            Page<CitaDTOResponse> citas = null;

            if (Objects.equals(usuarioLog.getRol(), "Medico")) {
                MedicoDTO medico = medicoDTOService
                        .getMedicoByUsuarioId(userId);
                if (medico != null) {
                    citas = citaDTOService.getCitasByMedicoId(medico.getId(), pageable);
                    model.addAttribute("medicoCompleto", true);
                    model.addAttribute("pacienteCompleto", true);
                } else {
                    model.addAttribute("medicoCompleto", null);
                    model.addAttribute("pacienteCompleto", true);
                }
            }

            if (Objects.equals(usuarioLog.getRol(), "Paciente")) {
                Paciente paciente = pacienteService.getPacienteByUsuarioId(userId);
                if (paciente != null) {
                    citas = citaDTOService.getCitasByPacienteId(paciente.getId(), pageable);
                    model.addAttribute("medicoCompleto", true);

                } else {
                    model.addAttribute("medicoCompleto", true);
                    model.addAttribute("pacienteCompleto", null);
                }
            }

            model.addAttribute("citas", citas);

            model.addAttribute("citasSize", citas.getTotalElements());
            model.addAttribute("page", new RenderPagina<>("cita-main", citas));
        }


        model.addAttribute("usuarioLog", usuarioLog);
        model.addAttribute("tituloPagina", "Citas de " + usuarioLog.getUsuario());

        return "citas/citas-historial";
    }

    @GetMapping("/citas-historial/{id}")
    public String citasHistorial(Model model,
                                 @PathVariable("id") Integer id,
                                 @RequestParam(name = "page", defaultValue = "0") int page) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);
        PacienteDTORequest paciente = pacienteDTOService.getPacienteDTORequest(id);
        log.info(id.toString());
        log.info(paciente.toString());

        Pageable pageable = PageRequest.of(page, 6, Sort.by("fecha").descending());
        model.addAttribute("usuarioNombre", usuarioLog.getUsuario());

        if (verificarUsuarioRolAttributes(model, usuarioLog, usuarioLog.getId(), new CitaDTORequest())){
            Page<CitaDTOResponse> citas = null;
            if (pacienteDTOService.validarAccesoPacienteDatos(usuarioLog.getId(), paciente)){
                citas = citaDTOService.getCitasByPacienteId(paciente.getId(), pageable);
                model.addAttribute("deshabilitar", true);
                model.addAttribute("medicoCompleto", true);
                model.addAttribute("pacienteCompleto", true);
                model.addAttribute("citas", citas);

                model.addAttribute("citasSize", citas.getTotalElements());
                model.addAttribute("page", new RenderPagina<>("citas-hitorial/", citas));
                model.addAttribute("usuarioLog", usuarioLog);
                model.addAttribute("tituloPagina", "Citas de " + usuarioLog.getUsuario());

                return "citas/citas-historial";
            }


        }
        return "redirect:/inicio";

    }

    @GetMapping("/cita-nueva")
    public String citaNueva(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);
        Integer userId = usuarioLog.getId();
        CitaDTORequest cita = new CitaDTORequest();

        verificarUsuarioRolAttributes(model, usuarioLog, userId, cita);


        model.addAttribute("citaNueva", cita);
        model.addAttribute("tituloPagina", "Agendar nueva cita");
        return "citas/cita-nueva";
    }

    @GetMapping("/cita-nueva/{id}")
    public String citaNuevoMedico(Model model,
                            @PathVariable("id") Integer id) {

        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);

        Integer userId = usuarioLog.getId();
        Medico medicoFiltro = medicoService.getMedico(id);
        CitaDTORequest cita = new CitaDTORequest();
        if (medicoFiltro == null){
            verificarUsuarioRolAttributes(model, usuarioLog, userId, cita);
            log.warn("MedicoFiltroNulo");
        }
        else {
            PacienteDTORequest paciente = pacienteDTOService.getPacienteDTORequestByUsuarioId(userId);
            cita.setPacienteId(paciente.getId());
            cita.setMedicoId(medicoFiltro.getId());
            model.addAttribute("selMedicos", medicoFiltro);
            model.addAttribute("paciente", paciente);
        }

        model.addAttribute("citaNueva", cita);
        model.addAttribute("tituloPagina", "Agendar nueva cita");
        return "citas/cita-nueva";
    }

    @PostMapping("/cita-save")
    public String citaSave(Model model,
                           @Valid @ModelAttribute("citaNueva") CitaDTORequest cita,
                           BindingResult result,
                           RedirectAttributes flash) {

        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);
        log.info("Guardando cita... "+cita);
        Integer userId = usuarioLog.getId();

        if (result.hasErrors()) {
            verificarUsuarioRolAttributes(model, usuarioLog, userId, cita);
            model.addAttribute("error", "error al agendar cita " + cita);
            model.addAttribute("tituloPagina", "Agendar nueva cita");
            model.addAttribute("citaNueva", cita);
            return "citas/cita-nueva";
        }

        if (Objects.equals(usuarioLog.getRol(), "Medico")) {
            Medico medico = medicoService.getMedicoByUsuarioId(userId);
            cita.setMedicoId(medico.getId());
        }
        else if (Objects.equals(usuarioLog.getRol(), "Paciente")) {
            Paciente paciente = pacienteService.getPacienteByUsuarioId(userId);
            cita.setPacienteId(paciente.getId());
        }

        citaDTOService.saveCitaRequest(cita);

        flash.addFlashAttribute("success", "Cita agendada con éxito");
        flash.addFlashAttribute("usuarioLog", usuarioLog);

        return "redirect:/citas/cita-main";
    }

    @GetMapping("/update-cita/{id}")
    public String updateCita(@PathVariable("id") Integer id, Model model,
                             RedirectAttributes flash) {

        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);
        Integer userId = usuarioLog.getId();


        verificarUsuarioRolAttributes(model, usuarioLog, userId, citaDTOService.getCitaRequest(id));
        CitaDTORequest cita = new CitaDTORequest();
        cita = citaDTOService.getCitaRequest(id);
        log.info("Edintando cita... "+cita);
        model.addAttribute("citaNueva", cita);
        model.addAttribute("contenido", "Modificar Cita");
        return "citas/cita-nueva";
    }

    @GetMapping("eliminar-cita/{id}")
    public String eliminar(@PathVariable("id") Integer id, RedirectAttributes flash){
        citaDTOService.deleteCitaId(id);
        flash.addFlashAttribute("success", "Se borró con éxito la cita");
        return "redirect:/citas/cita-main";
    }
}