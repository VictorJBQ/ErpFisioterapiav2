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
import proy.MoisVictorv1.ErpFisioterapiav1.Form.empleados.EmpleadosFormE;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.tarifas.TarifasFormA;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.tarifas.TarifasFormE;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Empleados;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Ingresos;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Tarifas;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.TarifasRepositorio;

@RestController
@RequestMapping(value="/api-trf/")//esto es para dar la RUtA al controlador como de origen y lo de abajo se le añade,
@CrossOrigin("*")
public class TarifasAPI {
	
	@Autowired
	TarifasRepositorio tarifasRepositorio;
	
	
	@GetMapping("all")
	public List<Tarifas> getAllemple(){
		return (List<Tarifas>) tarifasRepositorio.findAll();
		
	}
	
	@PostMapping(value = "editar")
	@ResponseBody
	public ResponseEntity<?> editar(@RequestBody @Valid TarifasFormE e,BindingResult result) {
	    if (result.hasErrors()) {
	        // Si hay errores de validación, se envía un objeto con los errores y un código de estado HTTP 400 (Bad Request)
	        Map<String, String> errores = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.put(error.getField(), error.getDefaultMessage());
	            
	        }
	        System.out.println(errores.toString());
	        return ResponseEntity.badRequest().body(errores);
	    } else {
	        var op = tarifasRepositorio.findById(e.getTipoViejaE());
	        if (op.isPresent()) {
	        	Tarifas tarifa= tarifasRepositorio.findByTipo(e.getTipoViejaE());
	    			tarifa.setPrecio(Double.parseDouble(e.getPrecioE().replace(",", ".")));
	    			tarifasRepositorio.save(tarifa);
		            return ResponseEntity.ok(e);
	        }else {
	        	 Map<String, String> errores2 = new HashMap<>();
		            
		            errores2.put("tipoNuevaE", "Tarifa no existente");
		            

	            return ResponseEntity.badRequest().body(errores2);
	        }
	   
	    }
	}
	
	@PostMapping(value = "add")
	@ResponseBody
	public ResponseEntity<?> add(@RequestBody @Valid TarifasFormA e,BindingResult result) {
	    if (result.hasErrors()) {
	        // Si hay errores de validación, se envía un objeto con los errores y un código de estado HTTP 400 (Bad Request)
	        Map<String, String> errores = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.put(error.getField(), error.getDefaultMessage());
	            
	        }
	        System.out.println(errores.toString());
	        return ResponseEntity.badRequest().body(errores);
	    } else {
	        var op = tarifasRepositorio.findById(e.getTipoNuevaA());
	        if (op.isPresent()) {
	        	 Map<String, String> errores2 = new HashMap<>();
		            
		            errores2.put("tipoNuevaA", "Tarifa ya existente");
		            

	            return ResponseEntity.badRequest().body(errores2);
	        	
	        }else {

	        	Tarifas tarifa= new Tarifas(e.getTipoNuevaA(),
	        			Double.parseDouble(e.getPrecioA().replace(",", ".")));
	    			
	    			tarifasRepositorio.save(tarifa);
		            return ResponseEntity.ok(e);
	        }
	   
	    }
	}
	
	@DeleteMapping("delete/{id}")
	 public ResponseEntity<Tarifas> eliminarRegistro(@PathVariable String id) {
		 System.out.println(id);
		 Tarifas ing= tarifasRepositorio.findByTipo(id);
	        if (ing!=null) {
	        	tarifasRepositorio.delete(ing);
	            return new  ResponseEntity<Tarifas> (ing,HttpStatus.OK);
	        } else {
	            return new  ResponseEntity<Tarifas> (ing,HttpStatus.NO_CONTENT);
	        }
	    }

}
