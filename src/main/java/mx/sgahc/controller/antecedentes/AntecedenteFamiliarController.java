package mx.sgahc.controller.antecedentes;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.datos.LugarNacimiento;
import mx.sgahc.model.datos.Sexo;
import mx.sgahc.model.datos.dto.LugarNacimientoDTO;
import mx.sgahc.model.datos.dto.SexoDTO;
import mx.sgahc.model.enfermedades.Enfermedad;
import mx.sgahc.model.enfermedades.dto.EnfermedadDTOResponse;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.model.pacientes.dto.*;
import mx.sgahc.model.pacientes.familiares.AntecedenteFamiliar;
import mx.sgahc.model.pacientes.familiares.Parentesco;
import mx.sgahc.model.pacientes.familiares.RazonFallecimiento;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import mx.sgahc.model.util.RenderPagina;
import mx.sgahc.service.datos.SexoService;
import mx.sgahc.service.datos.dto.SexoDTOService;
import mx.sgahc.service.pacientes.*;
import mx.sgahc.service.pacientes.dto.*;
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
public class AntecedenteFamiliarController {
    private final AntecedenteFamiliarDTOService antecedenteFamiliarDTOService;
    private final PacienteDTOService pacienteDTOService;
    private final UsuarioDTOService usuarioDTOService;
    private final LugarNacimientoDTOService lugarNacimientoDTOService;
    private final EnfermedadDTOService enfermedadDTOService;
    private final SexoDTOService sexoDTOService;
    private final ParentescoDTOService parentescoDTOService;
    private final RazonFallecimientoDTOService razonFallecimientoDTOService;

    @Autowired
    public AntecedenteFamiliarController(AntecedenteFamiliarDTOService antecedenteFamiliarDTOService, PacienteDTOService pacienteDTOService,
                                         UsuarioDTOService usuarioDTOService, LugarNacimientoDTOService lugarNacimientoDTOService,
                                         EnfermedadDTOService enfermedadDTOService, SexoDTOService sexoDTOService,
                                         ParentescoDTOService parentescoDTOService, RazonFallecimientoDTOService razonFallecimientoDTOService) {
        this.antecedenteFamiliarDTOService = antecedenteFamiliarDTOService;
        this.pacienteDTOService = pacienteDTOService;
        this.usuarioDTOService = usuarioDTOService;
        this.lugarNacimientoDTOService = lugarNacimientoDTOService;
        this.enfermedadDTOService = enfermedadDTOService;
        this.sexoDTOService = sexoDTOService;
        this.parentescoDTOService = parentescoDTOService;

        this.razonFallecimientoDTOService = razonFallecimientoDTOService;
    }

    @GetMapping("/antecedentes-familiares/{id}")
    public String antecedenteFamiliar(@PathVariable("id") Integer id,
                                      Model model,
                                      @RequestParam(name = "page", defaultValue = "0") int page) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);
        PacienteDTOResponse paciente = pacienteDTOService.getPacienteDTOResponse(id);
        PacienteDTORequest pacienteDTORequest = pacienteDTOService.getPacienteDTORequest(id);

        Pageable pageable = PageRequest.of(page, 6, Sort.by("parentesco").descending());
        if (pacienteDTOService.validarAccesoPacienteDatos(usuarioLog.getId(), pacienteDTORequest)){
            Page<AntecedenteFamiliarDTOResponse> antecedentesFamiliares =
                    antecedenteFamiliarDTOService.findAntecedentesByPacienteId(paciente.getId(), pageable);

            model.addAttribute("antecedentesFamiliares", antecedentesFamiliares);
            model.addAttribute("paciente", paciente);
            model.addAttribute("usuarioNombre", usuarioLog.getUsuario());
            model.addAttribute("tituloPagina",
                    "Antecedentes Familiares de "+paciente.getDatosPersonales());

            model.addAttribute("antecedentesFamiliaresSize", antecedentesFamiliares.getTotalElements());
            model.addAttribute("page",
                    new RenderPagina<>("antecedentes-familiares/", antecedentesFamiliares));
            return "antecedentes/antecedentes-familiares";
        }
        return "redirect:/inicio";
    }

    @GetMapping("/crear-antecedente-familiar")
    public String crearAntecedenteFamiliar(Model model){
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);

        AntecedenteFamiliarDTORequest antecedenteFamiliarNuevo = new AntecedenteFamiliarDTORequest();
        LugarNacimientoDTO lugarNacimientoDTO = new LugarNacimientoDTO();
        List<ParentescoDTOResponse> selectParentescos = parentescoDTOService.getAllParentesco();
        List<EnfermedadDTOResponse> selectEnfermedades= enfermedadDTOService.getEnfermedades();
        List<SexoDTO> selectSexos = sexoDTOService.getSexos();
        List<RazonFallecimientoDTOResponse>  selectRazones = razonFallecimientoDTOService.getRazonesFallecimientos();

        model.addAttribute("selectParentescos", selectParentescos);
        model.addAttribute("selectEnfermedades", selectEnfermedades);
        model.addAttribute("selectSexos", selectSexos);
        model.addAttribute("selectRazones", selectRazones);

        model.addAttribute("antecedenteFamiliarNuevo", antecedenteFamiliarNuevo);
        model.addAttribute("lugarNacimiento", lugarNacimientoDTO);
        model.addAttribute("tituloPagina", "Registrar Antecdente Heredofamiliar");
        model.addAttribute("usuarioNombre", usuarioLog.getUsuario());
        return "antecedentes/crear-antecedente-familiar";
    }

    @PostMapping("antecedente-familiar-save")
    public String saveAntecedenteFamiliar(RedirectAttributes flash,
                                          @ModelAttribute("antecedenteFamiliarNuevo") AntecedenteFamiliarDTORequest antecedenteFamiliar,
                                          @ModelAttribute("lugarNacimiento") LugarNacimientoDTO lugarNacimientoDTO){
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);
        PacienteDTORequest paciente = pacienteDTOService.getPacienteDTORequestByUsuarioId(usuarioLog.getId());
        antecedenteFamiliar.setPacienteId(paciente.getId());
        log.info("Antecednete familiar nuevo: {}",antecedenteFamiliar);
        antecedenteFamiliar.setLugarNacimientoId(lugarNacimientoDTOService.saveLugarNacimiento(lugarNacimientoDTO).getId());

        antecedenteFamiliarDTOService.saveAntecedenteFamiliar(antecedenteFamiliar);
        flash.addFlashAttribute("success", "Antecedente familiar registrado correctamente");
        return "redirect:/antecedentes/antecedentes-familiares/"+paciente.getId();
    }
}
