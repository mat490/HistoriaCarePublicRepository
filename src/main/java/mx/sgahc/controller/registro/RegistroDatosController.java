package mx.sgahc.controller.registro;

import lombok.extern.slf4j.Slf4j;
import mx.sgahc.model.datos.DatosPersonales;
import mx.sgahc.model.datos.Direccion;
import mx.sgahc.model.datos.Sexo;
import mx.sgahc.model.datos.dto.DatosPersonalesDTO;
import mx.sgahc.model.datos.dto.DatosPersonalesDTORequest;
import mx.sgahc.model.datos.dto.DireccionDTO;
import mx.sgahc.model.datos.dto.SexoDTO;
import mx.sgahc.model.usuarios.Usuario;
import mx.sgahc.model.usuarios.dto.UsuarioDTO;
import mx.sgahc.service.datos.DatosService;
import mx.sgahc.service.datos.DireccionService;
import mx.sgahc.service.datos.SexoService;
import mx.sgahc.service.datos.dto.DatosDTOService;
import mx.sgahc.service.datos.dto.DireccionDTOService;
import mx.sgahc.service.datos.dto.SexoDTOService;
import mx.sgahc.service.medicos.EspecialidadService;
import mx.sgahc.service.medicos.MedicoService;
import mx.sgahc.service.pacientes.*;
import mx.sgahc.service.usuarios.RolService;
import mx.sgahc.service.usuarios.UsuarioService;
import mx.sgahc.service.usuarios.dto.UsuarioDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/registro")
public class RegistroDatosController {

    private final UsuarioService usuarioService;
    private final UsuarioDTOService usuarioDTOService;
    private final SexoDTOService sexoDTOService;
    private final DireccionDTOService direccionDTOService;
    private final DatosDTOService datosDTOService;


    private DireccionDTO direccion;
    private DatosPersonalesDTORequest datos;

    @Autowired
    public RegistroDatosController(UsuarioService usuarioService, UsuarioDTOService usuarioDTOService,
                                   SexoDTOService sexoDTOService,
                                   DireccionDTOService direccionDTOService,
                                   DatosDTOService datosDTOService) {
        this.usuarioService = usuarioService;
        this.usuarioDTOService = usuarioDTOService;
        this.sexoDTOService = sexoDTOService;
        this.direccionDTOService = direccionDTOService;
        this.datosDTOService = datosDTOService;
    }

    @GetMapping("/registro-direccion")
    public String registroDireccion(Model model) {
        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        direccion = new DireccionDTO();
        model.addAttribute("tituloPagina", usuarioLog.getUsuario());
        model.addAttribute("direccionNueva", direccion);
        model.addAttribute("usuarioLog", usuarioLog);
        System.out.println("not save_direccion =" + usuarioLog.getId());
        return "registro/registro-direccion";
    }

    @PostMapping("/save-direccion")
    public String saveDireccion(@ModelAttribute("direccionNueva") DireccionDTO direccion, Model model,
                                BindingResult result, RedirectAttributes flash) {
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        if (result.hasErrors()) {
            model.addAttribute("direccionNueva", direccion);
            model.addAttribute("usuarioLog", usuarioLog);
            model.addAttribute("tituloPagina", "ERROR");
            model.addAttribute("error", "Error al registrar sus dirección");
            return "registro/registro-direccion";
        }


        List<SexoDTO> selectSexo = sexoDTOService.getSexos();

        datos = new DatosPersonalesDTORequest();
        datos.setDireccionId(direccionDTOService.saveDireccion(direccion).getId());
        System.out.println("save_direccion " + datos.getDireccionId());

        flash.addFlashAttribute("selectSexo", selectSexo);
        flash.addFlashAttribute("datosNuevos", datos);
        flash.addFlashAttribute("direccionId", datos.getDireccionId());
        flash.addFlashAttribute("tituloPagina", usuarioLog.getUsuario());
        flash.addFlashAttribute("usuarioLog", usuarioLog);

        return "redirect:/registro/registro-datos";
    }

    @GetMapping("/registro-datos")
    public String registroDatos(@ModelAttribute("direccionId") Integer direccionId,
                                Model model) {
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        log.info(direccionId.toString());

        DatosPersonalesDTORequest datos = new DatosPersonalesDTORequest();
        datos.setDireccionId(direccionId);
        datos.setUsuarioId(usuarioLog.getId());
        model.addAttribute("datosNuevos", datos);
        model.addAttribute("direccionId", direccionId);
        model.addAttribute("selectSexo", sexoDTOService.getSexos());
        model.addAttribute("tituloPagina", "Registro de datos");

        return "registro/registro-datos";
    }

    @PostMapping("/save-datos")
    public String saveDatos(@ModelAttribute("datosNuevos") DatosPersonalesDTORequest datos,
                            BindingResult result,
                            Model model,
                            @ModelAttribute("direccionId") Integer id,
                            RedirectAttributes flash) {
        UsuarioDTO usuarioLog = usuarioDTOService.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        List<SexoDTO> selectSexo = sexoDTOService.getSexos();
        datos.setUsuarioId(usuarioLog.getId());
        log.info("Guardando datos");
        if (result.hasErrors()) {
            model.addAttribute("datosNuevos", datos);
            model.addAttribute("selectSexo", selectSexo);
            model.addAttribute("tituloPagina", usuarioLog.getUsuario());
            model.addAttribute("direccionId", id);
            model.addAttribute("usuarioLog", usuarioLog);
            model.addAttribute("error", "Error al regustrar sus datos");


            return "registro/registro-datos";
        }
        log.info("id "+id);
        System.out.println(id);
        datos.setUsuarioId(usuarioDTOService.findByEmail(usuarioLog.getCorreoElectronico()).getId());  // Asigna el usuario de sesión a los datos personales
        datos.setDireccionId(id);
        log.info("Guardando datos "+datos);
        datosDTOService.saveDatosRequestPersonales(datos);
        System.out.println(usuarioService.findByEmail(usuarioLog.getCorreoElectronico()).getRol());
        flash.addFlashAttribute("registroIncompleto", false);
        return  "redirect:/inicio";
    }
}
