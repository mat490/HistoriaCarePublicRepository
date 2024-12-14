package mx.sgahc.controller.recetas;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.citas.Diagnostico;
import mx.sgahc.model.citas.dto.DiagnosticoDTOResponse;
import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.enfermedades.Medicamento;
import mx.sgahc.model.enfermedades.Tratamiento;
import mx.sgahc.model.enfermedades.dto.MedicamentoDTOResponse;
import mx.sgahc.model.enfermedades.dto.TratamientoDTORequest;
import mx.sgahc.model.enfermedades.dto.TratamientoDTOResponse;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.medicos.dto.MedicoDTO;
import mx.sgahc.model.pacientes.Paciente;
import mx.sgahc.model.pacientes.dto.PacienteDTORequest;
import mx.sgahc.model.pacientes.dto.PacienteDTOResponse;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import mx.sgahc.model.util.RenderPagina;
import mx.sgahc.service.citas.DiagnosticoService;
import mx.sgahc.service.citas.dto.DiagnosticoDTOService;
import mx.sgahc.service.datos.DatosService;
import mx.sgahc.service.medicos.MedicoService;
import mx.sgahc.service.medicos.dto.MedicoDTOService;
import mx.sgahc.service.pacientes.PacienteService;
import mx.sgahc.service.pacientes.dto.PacienteDTOService;
import mx.sgahc.service.tratamientos.MedicamentoService;
import mx.sgahc.service.tratamientos.TratamientoService;
import mx.sgahc.service.tratamientos.dto.MedicamentoDTOService;
import mx.sgahc.service.tratamientos.dto.TratamientoDTOService;
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

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/recetas")
public class RecetasController {
    private final UsuarioDTOService usuarioDTOService;
    private final DiagnosticoDTOService diagnosticoDTOService;
    private final MedicoDTOService medicoDTOService;
    private final PacienteDTOService pacienteDTOService;
    private final TratamientoDTOService tratamientoDTOService;
    private final MedicamentoDTOService medicamentoDTOService;

    @Autowired
    public RecetasController(UsuarioDTOService usuarioDTOService, DiagnosticoDTOService diagnosticoDTOService,
                             MedicoDTOService medicoDTOService,
                             PacienteDTOService pacienteDTOService,
                             TratamientoDTOService tratamientoDTOService,
                             MedicamentoDTOService medicamentoDTOService) {
        this.usuarioDTOService = usuarioDTOService;
        this.diagnosticoDTOService = diagnosticoDTOService;
        this.medicoDTOService = medicoDTOService;
        this.pacienteDTOService = pacienteDTOService;
        this.tratamientoDTOService = tratamientoDTOService;
        this.medicamentoDTOService = medicamentoDTOService;
    }

    @GetMapping("/recetar")
    public String recetar(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);


        MedicoDTO medico = medicoDTOService.getMedicoByUsuarioId(usuarioLog.getId());
        if (medico != null) {
            List<DiagnosticoDTOResponse> diagnosticos =
                    diagnosticoDTOService.getDIagnosticosResponseSinTratamientoByMedicoId(medico.getId());
            log.info("Diagnosticos sin tratamientos: {}" + diagnosticos);
            List<MedicamentoDTOResponse> medicamentos = medicamentoDTOService.getAllMedicamentos();
            TratamientoDTORequest tramientoNuevo = new TratamientoDTORequest();
            model.addAttribute("tratamientoNuevo", tramientoNuevo);
            model.addAttribute("medicoCompleto", medico);
            model.addAttribute("selectDiag", diagnosticos);
            model.addAttribute("selectMedic", medicamentos);
            model.addAttribute("pacientes", pacienteDTOService.getPacienteDTOResponsesByMedicoId(medico.getId()));
            model.addAttribute("usuario", usuarioLog);

        }
        return "recetas/recetar";

    }
    @GetMapping("/ver-tratamiento/{id}")
    public String modificarReceta(@PathVariable("id") Integer id, Model model) {
        log.info("Modificando receta " + id);
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);
        TratamientoDTOResponse tratamiento = tratamientoDTOService.getTratamientoResponseById(id);
        boolean autorizado = tratamientoDTOService.validarAccesoDeTratamientoResponse(usuarioLog.getId(), tratamiento);
        if ( autorizado) {
        log.info("Tratamiento "+tratamiento );
        model.addAttribute("tituloPagina", "Tratamiento");
        model.addAttribute("tratamiento", tratamiento);
        model.addAttribute("usuarioNombre", usuarioLog.getUsuario());
        return "recetas/ver-tratamiento";
        } else
            return "redirect:/inicio";
    }

    @GetMapping("/tratamientos/{id}")
    public String tratamientos(Model model,
                               @PathVariable("id") Integer id,
                               @RequestParam(name = "page", defaultValue = "0") int page){
        log.info("Verificando tratamientos de paciente con id" + id.toString());
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);
        PacienteDTORequest paciente = pacienteDTOService.getPacienteDTORequest(id);
        PacienteDTOResponse pacienteResponse = pacienteDTOService.getPacienteDTOResponse(id);

        Pageable pageable = PageRequest.of(page, 6, Sort.by("fechaInicio").descending());

        if (pacienteDTOService.validarAccesoPacienteDatos(usuarioLog.getId(), paciente)){
            Page<TratamientoDTOResponse> tratamientos = tratamientoDTOService.getTratamientosResponseByPacienteIdPage(id, pageable);
            model.addAttribute("tratamientos", tratamientos);
            model.addAttribute("tratamientosSize", tratamientos.getTotalElements());
            model.addAttribute("page", new RenderPagina<>("tratamientos/", tratamientos));
            model.addAttribute("tituloPagina", "Tratamientos de "+pacienteResponse.getDatosPersonales());
            model.addAttribute("tituloLista", "Tratamientos de "+pacienteResponse.getDatosPersonales());
            model.addAttribute("usuarioNombre", usuarioLog.getUsuario());
            return "recetas/tratamientos";
        }

        return "redirect:/inicio";
    }

    @PostMapping("/receta-save")
    public String recetaSave(Model model,
                             @ModelAttribute("tratamientoNuevo") TratamientoDTORequest tratamientoNuevo,
                             BindingResult result,
                             RedirectAttributes flash) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(email);

        Integer userId = usuarioLog.getId();
        if (result.hasErrors()) {
            flash.addFlashAttribute("error", "Error al generar receta "+result.toString());
            return "redirect:/recetas/recetar";
        }
        TratamientoDTOResponse tratamientoSave = tratamientoDTOService.saveTratamiento(tratamientoNuevo);
        if (tratamientoSave != null) {
            flash.addFlashAttribute("success", "Receta salida con exito");
            return "redirect:/recetas/ver-tratamiento/" + tratamientoSave.getId();
        }
        flash.addFlashAttribute("warning", "Receta no guardada");
        return "redirect:/recetas/recetar";

    }
}
