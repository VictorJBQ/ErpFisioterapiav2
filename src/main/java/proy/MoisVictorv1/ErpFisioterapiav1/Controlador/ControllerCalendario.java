package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.Calendario.AsignarFomrClale;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.Calendario.AsignarFormClale2;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.pacientes.PacienteFormCita;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Pacientes;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;

@Controller
public class ControllerCalendario {
	@Autowired
	CitasRepositorio citasRepositorio;
	@Autowired
	PacientesRepositorio pacientesRepositorio;
	
	@RequestMapping(path="intranet/calendario/calendario")
	public String verCalendario(Model model) {
		Iterable<Pacientes> itUsu = pacientesRepositorio.findAll();
		List<Pacientes> listaUsuarios = new ArrayList<Pacientes>();
		itUsu.forEach(listaUsuarios::add);
		model.addAttribute("lista", listaUsuarios);
		
		return "intranet/calendario/calendario";
	}
	
	@RequestMapping(path="cal")
	public String verCalendardio(Model model) {
		
		return "intranet/calendario/pruebaC";
	}
	
	@GetMapping(path="citas")
	@ResponseBody
	public List<Citas> obtenerCitas() {
	    // Aquí deberías llamar a tu servicio o repositorio para obtener las citas
	    List<Citas> citas = (List<Citas>) citasRepositorio.findByEstadoIn(Arrays.asList("libre", "cancelada"));;
	    
	    List<Citas> aux =  new ArrayList<Citas>();
	    for(Citas c: citas) {
	    	if ((c.getFecha().isEqual(LocalDate.now()) && c.getHora().isAfter(LocalTime.now())) || c.getFecha().isAfter(LocalDate.now())) {
	    	    aux.add(c);
	    	}
	    }
	    return aux;
	}
	
	

	@PostMapping(value = "asignarCale")
	@ResponseBody
	public ResponseEntity<?> asignar(@RequestBody @Valid AsignarFomrClale e, BindingResult result) {
	    if (result.hasErrors()) {
	        // Si hay errores de validación, se envía un objeto con los errores y un código de estado HTTP 400 (Bad Request)
	        Map<String, String> errores = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.put(error.getField(), error.getDefaultMessage());
	            
	        }
	        return ResponseEntity.badRequest().body(errores);
	    } else {
	    	String estado="pendiente-Confirmar";
	        Citas cita= citasRepositorio.findById(e.getIdCita());
	        if(cita.getEstado().equals("cancelada") || cita.getEstado().equals("salvada-pendiente")) {
	        	estado="salvada-pendiente";
	        }
	        cita.setPacientes(pacientesRepositorio.findById(e.getIdPaciente()));
	        cita.setEstado(estado);
	        
	       citasRepositorio.save(cita);
	       
	    
	        return ResponseEntity.ok(e);
	    }
	}
	

	@PostMapping(value = "asignarCale2")
	@ResponseBody
	public ResponseEntity<?> asignar2(@RequestBody @Valid AsignarFormClale2 e, BindingResult result) {
	    if (result.hasErrors()) {
	        // Si hay errores de validación, se envía un objeto con los errores y un código de estado HTTP 400 (Bad Request)
	        Map<String, String> errores = new HashMap<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.put(error.getField(), error.getDefaultMessage());
	            
	        }
	        return ResponseEntity.badRequest().body(errores);
	    } else {
	    	String estado="pendiente-Confirmar";
	        Citas cita= citasRepositorio.findById(e.getIdCita());
	        if(cita.getEstado().equals("cancelada") || cita.getEstado().equals("salvada-pendiente")) {
	        	estado="salvada-pendiente";
	        }
	        Pacientes paciente= new Pacientes(e.getNomPaciente(), Integer.parseInt(e.getTelPaciente()));
	        pacientesRepositorio.save(paciente);
	        
	        
	        cita.setPacientes(paciente);
	       cita.setEstado("pendiente-Confirmar");
	        
	       citasRepositorio.save(cita);
	       
	    
	        return ResponseEntity.ok(e);
	    }
	}

}
