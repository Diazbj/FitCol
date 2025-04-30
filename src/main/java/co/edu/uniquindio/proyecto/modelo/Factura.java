package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Factura")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codFactura")
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String nombre;

//    @ManyToOne
//    @JoinColumn(name = "MetodoPago_codMetodo")
//    private MetodoPago metodoPago;

//    @ManyToOne
//    @JoinColumn(name = "EstadoFactura_codEstado")
//    private EstadoFactura estadoFactura;

    @OneToMany(mappedBy = "factura")
    private List<Suscripcion> suscripciones;

    // Getters y Setters
}
