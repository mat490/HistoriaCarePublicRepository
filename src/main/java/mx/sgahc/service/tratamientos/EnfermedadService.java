package mx.sgahc.service.tratamientos;

import mx.sgahc.model.enfermedades.Enfermedad;

import java.util.List;

public interface EnfermedadService {
    Enfermedad getEnfermedadById(Integer id);
    Enfermedad getEnfermedadByNombre(String nombre);
    List<Enfermedad> getEnfermedades();
    Enfermedad saveEnfermedad(Enfermedad enfermedad);
    void deleteEnfermedadById(Enfermedad enfermedad);
    void updateEnfermedadById(Enfermedad enfermedad, Integer id);
}
