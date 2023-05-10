package proy.MoisVictorv1.ErpFisioterapiav1.Servicio;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PasswordResetService {

    // HashMap para almacenar los tokens de reseteo de contraseña
    private final Map<String, LocalDateTime> resetTokenMap = new HashMap<>();

    public String generatePasswordResetToken(String userEmail) {
        // Generar token y fecha de expiración
        String token = UUID.randomUUID().toString();
        LocalDateTime expirationDateTime = LocalDateTime.now().plusHours(24);

        // Almacenar token en el HashMap
        resetTokenMap.put(token, expirationDateTime);

        return token;
    }

    public boolean validatePasswordResetToken(String token) {
        // Verificar si el token existe en el HashMap y si no ha expirado
        LocalDateTime expirationDateTime = resetTokenMap.get(token);
        if (expirationDateTime != null && LocalDateTime.now().isBefore(expirationDateTime)) {
            // Eliminar token del HashMap después de ser utilizado
            resetTokenMap.remove(token);
            return true;
        } else {
            return false;
        }
    }
}