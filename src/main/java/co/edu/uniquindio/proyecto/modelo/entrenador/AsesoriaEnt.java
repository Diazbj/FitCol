package co.edu.uniquindio.proyecto.modelo.entrenador;

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
@IdClass(AsesoriaEntId.class)
public class AsesoriaEnt implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "Entrenador_Usuario_codigo")
    private Entrenador entrenador;

    @Id
    @ManyToOne
    @JoinColumn(name = "Cliente_Usuario_codigo")
    private Cliente cliente;

    @Id
    private LocalDate fecha;

    @Lob
    private String consulta;

    @Lob
    private String respuesta;
}
