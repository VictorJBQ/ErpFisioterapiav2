package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.inicio.CambioPSForm;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Empleados;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Pacientes;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Tarifas;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.EmpleadosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.TarifasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Servicio.EmailSenderService;
import proy.MoisVictorv1.ErpFisioterapiav1.Utilidades.TokenList;
import proy.MoisVictorv1.ErpFisioterapiav1.Utilidades.UrlGenerator;

@Controller
public class ControllerGeneral {
	@Autowired
	CitasRepositorio citasRepositorio;
	@Autowired
	PacientesRepositorio pacientesRepositorio;

	@Autowired
	TarifasRepositorio tarifasRepositorio;
	@Autowired
	EmpleadosRepositorio empleadosRepositorio;

	@Autowired
	private EmailSenderService emailSenderService;

	@RequestMapping("/intranet/inicio")
	public String bienvenida(Model model, Authentication authentication) {
		HashMap<String, String> empleado = new HashMap<>();

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Empleados emp = empleadosRepositorio.findByidentificador(userDetails.getUsername());
		Integer in = citasRepositorio.citasTotalEmpleado("T01");
		Integer lib = citasRepositorio.citasMesLibEmpleado("T01", LocalDate.now().getYear(),
				LocalDate.now().getMonthValue());
		Integer canc = citasRepositorio.citasMesCanceladasEmpleado("T01", LocalDate.now().getYear(),
				LocalDate.now().getMonthValue());
		Integer tol = citasRepositorio.citasMesTotalladasEmpleado("T01", LocalDate.now().getYear(),
				LocalDate.now().getMonthValue());
		Integer ter = citasRepositorio.citasMesTermidasEmpleado("T01", LocalDate.now().getYear(),
				LocalDate.now().getMonthValue());

		empleado.put("codigo", emp.getIdentificador());
		empleado.put("nombre", emp.getNombre());
		empleado.put("rol", emp.getRoles().getTipo());
		empleado.put("citas", "" + in);
		empleado.put("citasTotalmes", "" + tol);
		empleado.put("citasCanceladasmes", "" + canc);
		empleado.put("citasLibresmes", "" + lib);
		empleado.put("citasTerminadasmes", "" + ter);

		List<Tarifas> tarifas = (List<Tarifas>) tarifasRepositorio.findAll();
		model.addAttribute("tari", tarifas);
		Iterable<Pacientes> itUsu = pacientesRepositorio.findAll();
		List<Pacientes> listaUsuarios = new ArrayList<Pacientes>();
		itUsu.forEach(listaUsuarios::add);
		model.addAttribute("lista", listaUsuarios);
		model.addAttribute("datos", empleado);
		if (emp.getIdentificador().equals("T01")) {
			model.addAttribute("codigoEmpleado", "x");
		} else {
			model.addAttribute("codigoEmpleado", "y");
		}
		return "/intranet/inicio";
	}

	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("mensaje", model.getAttribute("mensaje"));
		return "/index";
	}

	@GetMapping("/login")
	public String add2() {
		return "/login";
	}

	@GetMapping("/rest")
	public String rest() {
		return "/rest";
	}

	@PostMapping(path = "/login")
	public String add1() {
		return "/login";
	}



	@GetMapping("/reset-password")
	public String showResetPasswordForm(@RequestParam("token") String token,
			@RequestParam("expiration") String expiration, String email, Model model) {
		String decodedEmail = new String(Base64.getUrlDecoder().decode(email));
		String decodetoken = new String(Base64.getUrlDecoder().decode(token));
		System.out.println(decodetoken);

		try {
			String decodedToken = new String(Base64.getUrlDecoder().decode(token));
			String decodedExpiration = new String(Base64.getUrlDecoder().decode(expiration));
			UUID uuid = UUID.fromString(decodedToken);
			LocalDateTime expirationTime = LocalDateTime.parse(decodedExpiration);
			if (LocalDateTime.now().isAfter(expirationTime)) {
				
				return "/login";
			} else {
				// TODO: mostrar el formulario de cambio de contraseña
				model.addAttribute("token", token);
				model.addAttribute("email", email);
				model.addAttribute("ex", expiration);
			}
		} catch (Exception e) {
			
		}
		return "reset-password";
	}



	@PostMapping(path = "/intranet/cambioPS")
	public String checkPersonInfo(@Valid CambioPSForm e, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return "/intranet/inicio";
		}
		return null;

	}
}
