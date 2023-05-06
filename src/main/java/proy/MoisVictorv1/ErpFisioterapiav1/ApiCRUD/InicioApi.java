package proy.MoisVictorv1.ErpFisioterapiav1.ApiCRUD;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import proy.MoisVictorv1.ErpFisioterapiav1.Form.inicio.CambioPSForm;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Empleados;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.EmpleadosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.FacturasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.IngresosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.TarifasRepositorio;

@RestController
@RequestMapping(value = "/api-inicio/") // esto es para dar la RUtA al controlador como de origen y lo de abajo se le
									// añade,
@CrossOrigin("*")
public class InicioApi {

	@Autowired
	CitasRepositorio citasRepositorio;

	@Autowired
	PacientesRepositorio pacientesRepositorio;

	@Autowired
	EmpleadosRepositorio empleadosRepositorio;
	
	@Autowired
	FacturasRepositorio facturasRepositorio;
	
	@Autowired
	IngresosRepositorio ingresosRepositorio;
	
	@Autowired
	TarifasRepositorio tarifasRepositorio;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("hoy")
	public List<Citas> getAllHoy() {
		return (List<Citas>) citasRepositorio.getCitasDiaActual();

	}
	
	@PostMapping(value = "campsw")
	@ResponseBody
	public ResponseEntity<?> crear(@RequestBody @Valid CambioPSForm e, BindingResult result) {
	    if (result.hasErrors()) {
	        // Si hay errores de validación, se envía un objeto con los errores y un código de estado HTTP 400 (Bad Request)
	        Map<String, String> errores = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.put(error.getField(), error.getDefaultMessage());
	            
	        }
	 
	        return ResponseEntity.badRequest().body(errores);
	    } else {
	        Empleados empl = empleadosRepositorio.findByidentificador(e.getIdEmpleado());
	        if (empl==null) {
	            // Si el empleado ya existe, se envía un objeto con un mensaje de error y un código de estado HTTP 400 (Bad Request)
	            Map<String, String> errores2 = new HashMap<>();
	            
		            errores2.put("nueva", "Empleado no existe");
		
	            return ResponseEntity.badRequest().body(errores2);
	        }else if(!e.getNueva().equals(e.getNuevaConfirmada())) {
	            Map<String, String> errores2 = new HashMap<>();

	            errores2.put("nueva", "No coinciden");
	            errores2.put("nuevaConfirmada", "No coinciden");
	
            return ResponseEntity.badRequest().body(errores2);
	        	
	        	
	        }else if(!passwordEncoder.matches(e.getVieja(), empl.getPassword())) {
	        	 Map<String, String> errores2 = new HashMap<>();

		            errores2.put("vieja", "Contraseña incorrecta");
		            return ResponseEntity.badRequest().body(errores2);
	        }
	        var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	        empl.setPassword(encoder.encode(e.getNueva()));
	        empleadosRepositorio.save(empl);

	        return ResponseEntity.ok(e);
	    }
	}

}
