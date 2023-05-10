package proy.MoisVictorv1.ErpFisioterapiav1.Utilidades;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

public class UrlGenerator {
    public static  String generateUrl(String email) {
    	String encodedEmail = Base64.getUrlEncoder().encodeToString(email.getBytes());
        UUID uuid = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = now.plusMinutes(5);
        String encodedUuid = Base64.getUrlEncoder().encodeToString(uuid.toString().getBytes());
        String encodedExpirationTime = Base64.getUrlEncoder().encodeToString(expirationTime.toString().getBytes());
        StringBuilder sb = new StringBuilder();
        sb.append("https://jbsolutions.up.railway.app/reset-password?token=");
        sb.append(encodedUuid);
        sb.append("&expiration=");
        sb.append(encodedExpirationTime);
        sb.append("&email=");
        sb.append(encodedEmail);
        return sb.toString();
    }
}