package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrenador {

    @Id
    @Column(name = "Usuario_codigo")
    private String codigo; // Este es también el código del Usuario

    @Column(nullable = false)
    private int aniosExp;

    @OneToOne
    @JoinColumn(name = "Usuario_codigo", insertable = false, updatable = false)
    private Usuario usuario;
}