package mx.sgahc.repository;

import mx.sgahc.model.enfermedades.Enfermedad;

public interface ConteoPorEnfermedad {
    Enfermedad getEnfermedad();
    Long getConteo();
}
