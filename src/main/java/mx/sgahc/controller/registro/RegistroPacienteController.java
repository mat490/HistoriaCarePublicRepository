package mx.sgahc.controller.registro;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.datos.Direccion;
import mx.sgahc.model.datos.LugarNacimiento;
import mx.sgahc.model.datos.dto.DatosPersonalesDTO;
import mx.sgahc.model.datos.dto.LugarNacimientoDTO;
import mx.sgahc.model.pacientes.*;
import mx.sgahc.model.pacientes.dto.*;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import mx.sgahc.service.datos.DatosService;
import mx.sgahc.service.datos.DireccionService;
import mx.sgahc.service.datos.SexoService;
import mx.sgahc.service.datos.dto.DatosDTOService;
import mx.sgahc.service.medicos.EspecialidadService;
import mx.sgahc.service.medicos.MedicoService;
import mx.sgahc.service.pacientes.*;
import mx.sgahc.service.pacientes.dto.*;
import mx.sgahc.service.usuarios.RolService;
import mx.sgahc.service.usuarios.UsuarioService;
import mx.sgahc.service.usuarios.dto.UsuarioDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class RegistroPacienteController {

    private final DatosDTOService datosDTOService;
    private final UsuarioDTOService usuarioDTOService;
    private final OcupacionDTOService ocupacionDTOService;
    private final RhDTOService rhDTOService;
    private final EstadoCivilDTOService estadoCivilDTOService;
    private final GrupoSanguineoDTOService grupoSanguineoDTOService;
    private final LugarNacimientoDTOService lugarNacimientoDTOService;
    private final PacienteDTOService pacienteDTOService;
    private final CombeDTOService combeDTOService;


    @Autowired
    public RegistroPacienteController(DatosDTOService datosDTOService,
                                      UsuarioDTOService usuarioDTOService,
                                      OcupacionDTOService ocupacionDTOService,
                                      RhDTOService rhDTOService,
                                      EstadoCivilDTOService estadoCivilDTOService,
                                      GrupoSanguineoDTOService grupoSanguineoDTOService,
                                      LugarNacimientoDTOService lugarNacimientoDTOService,
                                      PacienteDTOService pacienteDTOService,
                                      CombeDTOService combeDTOService) {
        this.datosDTOService = datosDTOService;
        this.usuarioDTOService = usuarioDTOService;
        this.ocupacionDTOService = ocupacionDTOService;
        this.rhDTOService = rhDTOService;
        this.estadoCivilDTOService = estadoCivilDTOService;
        this.grupoSanguineoDTOService = grupoSanguineoDTOService;
        this.lugarNacimientoDTOService = lugarNacimientoDTOService;
        this.pacienteDTOService = pacienteDTOService;
        this.combeDTOService = combeDTOService;
    }


    @GetMapping("/registro-paciente")
    @PreAuthorize("hasAuthority('Paciente')")
    public String registroPaciente(Model model, RedirectAttributes flash) {
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
        );
        DatosPersonalesDTO datosPersonales = datosDTOService.getDatosPersonalesByUsuarioId(usuarioLog.getId());
        PacienteDTORequest pacienteNuevo = new PacienteDTORequest();
        pacienteNuevo.setDatosPersonalesId(datosPersonales.getId());
        LugarNacimientoDTO lugarNacimiento = new LugarNacimientoDTO();
        pacienteNuevo.setLugarNacimiento(lugarNacimiento);

        List<EstadoCivilDTO> estadoCiviles = estadoCivilDTOService.getAllEstadoCivil();
        List<CombeDTO> combes = combeDTOService.getAllCombe();
        List<GrupoSanguineoDTO> grupoSanguineos = grupoSanguineoDTOService.getAllGrupoSanguineo();
        List<RhDTO> rhs = rhDTOService.getAllRh();
        List<OcupacionDTO> ocupaciones = ocupacionDTOService.getOcupaciones();

        model.addAttribute("selectEst", estadoCiviles);
        model.addAttribute("selectCombe", combes);
        model.addAttribute("selectGpoSan", grupoSanguineos);
        model.addAttribute("selectRh", rhs);
        model.addAttribute("selectOc", ocupaciones);
        model.addAttribute("tituloPagina", "Registro Paciente");
        model.addAttribute("datosPersonales", datosPersonales);



        model.addAttribute("pacienteNuevo", pacienteNuevo);

        return "registro/registro-paciente";
    }

    @PostMapping("/paciente-save")
    public String registroPacienteSave(Model model, @ModelAttribute("pacienteNuevo") PacienteDTORequest pacienteNuevo,
                                       BindingResult result, RedirectAttributes flash) {
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        log.info("Entered");
        if (result.hasErrors()) {
            DatosPersonalesDTO datosPersonales = datosDTOService.getDatosPersonalesByUsuarioId(usuarioLog.getId());
            pacienteNuevo.setDatosPersonalesId(datosPersonales.getId());
            LugarNacimientoDTO lugarNacimiento = new LugarNacimientoDTO();
            pacienteNuevo.setLugarNacimiento(lugarNacimiento);

            List<EstadoCivilDTO> estadoCiviles = estadoCivilDTOService.getAllEstadoCivil();
            List<CombeDTO> combes = combeDTOService.getAllCombe();
            List<GrupoSanguineoDTO> grupoSanguineos = grupoSanguineoDTOService.getAllGrupoSanguineo();
            List<RhDTO> rhs = rhDTOService.getAllRh();
            List<OcupacionDTO> ocupaciones = ocupacionDTOService.getOcupaciones();

            model.addAttribute("selectEst", estadoCiviles);
            model.addAttribute("selectCombe", combes);
            model.addAttribute("selectGpoSan", grupoSanguineos);
            model.addAttribute("selectRh", rhs);
            model.addAttribute("selectOc", ocupaciones);
            model.addAttribute("tituloPagina", "Registro Paciente");
            model.addAttribute("datosPersonales", datosPersonales);



            model.addAttribute("pacienteNuevo", pacienteNuevo);

            return "registro/registro-paciente";
        }


        pacienteNuevo.setLugarNacimiento(lugarNacimientoDTOService.saveLugarNacimiento(pacienteNuevo.getLugarNacimiento()));
        pacienteDTOService.savePaciente(pacienteNuevo);

        flash.addFlashAttribute("usuarioLog", usuarioLog);
        flash.addFlashAttribute("success", "Sus datos como paciente han sido agregados");
        flash.addFlashAttribute("pacienteCompleto", pacienteNuevo);
        flash.addFlashAttribute("registroIncompleto", false);

        return "redirect:/citas/cita-main";

    }
}
