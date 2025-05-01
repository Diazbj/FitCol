package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {

    @Id
    private Long codFactura;

    private LocalDate fecha;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "codMetodo")
    private MetodoPago metodoPago;

    @OneToMany(mappedBy = "factura")
    private List<Suscripcion> suscripciones;
}
