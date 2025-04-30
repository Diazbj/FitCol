package co.edu.uniquindio.proyecto.repositorio;

import co.edu.uniquindio.proyecto.dto.cliente.ClienteDTO;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, String> {

    //JPQL Java Persistence Query language
    //@Query("select new co.edu.uniquindio.proyecto.dto.cliente.ClienteDTO(c.id, c.primerNombre, c.segundoNombre, c.primerApellido, c.segundoApellido, c.email, c.sexo, c.historialMedico,  12 , c.peso, c.altura, (select t.numero from UsuarioTelefono t join t.usuario u where u.id = c.id)) from Cliente c where c.id = :id")
    //ClienteDTO obtenerCliente(String id);

}
