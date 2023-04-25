package proy.MoisVictorv1.ErpFisioterapiav1.ApiCRUD;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

import proy.MoisVictorv1.ErpFisioterapiav1.Form.pacientes.PacienteFormCita;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.pacientes.PacientesFormA;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.pacientes.PacientesFormE;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Pacientes;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.TarifasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Utilidades.ValidarNIFyNIE;

@RestController
@RequestMapping(value="/api-pct/")//esto es para dar la RUtA al controlador como de origen y lo de abajo se le añade,
@CrossOrigin("*")
public class PacientesAPI {

	@Autowired
	PacientesRepositorio pacientesRepositorio;
	
	@Autowired
	TarifasRepositorio tarifasRepositorio;
	
	@Autowired
	CitasRepositorio citasRepositorio;
	
	@GetMapping("all")
	public List<Pacientes> getAll(){
		return (List<Pacientes>) pacientesRepositorio.findAll();
		
	}
	
	
	
	@PostMapping(value = "add")
	@ResponseBody
	public ResponseEntity<?> crear(@RequestBody @Valid PacientesFormA e, BindingResult result) {
	    if (result.hasErrors()) {
	        // Si hay errores de validación, se envía un objeto con los errores y un código de estado HTTP 400 (Bad Request)
	        Map<String, String> errores = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.put(error.getField(), error.getDefaultMessage());
	            
	        }
	        System.out.println(errores.toString());
	        return ResponseEntity.badRequest().body(errores);
	    } else {
	        Pacientes op = pacientesRepositorio.findByDni(e.getDniA());
	        boolean dni= ValidarNIFyNIE.validarDni(e.getDniA().toUpperCase());
	        if (op!=null){
	            // Si el empleado ya existe, se envía un objeto con un mensaje de error y un código de estado HTTP 400 (Bad Request)
	            Map<String, String> errores2 = new HashMap<>();
	            
		            errores2.put("dniA", "Usuario ya existente");

	            System.out.println(errores2.toString());
	            return ResponseEntity.badRequest().body(errores2);
	        }else if(dni==false) {
	        	/*Esto es para verificar si el dni es formato correcto, descomentar cuando finalicen pruebas*/
	        	//  Map<String, String> errores2 = new HashMap<>();
		            
		        //    errores2.put("dniA", "Formato no válido");
		        //    return ResponseEntity.badRequest().body(errores2);
	        }
	        System.out.println(e.getTarifaA());
	        
	        /*Inserción nuevo paciente comprobar dni*/
	        Pacientes paciente= new Pacientes(e.getDniA(),
	        		e.getNombreA(),
	        		e.getApellidosA(), e.getDomicilioA(),
	        		e.getCpA(),
	        		Integer.parseInt(e.getTelA()),
	        		e.getConoceA(),
	        		e.getPoblaA(),
	        		tarifasRepositorio.findByTipo(e.getTarifaA()));
	        
	        
	      pacientesRepositorio.save(paciente);
	    
	        return ResponseEntity.ok(e);
	    }
	}
	
	@PostMapping(value = "asignar")
	@ResponseBody
	public ResponseEntity<?> asignar(@RequestBody @Valid PacienteFormCita e, BindingResult result) {
	    if (result.hasErrors()) {
	        // Si hay errores de validación, se envía un objeto con los errores y un código de estado HTTP 400 (Bad Request)
	        Map<String, String> errores = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.put(error.getField(), error.getDefaultMessage());
	            
	        }
	        return ResponseEntity.badRequest().body(errores);
	    } else {
	        Citas cita= citasRepositorio.findById(e.getIdCita());
	        cita.setPacientes(pacientesRepositorio.findById(e.getIdPaciente()));
	        cita.setEstado("pendinte-Confirmar");
	        
	       citasRepositorio.save(cita);
	       
	    
	        return ResponseEntity.ok(e);
	    }
	}
	
	
	@PostMapping(value = "editar")
	@ResponseBody
	public ResponseEntity<?> editar(@RequestBody @Valid PacientesFormE e, BindingResult result) {
	    if (result.hasErrors()) {
	        // Si hay errores de validación, se envía un objeto con los errores y un código de estado HTTP 400 (Bad Request)
	        Map<String, String> errores = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.put(error.getField(), error.getDefaultMessage());
	            
	        }
	        
	        return ResponseEntity.badRequest().body(errores);
	    } else {
	    	Pacientes pac= pacientesRepositorio.findByDni(e.getDniE());
	    	
	 
	       if(e.getDniE().toUpperCase().equals(e.getDniViejoE().toUpperCase())) {
	   
	    	   Pacientes paciente= pacientesRepositorio.findById(e.getIdE());
	    	   paciente.setApellidos(e.getApellidosE());
	    	   paciente.setCodigoPostal(e.getCpE());
	    	   paciente.setDomicilio(e.getDomicilioE());
	    	   paciente.setNombre(e.getNombreE());
	    	   paciente.setPoblacion(e.getPoblaE());
	    	   paciente.setSabeDemi(e.getConoceE());
	    	   paciente.setTelefono(Integer.parseInt(e.getTelE()));
	    	   paciente.setTarifas(tarifasRepositorio.findByTipo(e.getTarifaE()));
	    	   
	    	   pacientesRepositorio.save(paciente);
	    	   return ResponseEntity.ok(e);
	       }else if(pac!=null) {
	    	   Map<String, String> errores2 = new HashMap<>();
	            
	            errores2.put("dniE", "Usuario ya existente");

           System.out.println(errores2.toString());
           return ResponseEntity.badRequest().body(errores2);
	       }
	       
	       Pacientes paciente= pacientesRepositorio.findById(e.getIdE());
    	   paciente.setApellidos(e.getApellidosE());
    	   paciente.setDni(e.getDniE());
    	   paciente.setCodigoPostal(e.getCpE());
    	   paciente.setDomicilio(e.getDomicilioE());
    	   paciente.setNombre(e.getNombreE());
    	   paciente.setPoblacion(e.getPoblaE());
    	   paciente.setSabeDemi(e.getConoceE());
    	   paciente.setTelefono(Integer.parseInt(e.getTelE()));
    	   paciente.setTarifas(tarifasRepositorio.findByTipo(e.getTarifaE()));
    	   
    	   pacientesRepositorio.save(paciente);
	       
	        
	    
	    
	        return ResponseEntity.ok(e);
	    }
	}
	
	
	
	@DeleteMapping("delete/{id}")
	 public ResponseEntity<Pacientes> eliminarRegistro(@PathVariable String id) {
		 System.out.println(id);
	        Pacientes paciente = pacientesRepositorio.findById(id);
	        if (paciente!=null) {
	        	pacientesRepositorio.delete(paciente);
	            return new  ResponseEntity<Pacientes> (paciente,HttpStatus.OK);
	        } else {
	            return new  ResponseEntity<Pacientes> (paciente,HttpStatus.NO_CONTENT);
	        }
	    }
}
