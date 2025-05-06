package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;

public interface LoginServicio {

    TokenDTO login(LoginDTO loginDTO) throws Exception ;
}
