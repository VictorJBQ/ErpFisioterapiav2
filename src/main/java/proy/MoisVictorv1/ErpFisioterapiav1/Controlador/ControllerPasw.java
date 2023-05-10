package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.restPsw.OlvidoPsw;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.restPsw.ValidarPsw;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Empleados;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.EmpleadosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Servicio.EmailSenderService;
import proy.MoisVictorv1.ErpFisioterapiav1.Utilidades.TokenList;
import proy.MoisVictorv1.ErpFisioterapiav1.Utilidades.UrlGenerator;

@RestController
@RequestMapping(value = "/api-psw/") // esto es para dar la RUtA al controlador como de origen y lo de abajo se le
										// añade,
@CrossOrigin("*")
public class ControllerPasw {
	@Autowired
	EmpleadosRepositorio empleadosRepositorio;
	@Autowired
	private EmailSenderService emailSenderService;

	/* igual hacerlo con js para controlar los errores y mostrar una cosa u otra */
	@PostMapping("rest")
	@ResponseBody
	public ResponseEntity<?> procesarFormulario(@RequestBody @Valid OlvidoPsw e, BindingResult result)
			throws IOException {
	
		if (result.hasErrors()) {
			// Si hay errores de validación, se envía un objeto con los errores y un código
			// de estado HTTP 400 (Bad Request)
			Map<String, String> errores = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errores.put(error.getField(), error.getDefaultMessage());

			}

			return ResponseEntity.badRequest().body(errores);
		} else {
			
			Empleados emp = empleadosRepositorio.findByemail(e.getEmail());
			if (emp == null) {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("email", "Usuario no encontrado");

				return ResponseEntity.badRequest().body(errores2);
			} else {
				String TOKEN = UrlGenerator.generateUrl(e.getEmail());
				String tok= TOKEN.substring(TOKEN.indexOf("=") + 1, TOKEN.indexOf("&"));
				System.out.println(TokenList.tokens.toString());
				
				emailSenderService.sendSimpleEmail(e.getEmail(), TOKEN, emp.getNombre());
				return ResponseEntity.ok(e);
			}
		}

	}

	@PostMapping("/reset-password")
	@ResponseBody
	public ResponseEntity<?> processResetPasswordForm(@RequestBody @Valid ValidarPsw e, BindingResult result) {
		System.out.println(e.getExpiration());
		if (result.hasErrors()) {
			// Si hay errores de validación, se envía un objeto con los errores y un código
			// de estado HTTP 400 (Bad Request)
			Map<String, String> errores = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errores.put(error.getField(), error.getDefaultMessage());

			}

			return ResponseEntity.badRequest().body(errores);
		} else {
			String decodedToken = new String(Base64.getUrlDecoder().decode(e.getToken()));
			
			String decodedExpiration = new String(Base64.getUrlDecoder().decode(e.getExpiration()));
			System.out.println(decodedExpiration);
			UUID uuid = UUID.fromString(decodedToken);
			LocalDateTime expirationTime = LocalDateTime.parse(decodedExpiration);
			if (LocalDateTime.now().isAfter(expirationTime) ) {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("expirado", "Contraseñas no coinciden");

				return ResponseEntity.badRequest().body(errores2);
			} 
			if (!e.getPsw1().equals(e.getPs2())) {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("psw1", "Contraseñas no coinciden");
				errores2.put("ps2", "Contraseñas no coinciden");

				return ResponseEntity.badRequest().body(errores2);
			} else {
				var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
				String decodedEmail = new String(Base64.getUrlDecoder().decode(e.getEmail1()));
				Empleados em = empleadosRepositorio.findByemail(decodedEmail);
				em.setPassword(encoder.encode(e.getPsw1()));
				empleadosRepositorio.save(em);
				
				
				return ResponseEntity.ok(e);
			}
		}
		
	}

}
