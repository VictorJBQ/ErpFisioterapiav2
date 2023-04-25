package proy.MoisVictorv1.ErpFisioterapiav1.ApiCRUD;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import proy.MoisVictorv1.ErpFisioterapiav1.Form.gastos.GastosFormA;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.gastos.GastosFormE;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Empleados;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Gastos;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.EmpleadosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.GastosRepositorio;

@RestController
@RequestMapping(value="/api-gst/")//esto es para dar la RUtA al controlador como de origen y lo de abajo se le añade,
@CrossOrigin("*")
public class GastosAPI {
	
	@Autowired
	GastosRepositorio gastosRepositorio;
	
	@Autowired
	EmpleadosRepositorio empleadosRepositorio;
	
	@GetMapping("all")
	public List<Gastos> getAll(){
		System.out.println("holaa");
		return (List<Gastos>) gastosRepositorio.findAll();
		
	}
	
	
	@PostMapping(value = "add")
	@ResponseBody
	public ResponseEntity<?> crear(@RequestBody @Valid GastosFormA e, BindingResult result) {
		
	    if (result.hasErrors()) {
	        // Si hay errores de validación, se envía un objeto con los errores y un código de estado HTTP 400 (Bad Request)
	        Map<String, String> errores = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.put(error.getField(), error.getDefaultMessage());
	            
	        }

	        return ResponseEntity.badRequest().body(errores);
	    } else {
	        Gastos gasto= new Gastos(LocalDate.parse(e.getFechaA()),
	        		e.getTipoA(),
	        		Double.parseDouble(e.getImporteA().replace(",", ".")),
	        		empleadosRepositorio.findByidentificador(e.getEmpleadoA()));
	        		gastosRepositorio.save(gasto);
	       
	        return ResponseEntity.ok(e);
	    }
	}
	
	@PostMapping(value = "editar")
	@ResponseBody
	public ResponseEntity<?> editar(@RequestBody @Valid GastosFormE e,BindingResult result) {
		
	    if (result.hasErrors()) {
	        // Si hay errores de validación, se envía un objeto con los errores y un código de estado HTTP 400 (Bad Request)
	        Map<String, String> errores = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.put(error.getField(), error.getDefaultMessage());
	            
	        }
	        System.out.println(errores.toString());
	        return ResponseEntity.badRequest().body(errores);
	    } else {
	        var op = gastosRepositorio.findById(Integer.parseInt(e.getIdE()));
	        if (op.isPresent()) {
	        	Gastos gastos= gastosRepositorio.findById(e.getIdE());
	        	gastos.setEmpleados(empleadosRepositorio.findByidentificador(e.getEmpleadoE()));
	        	gastos.setFecha(LocalDate.parse(e.getFechaE()));
	        	gastos.setImporte(Double.parseDouble(e.getImporteE().replace(",", ".")));
	        	gastos.setTipo(e.getTipoE());
	        	gastosRepositorio.save(gastos);
		            return ResponseEntity.ok(e);
	        }else {
	        	 Map<String, String> errores2 = new HashMap<>();
		            
		            errores2.put("idE", " No existente");
		            

	            return ResponseEntity.badRequest().body(errores2);
	        }
	   
	    }
	}
	
	 @DeleteMapping("delete/{id}")
	 public ResponseEntity<Gastos> eliminarRegistro(@PathVariable String id) {
		 System.out.println(id);
	        Gastos gastos = gastosRepositorio.findById(id);
	        if (gastos!=null) {
	        	gastosRepositorio.delete(gastos);
	            return new  ResponseEntity<Gastos> (gastos,HttpStatus.OK);
	        } else {
	            return new  ResponseEntity<Gastos> (gastos,HttpStatus.NO_CONTENT);
	        }
	    }


}
