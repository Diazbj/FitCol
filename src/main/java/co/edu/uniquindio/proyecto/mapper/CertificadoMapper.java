package co.edu.uniquindio.proyecto.mapper;


import co.edu.uniquindio.proyecto.dto.entrenador.CertificacionDTO;
import co.edu.uniquindio.proyecto.modelo.entrenador.Certificacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CertificadoMapper {

    Certificacion fromCrearDTOtoEntity(CertificacionDTO certificacionDTO);
}
