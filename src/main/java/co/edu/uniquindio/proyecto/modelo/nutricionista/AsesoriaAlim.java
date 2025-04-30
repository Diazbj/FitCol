package co.edu.uniquindio.proyecto.modelo.nutricionista;

import co.edu.uniquindio.proyecto.modelo.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AsesoriaAlimId.class)
public class AsesoriaAlim implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "Nutri_Usuario_codigo", nullable = false)
    private Nutricionista nutricionista;

    @Id
    @ManyToOne
    @JoinColumn(name = "Cliente_Usuario_codigo", nullable = false)
    private Cliente cliente;

    @Lob
    @Column(nullable = false)
    private String consulta;

    @Lob
    @Column(nullable = false)
    private String respuesta;

    @Column(nullable = false)
    private LocalDate fecha;
}