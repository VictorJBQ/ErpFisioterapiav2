package proy.MoisVictorv1.ErpFisioterapiav1.ApiCRUD;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Facturas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Ingresos;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Pacientes;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.EmpleadosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.FacturasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.IngresosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.TarifasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Utilidades.IdListDTO;
import proy.MoisVictorv1.ErpFisioterapiav1.Utilidades.ResponseMessage;

@RestController
@RequestMapping(value = "/api-revisar/") // esto es para dar la RUtA al controlador como de origen y lo de abajo se le
									// añade,
@CrossOrigin("*")
public class CitasRevisarAPI {

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

	@GetMapping("allRecordar")
	public List<Citas> getAllRec() {
		   LocalDate fecha = LocalDate.now().plusDays(1);
		   
		return (List<Citas>) citasRepositorio.findByFechaMenosUnoYEstadoPendienteConfirmar(fecha);

	}
	
	@GetMapping("allReserva")
	public List<Citas> getAllRes() {
		return (List<Citas>) citasRepositorio.findByEstadoReservada();

	}
	
	@GetMapping("allTerminar")
	public List<Citas> getAllTer() {
		return (List<Citas>) citasRepositorio.findByEstadoConfirmada();

	}
	
	@GetMapping("allActuales")
	public List<Citas> getAllAct() {
		return (List<Citas>) citasRepositorio.findByFechaActualOrFutura();

	}
	
	
	@PostMapping("terminar/{id}")
	public ResponseEntity<?> terminarCita(@PathVariable String id, @RequestParam String fp) {
		
		Citas ing = citasRepositorio.findById(id);
		if (ing != null) {
			if(ing.getPacientes().getTarifas()==null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Antes de terminar la cita debes rellenar los datos del paciente"));
			}
			if(ing.getEstado().equals("salvada-pendiente")) {
				ing.setEstado("salvada");
				citasRepositorio.save(ing);
			}else {
				ing.setEstado("terminada");
				citasRepositorio.save(ing);
			}
			
			
			Facturas factura = new Facturas(fp, ing.getPacientes().getTarifas().getPrecio(), ing.getFecha(),
					ing);
			facturasRepositorio.save(factura);
			
			Ingresos ingreso= new Ingresos(factura.getImporte(), factura,factura.getFecha());
			
			ingresosRepositorio.save(ingreso);
			
			return new ResponseEntity<Citas>(ing, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Cita no encontrada"));

		}
	}
	
	@GetMapping("confirmar/{id}")
	public ResponseEntity<Citas> confirmarCita(@PathVariable String id) {
		
		Citas ing = citasRepositorio.findById(id);
		if (ing != null) {
			if((ing.getEstado().equals("reservada") && (ing.getFecha().equals(LocalDate.now().plusDays(1)) || ing.getFecha().equals(LocalDate.now()) ))
					|| ing.getEstado()!="reservada") {
				ing.setEstado("confirmada");
				citasRepositorio.save(ing);
				
			}else {
				ing.setEstado("pendiente-Confirma");
				citasRepositorio.save(ing);
			}
			
			
			
			return new ResponseEntity<Citas>(ing, HttpStatus.OK);
		} else {
			return new ResponseEntity<Citas>(ing, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping("cancelar/{id}")
	public ResponseEntity<Citas> cancelarCita(@PathVariable String id) {
		
		Citas ing = citasRepositorio.findById(id);
		String estado= "";
		
		if (ing != null) {
			LocalDate fecha = LocalDate.now().plusDays(1);
			if(ing.getFecha().isBefore(fecha) || ing.getFecha().equals(fecha)) {
				estado="cancelada";
				ing.setEstado(estado);
				citasRepositorio.save(ing);
			}else {
				estado="libre";
				ing.setEstado(estado);
				ing.setPacientes(null);
				citasRepositorio.save(ing);
			}
			
			
			return new ResponseEntity<Citas>(ing, HttpStatus.OK);
		} else {
			System.out.println(id);
			return new ResponseEntity<Citas>(ing, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PostMapping("elegir/{id}")
	public ResponseEntity<?> elegirTarifa(@PathVariable String id, @RequestParam String tf) {
		
		Pacientes paciente= pacientesRepositorio.findById(id);
		if (paciente != null) {
			paciente.setTarifas(tarifasRepositorio.findByTipo(tf));
			pacientesRepositorio.save(paciente);
			return new ResponseEntity<Pacientes>(paciente, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Cita no encontrada"));

		}
	}
	

}
