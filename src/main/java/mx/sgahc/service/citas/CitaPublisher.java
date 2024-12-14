package mx.sgahc.service.citas;

import mx.sgahc.model.Suscriber;
import mx.sgahc.model.citas.Cita;

import java.util.List;

public interface CitaPublisher {
    void alert(String notification, Cita cita);
    void unsuscribe(Cita cita);
    void suscribe(Cita cita, Suscriber... suscribers);
}