package mx.sgahc.model.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notificaciones")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String notificacion;
    private String tipo;
    private String estado;
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;
}
