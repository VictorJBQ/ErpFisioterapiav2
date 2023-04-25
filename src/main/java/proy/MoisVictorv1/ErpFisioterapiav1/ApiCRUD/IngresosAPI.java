package proy.MoisVictorv1.ErpFisioterapiav1.ApiCRUD;

import java.time.LocalDate;
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
import proy.MoisVictorv1.ErpFisioterapiav1.Form.Ingresos.IngresosFormA;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.Ingresos.IngresosFormE;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.gastos.GastosFormA;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.gastos.GastosFormE;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Gastos;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Ingresos;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.IngresosRepositorio;

@RestController
@RequestMapping(value="/api-ing/")//esto es para dar la RUtA al controlador como de origen y lo de abajo se le añade,
@CrossOrigin("*")
public class IngresosAPI {

	@Autowired
	IngresosRepositorio ingresosRepositorio;
	
	@GetMapping("all")
	public List<Ingresos> getAll(){
		return (List<Ingresos>) ingresosRepositorio.findAll();
		
	}
	
	
	@PostMapping(value = "add")
	@ResponseBody
	public ResponseEntity<?> crear(@RequestBody @Valid IngresosFormA e, BindingResult result) {
		
	    if (result.hasErrors()) {
	        // Si hay errores de validación, se envía un objeto con los errores y un código de estado HTTP 400 (Bad Request)
	        Map<String, String> errores = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.put(error.getField(), error.getDefaultMessage());
	            
	        }

	        return ResponseEntity.badRequest().body(errores);
	    } else {
	        Ingresos ing = new Ingresos(e.getTipoA(),
	        		Double.parseDouble(e.getImporteA().replace(",", ".")));
	        ingresosRepositorio.save(ing);
	       
	        return ResponseEntity.ok(e);
	    }
	}
	
	
	@PostMapping(value = "editar")
	@ResponseBody
	public ResponseEntity<?> editar(@RequestBody @Valid IngresosFormE e,BindingResult result) {
		
	    if (result.hasErrors()) {
	        // Si hay errores de validación, se envía un objeto con los errores y un código de estado HTTP 400 (Bad Request)
	        Map<String, String> errores = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.put(error.getField(), error.getDefaultMessage());
	            
	        }
	        System.out.println(errores.toString());
	        return ResponseEntity.badRequest().body(errores);
	    } else {
	        var op = ingresosRepositorio.findById(Integer.parseInt(e.getIdE()));
	        if (op.isPresent()) {
	        	Ingresos ing= ingresosRepositorio.findById(e.getIdE());
	        	ing.setImporte(Double.parseDouble(e.getImporteE().replace(",", ".")));
	        	ing.setTipo(e.getTipoE());
	        	ingresosRepositorio.save(ing);
		            return ResponseEntity.ok(e);
	        }else {
	        	 Map<String, String> errores2 = new HashMap<>();
		            
		            errores2.put("idE", " No existente");
		            

	            return ResponseEntity.badRequest().body(errores2);
	        }
	   
	    }
	}
	
	@DeleteMapping("delete/{id}")
	 public ResponseEntity<Ingresos> eliminarRegistro(@PathVariable String id) {
		 System.out.println(id);
		 Ingresos ing= ingresosRepositorio.findById(id);
	        if (ing!=null) {
	        	ingresosRepositorio.delete(ing);
	            return new  ResponseEntity<Ingresos> (ing,HttpStatus.OK);
	        } else {
	            return new  ResponseEntity<Ingresos> (ing,HttpStatus.NO_CONTENT);
	        }
	    }
	
}
