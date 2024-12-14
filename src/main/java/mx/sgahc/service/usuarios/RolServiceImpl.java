package mx.sgahc.service.usuarios;

import mx.sgahc.model.usuarios.Rol;
import mx.sgahc.repository.usuarios.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService{
    @Autowired
    RolRepository rolRepository;

    @Override
    public List<Rol> getRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Rol getRole(String name) {
        return rolRepository.findRolByRol(name);
    }

    @Override
    public Rol getRolById(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }
}
