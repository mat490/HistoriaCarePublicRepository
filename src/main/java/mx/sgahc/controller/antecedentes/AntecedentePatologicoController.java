package mx.sgahc.controller.antecedentes;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.enfermedades.Enfermedad;
import mx.sgahc.model.enfermedades.dto.EnfermedadDTOResponse;
import mx.sgahc.model.pacientes.AntecedentePatologico;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.model.pacientes.dto.AntecedentPatologicoDTOResponse;
import mx.sgahc.model.pacientes.dto.AntecedentePatologicoDTORequest;
import mx.sgahc.model.pacientes.dto.PacienteDTORequest;
import mx.sgahc.model.pacientes.dto.PacienteDTOResponse;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import mx.sgahc.model.util.RenderPagina;
import mx.sgahc.service.pacientes.AntecedentePatologicoService;
import mx.sgahc.service.pacientes.PacienteService;
import mx.sgahc.service.pacientes.dto.AntecedentePatologicoDTOService;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/antecedentes")
public class AntecedentePatologicoController {
    private final AntecedentePatologicoService antecedentePatologicoService;
    private final AntecedentePatologicoDTOService antecedentePatologicoDTOService;
    private final UsuarioService usuarioService;
    private final UsuarioDTOService usuarioDTOService;
    private final PacienteService pacienteService;
    private final PacienteDTOService pacienteDTOService;
    private final EnfermedadService enfermedadService;
    private final EnfermedadDTOService enfermedadDTOService;

    @Autowired
    public AntecedentePatologicoController(AntecedentePatologicoService antecedentePatologicoService, AntecedentePatologicoDTOService antecedentePatologicoDTOService, UsuarioService usuarioService, UsuarioDTOService usuarioDTOService, PacienteService pacienteService, PacienteDTOService pacienteDTOService, EnfermedadService enfermedadService, EnfermedadDTOService enfermedadDTOService) {
        this.antecedentePatologicoService = antecedentePatologicoService;
        this.antecedentePatologicoDTOService = antecedentePatologicoDTOService;
        this.usuarioService = usuarioService;
        this.usuarioDTOService = usuarioDTOService;
        this.pacienteService = pacienteService;
        this.pacienteDTOService = pacienteDTOService;
        this.enfermedadService = enfermedadService;
        this.enfermedadDTOService = enfermedadDTOService;
    }

    @GetMapping("/antecedentes-patologicos/{id}")
    public String antecedentePatologico(@PathVariable("id") Integer id,
                                        Model model,
                                        @RequestParam(name = "page", defaultValue = "0") int page) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);
        PacienteDTOResponse paciente = pacienteDTOService.getPacienteDTOResponse(id);
        PacienteDTORequest pacienteRequest = pacienteDTOService.getPacienteDTORequest(id);

        if (pacienteDTOService.validarAccesoPacienteDatos(usuarioLog.getId(), pacienteRequest)){
            Pageable pageable = PageRequest.of(page, 6, Sort.by("enfermedad").descending());
            Page<AntecedentPatologicoDTOResponse> antecedentePatologicos =
                    antecedentePatologicoDTOService.getAntecedentesPatologicosByPacienteId(paciente.getId(), pageable);
            log.info("Antecedentes: {}", antecedentePatologicos.getTotalElements());
            model.addAttribute("antecedentesPatologicos", antecedentePatologicos);
            model.addAttribute("paciente", paciente);
            model.addAttribute("antecedentesSize", antecedentePatologicos.getTotalElements());
            model.addAttribute("usuarioNombre", usuarioLog.getUsuario());
            model.addAttribute("tituloPagina","Antecedentes Patológicos de "+paciente.getDatosPersonales());
            model.addAttribute("page", new RenderPagina<>("antecedentes-patologicos/", antecedentePatologicos));

            return "antecedentes/antecedentes-patologicos";
        }
        return "redirect:/inicio";
    }

    @GetMapping("/crear-antecedente-patologico")
    public String crearAntecedetePatologico(Model model){
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);

        AntecedentePatologicoDTORequest antecedentePatologicoNuevo = new AntecedentePatologicoDTORequest();
        PacienteDTOResponse paciente = pacienteDTOService.getPacienteDTOResponseByUsuarioId(usuarioLog.getId());
        if (paciente != null) {
            antecedentePatologicoNuevo.setPacienteId(paciente.getId());
            model.addAttribute("antecedentePatologicoNuevo", antecedentePatologicoNuevo);

            List<EnfermedadDTOResponse> selectEnfermedades = enfermedadDTOService.getEnfermedades();
            model.addAttribute("selectEnfermedades", selectEnfermedades);

            return "antecedentes/crear-antecedente-patologico";
        }
        return "redirect:/inicio";


    }

    @PostMapping("/antecedente-patologico-save")
    public String saveAntecedentePatologico(
            RedirectAttributes flash,
            @ModelAttribute("antecedentePatologicoNuevo") AntecedentePatologicoDTORequest antecedentePatologicoNuevo) {
        log.info(antecedentePatologicoNuevo.toString());
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);

        PacienteDTOResponse paciente = pacienteDTOService.getPacienteDTOResponseByUsuarioId(usuarioLog.getId());
        antecedentePatologicoNuevo.setPacienteId(paciente.getId());
        log.info("paciente: " + paciente.toString());

        if (antecedentePatologicoNuevo != null) {
            antecedentePatologicoDTOService.createAntecedentePatologico(antecedentePatologicoNuevo);
            flash.addFlashAttribute("success", "Antecedente guardado");
            return "redirect:/antecedentes/antecedentes-patologicos/" + paciente.getId();
        }
        flash.addAttribute("error", "Error al registrar antecedente");
        return "redirect:/crear";

    }

}
