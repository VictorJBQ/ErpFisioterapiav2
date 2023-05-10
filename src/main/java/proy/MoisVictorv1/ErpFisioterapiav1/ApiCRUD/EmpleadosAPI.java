package proy.MoisVictorv1.ErpFisioterapiav1.ApiCRUD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.empleados.EmpleadosFormA;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.empleados.EmpleadosFormE;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Empleados;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.EmpleadosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.RolesRepositorio;

@RestController
@RequestMapping(value="/api-emp/")//esto es para dar la RUtA al controlador como de origen y lo de abajo se le añade,
@CrossOrigin("*")
public class EmpleadosAPI {
	
	@Autowired
	private EmpleadosRepositorio empleadosRepositorio;
	
	@Autowired
	private RolesRepositorio rolesRepositorio;
	

	
	@GetMapping("allemple")
	public List<Empleados> getAllemple(){
		return (List<Empleados>) empleadosRepositorio.findAll();
		
	}
	
	@GetMapping("user")
	public String getUser(Authentication authentication){
		 UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return userDetails.getUsername();
		
	}
	
	
	@PostMapping(value = "editarEmple")
	@ResponseBody
	public ResponseEntity<?> editar(@RequestBody @Valid EmpleadosFormE e,BindingResult result) {
		System.out.println(e.getIdentificador());
	    if (result.hasErrors()) {
	        // Si hay errores de validación, se envía un objeto con los errores y un código de estado HTTP 400 (Bad Request)
	        Map<String, String> errores = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.put(error.getField(), error.getDefaultMessage());
	            
	        }
	        System.out.println(errores.toString());
	        return ResponseEntity.badRequest().body(errores);
	    } else {
	        var op = empleadosRepositorio.findById(e.getIdentificador());
	        if (op.isPresent()) {
	        	Empleados empleado= empleadosRepositorio.findByidentificador(e.getIdentificador());
	    		empleado.setNombre(e.getNombre());
	    		empleado.setRoles(rolesRepositorio.findByTipo(e.getRoles()));
	    		empleado.setEmail(e.getEmailE());
	    		empleadosRepositorio.save(empleado);
		            return ResponseEntity.ok(e);
	        }else {
	        	 Map<String, String> errores2 = new HashMap<>();
		            
		            errores2.put("identificadorE", "Usuario no existente");
		            

	            return ResponseEntity.badRequest().body(errores2);
	        }
	   
	    }
	}
	
	@PostMapping(value = "addEmple")
	@ResponseBody
	public ResponseEntity<?> crear(@RequestBody @Valid EmpleadosFormA e, BindingResult result) {
	    if (result.hasErrors()) {
	        // Si hay errores de validación, se envía un objeto con los errores y un código de estado HTTP 400 (Bad Request)
	        Map<String, String> errores = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.put(error.getField(), error.getDefaultMessage());
	            
	        }
	        System.out.println(errores.toString());
	        return ResponseEntity.badRequest().body(errores);
	    } else {
	        var op = empleadosRepositorio.findById(e.getIdentificador());
	        if (op.isPresent()) {
	            // Si el empleado ya existe, se envía un objeto con un mensaje de error y un código de estado HTTP 400 (Bad Request)
	            Map<String, String> errores2 = new HashMap<>();
	            
		            errores2.put("identificadorA", "Usuario ya existente");
		            
		        
	            System.out.println(errores2.toString());
	            return ResponseEntity.badRequest().body(errores2);
	        }
	        var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	        e.setPassword(encoder.encode(e.getPassword()));
	        Empleados emple= new Empleados(e.getIdentificador(), e.getNombre(), e.getPassword(),e.getEmailA(), rolesRepositorio.findByTipo(e.getRoles()));
	        empleadosRepositorio.save(emple);
	        return ResponseEntity.ok(e);
	    }
	}
	
	
	 @DeleteMapping("deleteEm/{id}")
	 public ResponseEntity<Empleados> eliminarRegistro(@PathVariable String id) {
		 System.out.println(id);
	        Empleados empleado = empleadosRepositorio.findByidentificador(id);
	        if (empleado!=null) {
	            empleadosRepositorio.delete(empleado);
	            return new  ResponseEntity<Empleados> (empleado,HttpStatus.OK);
	        } else {
	            return new  ResponseEntity<Empleados> (empleado,HttpStatus.NO_CONTENT);
	        }
	    }
	
}
