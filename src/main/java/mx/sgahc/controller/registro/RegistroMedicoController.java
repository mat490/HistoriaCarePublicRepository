package mx.sgahc.controller.registro;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.datos.dto.DatosPersonalesDTO;
import mx.sgahc.model.medicos.Especialidad;
import mx.sgahc.model.medicos.Medico;
import mx.sgahc.model.medicos.dto.EspecialidadDTO;
import mx.sgahc.model.medicos.dto.MedicoDTO;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import mx.sgahc.service.datos.DatosService;
import mx.sgahc.service.datos.DireccionService;
import mx.sgahc.service.datos.SexoService;
import mx.sgahc.service.datos.dto.DatosDTOService;
import mx.sgahc.service.medicos.EspecialidadService;
import mx.sgahc.service.medicos.MedicoService;
import mx.sgahc.service.medicos.dto.EspecialidadDTOService;
import mx.sgahc.service.medicos.dto.MedicoDTOService;
import mx.sgahc.service.pacientes.*;
import mx.sgahc.service.usuarios.RolService;
import mx.sgahc.service.usuarios.UsuarioService;
import mx.sgahc.service.usuarios.dto.UsuarioDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/registro")
public class RegistroMedicoController {
    private final UsuarioDTOService usuarioDTOService;
    private final MedicoDTOService medicoDTOService;
    private final DatosDTOService datosDTOService;
    private final EspecialidadDTOService especialidadDTOService;

    @Autowired
    public RegistroMedicoController(UsuarioDTOService usuarioDTOService, MedicoDTOService medicoDTOService,
                                    DatosDTOService datosDTOService, EspecialidadDTOService especialidadDTOServiceService) {
        this.usuarioDTOService = usuarioDTOService;
        this.medicoDTOService = medicoDTOService;
        this.datosDTOService = datosDTOService;
        this.especialidadDTOService = especialidadDTOServiceService;
    }

    @GetMapping("/registro-medico")
    public String registroMedico(Model model) {
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        List<EspecialidadDTO> especialidades = especialidadDTOService.getAllEspecialidades();
        Integer id = usuarioLog.getId();
        DatosPersonalesDTO datosPersonales = datosDTOService.getDatosPersonalesByUsuarioId(id);

        // Crear nuevo médico y asignar datos personales
        MedicoDTO medicoNuevo = new MedicoDTO();
        medicoNuevo.setDatosPersonales(datosPersonales);
        medicoNuevo.setEspecialidad(new EspecialidadDTO());

        model.addAttribute("selectEsp", especialidades);
        model.addAttribute("tituloPagina", usuarioLog.getUsuario());
        model.addAttribute("usuarioLog", usuarioLog);
        model.addAttribute("medicoNuevo", medicoNuevo);
        model.addAttribute("datosPersonales", datosPersonales);

        return "registro/registro-medico";
    }

    @PostMapping("/medico-save")
    public String saveMedico(@ModelAttribute("medicoNuevo") MedicoDTO medico,
                             Model model,
                             BindingResult result,
                             RedirectAttributes flash) {
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        List<EspecialidadDTO> especialidades = especialidadDTOService.getAllEspecialidades();

        if (result.hasErrors()) {
            model.addAttribute("selectEsp", especialidades);
            model.addAttribute("tituloPagina", usuarioLog.getUsuario());
            model.addAttribute("usuarioLog", usuarioLog);
            model.addAttribute("error", "error al añadir los atributos");
            return "registro/registro-medico";
        }
        medico.setDatosPersonales(datosDTOService.getDatosPersonalesByUsuarioId(usuarioLog.getId()));
        medicoDTOService.saveMedico(medico);

        flash.addFlashAttribute("usuarioLog", usuarioLog);
        flash.addFlashAttribute("success", "Sus datos como médico han sido agregados");
        flash.addFlashAttribute("medicoCompleto", medico);
        flash.addFlashAttribute("registroIncompleto", false);

        return "redirect:/citas/cita-main";
    }
}
