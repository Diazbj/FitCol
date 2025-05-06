package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.repositorio.UsuarioRepo;
import co.edu.uniquindio.proyecto.seguridad.JWTUtils;
import co.edu.uniquindio.proyecto.servicios.LoginServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServicioImpl implements LoginServicio {

    private final UsuarioRepo usuarioRepo;
    private final JWTUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {


        Optional<Usuario> optionalUsuario = usuarioRepo.findByEmail(loginDTO.email());


        if (optionalUsuario.isEmpty()) {
            throw new Exception("El usuario no existe");
        }


        Usuario usuario = optionalUsuario.get();


        // Verificar si la contraseña es correcta usando el PasswordEncoder
        if (!passwordEncoder.matches(loginDTO.password(), usuario.getPassword())) {
            throw new Exception("Contraseña incorrecta");
        }


        String token = jwtUtils.generateToken(usuario.getUsuarioId(), crearClaims(usuario));
        return new TokenDTO(token);
    }


    private Map<String, String> crearClaims(Usuario usuario) {
        return Map.of(
                "email", usuario.getEmail(),
                "nombre", usuario.getPrimerNombre(),
                "rol", "ROLE_" + usuario.getClass().getSimpleName().toUpperCase()
        );
    }
}
