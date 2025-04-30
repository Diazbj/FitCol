package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuario.CrearUsuarioDTO;

public interface UsuarioServicio {
    void crearUsuario(CrearUsuarioDTO crearUsuarioDTO) throws Exception;
    UsuarioDTO obtenerUsuario(String id) throws Exception;
    void eliminarUsuario(String id) throws Exception;
    void editarUsuario(String id, EditarUsuarioDTO editarUsuarioDTO) throws Exception;
}
