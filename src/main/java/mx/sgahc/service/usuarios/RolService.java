package mx.sgahc.service.usuarios;

import mx.sgahc.model.usuarios.Rol;

import java.util.List;

public interface RolService {
    List<Rol> getRoles();
    Rol getRole(String name);
    Rol getRolById(Integer id);
}
