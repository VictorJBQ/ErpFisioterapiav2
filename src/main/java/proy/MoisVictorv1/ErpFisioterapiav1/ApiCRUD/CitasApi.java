package proy.MoisVictorv1.ErpFisioterapiav1.ApiCRUD;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.citas.CitasFormEXA;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.citas.CitasFormEXE;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.citas.CitasFormNVA;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.citas.CitasFormNVE;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.citas.CitasFormSinA;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.citas.CitasFormSinE;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.empleados.EmpleadosFormA;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Empleados;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Facturas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Ingresos;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Pacientes;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.EmpleadosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.FacturasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.IngresosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Utilidades.ResponseMessage;

@RestController
@RequestMapping(value = "/api-ct/") // esto es para dar la RUtA al controlador como de origen y lo de abajo se le
// añade,
@CrossOrigin("*")
public class CitasApi {

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

	@GetMapping("all")
	public List<Citas> getAllemple() {
		return (List<Citas>) citasRepositorio.findAll();

	}

	@PostMapping(value = "addSIN")
	@ResponseBody
	public ResponseEntity<?> crearSIN(@RequestBody @Valid CitasFormSinA e, BindingResult result) {
		if (result.hasErrors()) {
// Si hay errores de validación, se envía un objeto con los errores y un código
// de estado HTTP 400 (Bad Request)
			Map<String, String> errores = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errores.put(error.getField(), error.getDefaultMessage());

			}

			return ResponseEntity.badRequest().body(errores);
		} else {
			LocalTime hora = LocalTime.parse(e.getHoraA());

			List<Citas> op = citasRepositorio.findByFechaAndHora(LocalDate.parse(e.getFechaA()),
					LocalTime.parse(e.getHoraA()));
			List<Citas> intervalo = citasRepositorio.findCitasByFechaAndHoraBetweenTwoRanges(
					LocalDate.parse(e.getFechaA()), hora, hora.plusMinutes(59), hora.minusMinutes(59), hora);

			if (op.size() > 0) {

				Map<String, String> errores2 = new HashMap<>();

				errores2.put("fechaA", "Este día y hora ya está registrado");

				return ResponseEntity.badRequest().body(errores2);
			} else if (LocalDate.parse(e.getFechaA()).equals(LocalDate.now())
					&& LocalTime.parse(e.getHoraA()).isBefore(LocalTime.now())) {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("horaA", "La hora no puede ser anterior a la actual para citas en el día de hoy");

				return ResponseEntity.badRequest().body(errores2);
			} else if (LocalDate.parse(e.getFechaA()).isBefore(LocalDate.now())) {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("fechaA", "La fecha no puede ser anterior a la actual");
				return ResponseEntity.badRequest().body(errores2);
			} else if (intervalo.size() > 0) {
				Map<String, String> errores2 = new HashMap<>();
				errores2.put("horaA",
						"En ese intervalo de hora: " + hora + "-" + hora.plusMinutes(59) + " o " + hora.minusMinutes(59)
								+ "-" + hora + " ya tienes una cita asignada"
								+ " recuerda el intervalo de citas es de 1 hora entre citas");
				return ResponseEntity.badRequest().body(errores2);
			}

			Citas citas = new Citas(LocalDate.parse(e.getFechaA()), LocalTime.parse(e.getHoraA()), "libre",
					e.getTipoA(), empleadosRepositorio.findByidentificador("T01"));
			citasRepositorio.save(citas);
			return ResponseEntity.ok(e);
		}
	}

	@PostMapping(value = "addNUE")
	@ResponseBody
	public ResponseEntity<?> crearNUE(@RequestBody @Valid CitasFormNVA e, BindingResult result) {
		if (result.hasErrors()) {
// Si hay errores de validación, se envía un objeto con los errores y un código
// de estado HTTP 400 (Bad Request)
			Map<String, String> errores = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errores.put(error.getField(), error.getDefaultMessage());

			}

			return ResponseEntity.badRequest().body(errores);
		} else {
			if ((LocalDate.parse(e.getFechaA()).isBefore(LocalDate.now())
					|| (LocalDate.parse(e.getFechaA()).equals(LocalDate.now())
							&& LocalTime.parse(e.getHoraA()).isBefore(LocalTime.now())))
					&& !e.getEstadoNUA().equals("confirmada")) {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("fechaA",
						"Una cita, anterior a día y hora actual para pacientes nuevos, solo puede estar en estado confirmada");
				return ResponseEntity.badRequest().body(errores2);
			}
			List<Citas> op = citasRepositorio.findByFechaAndHora(LocalDate.parse(e.getFechaA()),
					LocalTime.parse(e.getHoraA()));
			LocalTime hora = LocalTime.parse(e.getHoraA());

			List<Citas> intervalo = citasRepositorio.findCitasByFechaAndHoraBetweenTwoRanges(
					LocalDate.parse(e.getFechaA()), hora, hora.plusMinutes(59), hora.minusMinutes(59), hora);

			if (op.size() > 0) {

				Map<String, String> errores2 = new HashMap<>();

				errores2.put("fechaA", "Este día y hora ya está registrado");

				return ResponseEntity.badRequest().body(errores2);
			} else if (LocalDate.parse(e.getFechaA()).equals(LocalDate.now())
					&& LocalTime.parse(e.getHoraA()).isBefore(LocalTime.now())) {

			} else if (LocalDate.parse(e.getFechaA()).isBefore(LocalDate.now())) {

			} else if (intervalo.size() > 0) {
				Map<String, String> errores2 = new HashMap<>();
				errores2.put("horaA",
						"En ese intervalo de hora: " + hora + "-" + hora.plusMinutes(59) + " o " + hora.minusMinutes(59)
								+ "-" + hora + " ya tienes una cita asignada"
								+ " recuerda el intervalo de citas es de 1 hora entre citas");
				return ResponseEntity.badRequest().body(errores2);
			}
			Pacientes paciente = new Pacientes(e.getNombreA(), Integer.parseInt(e.getTelefonoA()));

			pacientesRepositorio.save(paciente);
			Citas citas = new Citas(LocalDate.parse(e.getFechaA()), LocalTime.parse(e.getHoraA()), e.getEstadoNUA(),
					e.getTipoA(), paciente, empleadosRepositorio.findByidentificador("T01"));
			citasRepositorio.save(citas);
			return ResponseEntity.ok(e);
		}
	}

	@PostMapping(value = "addEX")
	@ResponseBody
	public ResponseEntity<?> crearEX(@RequestBody @Valid CitasFormEXA e, BindingResult result) {
		if (result.hasErrors()) {
// Si hay errores de validación, se envía un objeto con los errores y un código
// de estado HTTP 400 (Bad Request)
			Map<String, String> errores = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errores.put(error.getField(), error.getDefaultMessage());

			}

			return ResponseEntity.badRequest().body(errores);
		} else {
			if ((e.getEstadoCONA().contains("terminada") || e.getEstadoCONA().equals("cancelada"))
					&& (LocalDate.parse(e.getFechaA()).isAfter(LocalDate.now())
							|| (LocalDate.parse(e.getFechaA()).equals(LocalDate.now())
									&& LocalTime.parse(e.getHoraA()).isAfter(LocalTime.now())))) {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("fechaA", "No puedes terminar o cancelar, una cita que que aún no se ha producido");
				errores2.put("estadoCONA", "Revisa el estado");
				return ResponseEntity.badRequest().body(errores2);

			}
			List<Citas> op = citasRepositorio.findByFechaAndHora(LocalDate.parse(e.getFechaA()),
					LocalTime.parse(e.getHoraA()));
			LocalTime hora = LocalTime.parse(e.getHoraA());

			List<Citas> intervalo = citasRepositorio.findCitasByFechaAndHoraBetweenTwoRanges(
					LocalDate.parse(e.getFechaA()), hora, hora.plusMinutes(59), hora.minusMinutes(59), hora);

			if (op.size() > 0) {

				Map<String, String> errores2 = new HashMap<>();

				errores2.put("fechaA", "Este día y hora ya está registrado");

				return ResponseEntity.badRequest().body(errores2);
			} else if (LocalDate.parse(e.getFechaA()).equals(LocalDate.now())
					&& LocalTime.parse(e.getHoraA()).isBefore(LocalTime.now())
					&& !(e.getEstadoCONA().equals("terminadaBZ") || e.getEstadoCONA().equals("terminadaEF")
							|| e.getEstadoCONA().equals("cancelada"))) {
				Map<String, String> errores2 = new HashMap<>();
				errores2.put("horaA", "No puedes reservar una  cita anterior a la fecha y hora de hoy");

				return ResponseEntity.badRequest().body(errores2);
			} else if (LocalDate.parse(e.getFechaA()).isBefore(LocalDate.now())
					&& !(e.getEstadoCONA().equals("terminadaBZ") || e.getEstadoCONA().equals("terminadaEF")
							|| e.getEstadoCONA().equals("cancelada"))) {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("fechaA", "No puedes reservar  una cita anterior a la fecha y hora de hoy");
				return ResponseEntity.badRequest().body(errores2);
			} else if (intervalo.size() > 0) {
				Map<String, String> errores2 = new HashMap<>();
				errores2.put("horaA",
						"En ese intervalo de hora: " + hora + "-" + hora.plusMinutes(59) + " o " + hora.minusMinutes(59)
								+ "-" + hora + " ya tienes una cita asignada"
								+ " recuerda el intervalo de citas es de 1 hora entre citas");
				return ResponseEntity.badRequest().body(errores2);
			}

			Pacientes paciente = pacientesRepositorio.findById(e.getIdPacienteA());
			if (paciente.getTarifas() == null && e.getEstadoCONA().contains("terminada")) {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("fechaA",
						"Este paciente no tiene asociada una tarifa, por lo cual no puedes terminar la cita");
				return ResponseEntity.badRequest().body(errores2);
			} else if (paciente.getTarifas() != null && e.getEstadoCONA().contains("terminada")) {
				Citas citas = new Citas(LocalDate.parse(e.getFechaA()), LocalTime.parse(e.getHoraA()), "terminada",
						e.getTipoA(), paciente, empleadosRepositorio.findByidentificador("T01"));
				citasRepositorio.save(citas);

				String formaPago;
				if (e.getEstadoCONA().contains("EF")) {
					formaPago = "Efectivo,al contado";
				} else {
					formaPago = "Bizum";
				}

				Facturas factura = new Facturas(formaPago, citas.getPacientes().getTarifas().getPrecio(),
						citas.getFecha(), citas);
				facturasRepositorio.save(factura);

				Ingresos ingreso = new Ingresos(factura.getImporte(), factura, factura.getFecha());

				ingresosRepositorio.save(ingreso);
			} else {

				Citas citas = new Citas(LocalDate.parse(e.getFechaA()), LocalTime.parse(e.getHoraA()),
						e.getEstadoCONA(), e.getTipoA(), paciente, empleadosRepositorio.findByidentificador("T01"));
				citasRepositorio.save(citas);
			}

			return ResponseEntity.ok(e);

		}
	}

	/* editar */
	@PostMapping(value = "editarSIN")
	@ResponseBody
	public ResponseEntity<?> editarSIN(@RequestBody @Valid CitasFormSinE e, BindingResult result) {
		if (result.hasErrors()) {
// Si hay errores de validación, se envía un objeto con los errores y un código
// de estado HTTP 400 (Bad Request)
			Map<String, String> errores = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errores.put(error.getField(), error.getDefaultMessage());

			}

			return ResponseEntity.badRequest().body(errores);
		} else {

			Citas op = citasRepositorio.findById(e.getIdE());

			if (!op.getFecha().equals(LocalDate.parse(e.getFechaE()))
					&& !op.getHora().equals(LocalTime.parse(e.getHoraE()))) {

				List<Citas> op2 = citasRepositorio.findByFechaAndHora(LocalDate.parse(e.getFechaE()),
						LocalTime.parse(e.getHoraE()));
				LocalTime hora = LocalTime.parse(e.getHoraE());

				List<Citas> intervalo = citasRepositorio.findCitasByFechaAndHoraBetweenTwoRanges(
						LocalDate.parse(e.getFechaE()), hora, hora.plusMinutes(59), hora.minusMinutes(59), hora);

				if (op2.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();

					errores2.put("fechaE", "Este día y hora ya está registrado");
					return ResponseEntity.badRequest().body(errores2);
				} else if (intervalo.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();
					errores2.put("horaE",
							"En ese intervalo de hora: " + hora + "-" + hora.plusMinutes(59) + " o "
									+ hora.minusMinutes(59) + "-" + hora + " ya tienes una cita asignada"
									+ " recuerda el intervalo de citas es de 1 hora entre citas");
					return ResponseEntity.badRequest().body(errores2);
				}
			}

			if (op.getFecha().equals(LocalDate.parse(e.getFechaE()))
					&& op.getHora().equals(LocalTime.parse(e.getHoraE()))) {
				op.setTipo(e.getTipoE());
			} else if (LocalDate.parse(e.getFechaE()).equals(LocalDate.now())
					&& LocalTime.parse(e.getHoraE()).isAfter(LocalTime.now())) {
				List<Citas> op2 = citasRepositorio.findByFechaAndHora(LocalDate.parse(e.getFechaE()),
						LocalTime.parse(e.getHoraE()));
				LocalTime hora = LocalTime.parse(e.getHoraE());

				List<Citas> intervalo = citasRepositorio.findCitasByFechaAndHoraBetweenTwoRanges(
						LocalDate.parse(e.getFechaE()), hora, hora.plusMinutes(59), hora.minusMinutes(59), hora);

				if (op2.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();

					errores2.put("fechaE", "Este día y hora ya está registrado");
					return ResponseEntity.badRequest().body(errores2);
				} else if (intervalo.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();
					errores2.put("horaE",
							"En ese intervalo de hora: " + hora + "-" + hora.plusMinutes(59) + " o "
									+ hora.minusMinutes(59) + "-" + hora + " ya tienes una cita asignada"
									+ " recuerda el intervalo de citas es de 1 hora entre citas");
					return ResponseEntity.badRequest().body(errores2);
				}

				op.setFecha(LocalDate.parse(e.getFechaE()));
				op.setHora(LocalTime.parse(e.getHoraE()));
				op.setTipo(e.getTipoE());
			} else if (LocalDate.parse(e.getFechaE()).isAfter(LocalDate.now())) {
				List<Citas> op2 = citasRepositorio.findByFechaAndHora(LocalDate.parse(e.getFechaE()),
						LocalTime.parse(e.getHoraE()));
				LocalTime hora = LocalTime.parse(e.getHoraE());

				List<Citas> intervalo = citasRepositorio.findCitasByFechaAndHoraBetweenTwoRanges(
						LocalDate.parse(e.getFechaE()), hora, hora.plusMinutes(59), hora.minusMinutes(59), hora);
				if (op2.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();

					errores2.put("fechaE", "Este día y hora ya está registrado");
					return ResponseEntity.badRequest().body(errores2);
				} else if (intervalo.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();
					errores2.put("horaE",
							"En ese intervalo de hora: " + hora + "-" + hora.plusMinutes(59) + " o "
									+ hora.minusMinutes(59) + "-" + hora + " ya tienes una cita asignada"
									+ " recuerda el intervalo de citas es de 1 hora entre citas");
					return ResponseEntity.badRequest().body(errores2);
				}
				op.setFecha(LocalDate.parse(e.getFechaE()));
				op.setHora(LocalTime.parse(e.getHoraE()));
				op.setTipo(e.getTipoE());
			} else {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("fechaE", "Fecha no válida, no puedes poner una fecha anterior al día de hoy");
				return ResponseEntity.badRequest().body(errores2);
			}

			citasRepositorio.save(op);
			return ResponseEntity.ok(e);
		}
	}

	@PostMapping(value = "editarNV")
	@ResponseBody
	public ResponseEntity<?> editarNUEVO(@RequestBody @Valid CitasFormNVE e, BindingResult result) {
		if (result.hasErrors()) {
// Si hay errores de validación, se envía un objeto con los errores y un código
// de estado HTTP 400 (Bad Request)
			Map<String, String> errores = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errores.put(error.getField(), error.getDefaultMessage());

			}

			return ResponseEntity.badRequest().body(errores);
		} else {
			if ((LocalDate.parse(e.getFechaE()).isBefore(LocalDate.now())
					|| (LocalDate.parse(e.getFechaE()).equals(LocalDate.now())
							&& LocalTime.parse(e.getHoraE()).isBefore(LocalTime.now())))
					&& !e.getEstadoE().equals("confirmada")) {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("fechaE",
						"Una cita, anterior a día y hora actual para pacientes nuevos, solo puede estar en estado confirmada");
				return ResponseEntity.badRequest().body(errores2);
			}

			Citas op = citasRepositorio.findById(e.getIdE());

			if (!op.getFecha().equals(LocalDate.parse(e.getFechaE()))
					&& !op.getHora().equals(LocalTime.parse(e.getHoraE()))) {
				List<Citas> op2 = citasRepositorio.findByFechaAndHora(LocalDate.parse(e.getFechaE()),
						LocalTime.parse(e.getHoraE()));
				LocalTime hora = LocalTime.parse(e.getHoraE());

				List<Citas> intervalo = citasRepositorio.findCitasByFechaAndHoraBetweenTwoRanges(
						LocalDate.parse(e.getFechaE()), hora, hora.plusMinutes(59), hora.minusMinutes(59), hora);
				if (op2.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();

					errores2.put("fechaE", "Este día y hora ya está registrado");
					return ResponseEntity.badRequest().body(errores2);
				} else if (intervalo.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();
					errores2.put("horaE",
							"En ese intervalo de hora: " + hora + "-" + hora.plusMinutes(59) + " o "
									+ hora.minusMinutes(59) + "-" + hora + " ya tienes una cita asignada"
									+ " recuerda el intervalo de citas es de 1 hora entre citas");
					return ResponseEntity.badRequest().body(errores2);
				}
			}

			if (op.getFecha().equals(LocalDate.parse(e.getFechaE()))
					&& op.getHora().equals(LocalTime.parse(e.getHoraE()))) {
				Pacientes paciente = new Pacientes(e.getNombreE(), Integer.parseInt(e.getTelefonoE()));
				pacientesRepositorio.save(paciente);

				op.setFecha(LocalDate.parse(e.getFechaE()));
				op.setHora(LocalTime.parse(e.getHoraE()));
				op.setTipo(e.getTipoE());
				op.setEstado(e.getEstadoE());
				op.setPacientes(paciente);

				citasRepositorio.save(op);
			} else if (LocalDate.parse(e.getFechaE()).equals(LocalDate.now())
					&& LocalTime.parse(e.getHoraE()).isAfter(LocalTime.now())) {
				List<Citas> op2 = citasRepositorio.findByFechaAndHora(LocalDate.parse(e.getFechaE()),
						LocalTime.parse(e.getHoraE()));
				LocalTime hora = LocalTime.parse(e.getHoraE());

				List<Citas> intervalo = citasRepositorio.findCitasByFechaAndHoraBetweenTwoRanges(
						LocalDate.parse(e.getFechaE()), hora, hora.plusMinutes(59), hora.minusMinutes(59), hora);
				if (op2.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();

					errores2.put("fechaE", "Este día y hora ya está registrado");
					return ResponseEntity.badRequest().body(errores2);
				} else if (intervalo.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();
					errores2.put("horaE",
							"En ese intervalo de hora: " + hora + "-" + hora.plusMinutes(59) + " o "
									+ hora.minusMinutes(59) + "-" + hora + " ya tienes una cita asignada"
									+ " recuerda el intervalo de citas es de 1 hora entre citas");
					return ResponseEntity.badRequest().body(errores2);
				}
				Pacientes paciente = new Pacientes(e.getNombreE(), Integer.parseInt(e.getTelefonoE()));
				pacientesRepositorio.save(paciente);

				op.setFecha(LocalDate.parse(e.getFechaE()));
				op.setHora(LocalTime.parse(e.getHoraE()));
				op.setTipo(e.getTipoE());
				op.setEstado(e.getEstadoE());
				op.setPacientes(paciente);

				citasRepositorio.save(op);
			} else if (LocalDate.parse(e.getFechaE()).isAfter(LocalDate.now())) {
				List<Citas> op2 = citasRepositorio.findByFechaAndHora(LocalDate.parse(e.getFechaE()),
						LocalTime.parse(e.getHoraE()));
				LocalTime hora = LocalTime.parse(e.getHoraE());

				List<Citas> intervalo = citasRepositorio.findCitasByFechaAndHoraBetweenTwoRanges(
						LocalDate.parse(e.getFechaE()), hora, hora.plusMinutes(59), hora.minusMinutes(59), hora);
				if (op2.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();

					errores2.put("fechaE", "Este día y hora ya está registrado");
					return ResponseEntity.badRequest().body(errores2);
				} else if (intervalo.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();
					errores2.put("horaE",
							"En ese intervalo de hora: " + hora + "-" + hora.plusMinutes(59) + " o "
									+ hora.minusMinutes(59) + "-" + hora + " ya tienes una cita asignada"
									+ " recuerda el intervalo de citas es de 1 hora entre citas");
					return ResponseEntity.badRequest().body(errores2);
				}
				Pacientes paciente = new Pacientes(e.getNombreE(), Integer.parseInt(e.getTelefonoE()));
				pacientesRepositorio.save(paciente);

				op.setFecha(LocalDate.parse(e.getFechaE()));
				op.setHora(LocalTime.parse(e.getHoraE()));
				op.setTipo(e.getTipoE());
				op.setEstado(e.getEstadoE());
				op.setPacientes(paciente);

				citasRepositorio.save(op);
			} else {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("fechaE", "Fecha no válida, no puedes poner una fecha anterior al día de hoy");
				return ResponseEntity.badRequest().body(errores2);
			}

			return ResponseEntity.ok(e);
		}
	}

	@PostMapping(value = "editarEX")
	@ResponseBody
	public ResponseEntity<?> editarEX(@RequestBody @Valid CitasFormEXE e, BindingResult result) {
		if (result.hasErrors()) {
// Si hay errores de validación, se envía un objeto con los errores y un código
// de estado HTTP 400 (Bad Request)
			Map<String, String> errores = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errores.put(error.getField(), error.getDefaultMessage());

			}

			return ResponseEntity.badRequest().body(errores);
		} else {
			Citas op = citasRepositorio.findById(e.getIdE());
			String estado = "";
			String formaPago = "";

			if (!e.getIdPacienteE().equals("nulo") && e.getEstadoE().equals("libre")) {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("estadoEE", "No puedes adjudicar una cita y dejar su estado en libre");
				return ResponseEntity.badRequest().body(errores2);
			}

			if (e.getEstadoE().contains("terminada") && !e.getIdPacienteE().equals("nulo")) {
				if (LocalDate.parse(e.getFechaE()).isAfter(LocalDate.now())) {
					Map<String, String> errores2 = new HashMap<>();

					errores2.put("fechaE", "No puedes terminar una cita que aún no ha sucedido");
					return ResponseEntity.badRequest().body(errores2);
				} else if (LocalDate.parse(e.getFechaE()).equals(LocalDate.now())
						&& LocalTime.parse(e.getHoraE()).isAfter(LocalTime.now())) {
					Map<String, String> errores2 = new HashMap<>();

					errores2.put("horaE", "No puedes terminar una cita que aún no ha sucedido");
					return ResponseEntity.badRequest().body(errores2);
				}

				if (op.getEstado().equals("cancelada") || op.getEstado().equals("salvada-pendiente")) {

					estado = "salvada";
				} else {
					estado = "terminada";
				}
				if (e.getEstadoE().contains("EF")) {
					formaPago = "Efectivo,al contado";
				} else {
					formaPago = "Bizum";
				}

			} else if ((e.getEstadoE().equals("terminadaEF") && e.getIdPacienteE().equals("nulo"))
					|| (e.getEstadoE().equals("terminadaBZ") && e.getIdPacienteE().equals("nulo"))) {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("fechaE", "No puedes terminar una cita sin paciente");

				return ResponseEntity.badRequest().body(errores2);
			} else if ((e.getEstadoE().equals("confirmada") || e.getEstadoE().equals("pendiente-Confirmar"))
					&& !e.getIdPacienteE().equals("nulo")) {

				if (op.getEstado().equals("cancelada") || op.getEstado().equals("salvada-pendiente")) {
					estado = "salvada-pendiente";
				} else if (e.getEstadoE().equals("pendiente-Confirmar")) {
					estado = "pendiente-Confirmar";
				} else {
					estado = "confirmada";
				}

			} else if ((e.getEstadoE().equals("pendiente-Confirmar") || e.getEstadoE().equals("confirmada")
					|| e.getEstadoE().equals("reservada") || e.getEstadoE().equals("cancelada"))
					&& e.getIdPacienteE().equals("nulo")) {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("estadoEE", "No puedes usar ese estado sin paciente");
				return ResponseEntity.badRequest().body(errores2);
			} else if (e.getEstadoE().equals("cancelada")) {
				LocalDate fecha = LocalDate.now().plusDays(1);
				if (LocalDate.parse(e.getFechaE()).isBefore(fecha) || e.getFechaE().equals(fecha)) {
					estado = "cancelada";

				} else {
					estado = "libre";

				}

			} else {
				estado = e.getEstadoE();
			}

			if (!op.getFecha().equals(LocalDate.parse(e.getFechaE()))
					&& !op.getHora().equals(LocalTime.parse(e.getHoraE()))) {
				System.out.println(op.getFecha() + " " + LocalDate.parse(e.getFechaE()) + " " + op.getHora() + " "
						+ LocalTime.parse(e.getHoraE()));
				List<Citas> op2 = citasRepositorio.findByFechaAndHora(LocalDate.parse(e.getFechaE()),
						LocalTime.parse(e.getHoraE()));
				LocalTime hora = LocalTime.parse(e.getHoraE());

				List<Citas> intervalo = citasRepositorio.findCitasByFechaAndHoraBetweenTwoRanges(
						LocalDate.parse(e.getFechaE()), hora, hora.plusMinutes(59), hora.minusMinutes(59), hora);

				if (op2.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();

					errores2.put("fechaE", "Este día y hora ya está registrado");
					return ResponseEntity.badRequest().body(errores2);
				} else if (intervalo.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();
					errores2.put("horaE",
							"En ese intervalo de hora: " + hora + "-" + hora.plusMinutes(59) + " o "
									+ hora.minusMinutes(59) + "-" + hora + " ya tienes una cita asignada"
									+ " recuerda el intervalo de citas es de 1 hora entre citas");
					return ResponseEntity.badRequest().body(errores2);
				}
			}

			if (op.getFecha().equals(LocalDate.parse(e.getFechaE()))
					&& op.getHora().equals(LocalTime.parse(e.getHoraE()))) {
				if (LocalTime.parse(e.getHoraE()).isAfter(LocalTime.now()) && e.getEstadoE().contains("termina")) {
					Map<String, String> errores2 = new HashMap<>();

					errores2.put("fechaE", "no puedes terminar una cita antes de tiempo");
				} else {
					if (e.getIdPacienteE().equals("nulo") || estado.equals("libre")) {
						op.setFecha(LocalDate.parse(e.getFechaE()));
						op.setHora(LocalTime.parse(e.getHoraE()));
						op.setTipo(e.getTipoE());
						op.setEstado(estado);
						op.setPacientes(null);
					} else {
						Pacientes paciente = pacientesRepositorio.findById(e.getIdPacienteE());
						op.setFecha(LocalDate.parse(e.getFechaE()));
						op.setHora(LocalTime.parse(e.getHoraE()));
						op.setTipo(e.getTipoE());
						op.setEstado(estado);
						op.setPacientes(paciente);
					}
				}

				if (e.getIdPacienteE().equals("nulo") || estado.equals("libre")) {
					op.setFecha(LocalDate.parse(e.getFechaE()));
					op.setHora(LocalTime.parse(e.getHoraE()));
					op.setTipo(e.getTipoE());
					op.setEstado(estado);
					op.setPacientes(null);
				} else {
					Pacientes paciente = pacientesRepositorio.findById(e.getIdPacienteE());
					op.setFecha(LocalDate.parse(e.getFechaE()));
					op.setHora(LocalTime.parse(e.getHoraE()));
					op.setTipo(e.getTipoE());
					op.setEstado(estado);
					op.setPacientes(paciente);
				}
			} else if (LocalDate.parse(e.getFechaE()).equals(LocalDate.now())
					&& LocalTime.parse(e.getHoraE()).isAfter(LocalTime.now())) {
				List<Citas> op2 = citasRepositorio.findByFechaAndHora(LocalDate.parse(e.getFechaE()),
						LocalTime.parse(e.getHoraE()));
				LocalTime hora = LocalTime.parse(e.getHoraE());

				List<Citas> intervalo = citasRepositorio.findCitasByFechaAndHoraBetweenTwoRanges(
						LocalDate.parse(e.getFechaE()), hora, hora.plusMinutes(59), hora.minusMinutes(59), hora);
				if (op2.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();

					errores2.put("fechaE", "Este día y hora ya está registrado ");
					return ResponseEntity.badRequest().body(errores2);
				} else if (intervalo.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();
					errores2.put("horaE",
							"En ese intervalo de hora: " + hora + "-" + hora.plusMinutes(59) + " o "
									+ hora.minusMinutes(59) + "-" + hora + " ya tienes una cita asignada"
									+ " recuerda el intervalo de citas es de 1 hora entre citas");
					return ResponseEntity.badRequest().body(errores2);
				}

				if (e.getIdPacienteE().equals("nulo") || estado.equals("libre")) {
					op.setFecha(LocalDate.parse(e.getFechaE()));
					op.setHora(LocalTime.parse(e.getHoraE()));
					op.setTipo(e.getTipoE());
					op.setEstado(estado);
					op.setPacientes(null);
				} else {
					Pacientes paciente = pacientesRepositorio.findById(e.getIdPacienteE());
					op.setFecha(LocalDate.parse(e.getFechaE()));
					op.setHora(LocalTime.parse(e.getHoraE()));
					op.setTipo(e.getTipoE());
					op.setEstado(estado);
					op.setPacientes(paciente);
				}
			} else if (LocalDate.parse(e.getFechaE()).isAfter(LocalDate.now())) {
				List<Citas> op2 = citasRepositorio.findByFechaAndHora(LocalDate.parse(e.getFechaE()),
						LocalTime.parse(e.getHoraE()));
				LocalTime hora = LocalTime.parse(e.getHoraE());

				List<Citas> intervalo = citasRepositorio.findCitasByFechaAndHoraBetweenTwoRanges(
						LocalDate.parse(e.getFechaE()), hora, hora.plusMinutes(59), hora.minusMinutes(59), hora);
				if (op2.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();

					errores2.put("fechaE", "Este día y hora ya está registrado");
					return ResponseEntity.badRequest().body(errores2);
				} else if (intervalo.size() > 0) {
					Map<String, String> errores2 = new HashMap<>();
					errores2.put("horaE",
							"En ese intervalo de hora: " + hora + "-" + hora.plusMinutes(59) + " o "
									+ hora.minusMinutes(59) + "-" + hora + " ya tienes una cita asignada"
									+ " recuerda el intervalo de citas es de 1 hora entre citas");
					return ResponseEntity.badRequest().body(errores2);
				}

				if (e.getIdPacienteE().equals("nulo") || estado.equals("libre")) {
					op.setFecha(LocalDate.parse(e.getFechaE()));
					op.setHora(LocalTime.parse(e.getHoraE()));
					op.setTipo(e.getTipoE());
					op.setEstado(estado);
					op.setPacientes(null);
				} else {
					Pacientes paciente = pacientesRepositorio.findById(e.getIdPacienteE());
					op.setFecha(LocalDate.parse(e.getFechaE()));
					op.setHora(LocalTime.parse(e.getHoraE()));
					op.setTipo(e.getTipoE());
					op.setEstado(estado);
					op.setPacientes(paciente);
				}
			} else {
				Map<String, String> errores2 = new HashMap<>();

				errores2.put("fechaE",
						"Fecha no válida, no puedes modificar la fecha de la cita a una anterior, debe ser igual a la que ya tenía o superior al día de hoy ");
				return ResponseEntity.badRequest().body(errores2);
			}

			if (e.getEstadoE().contains("terminada")) {
				if (op.getPacientes().getTarifas() == null) {
					Map<String, String> errores2 = new HashMap<>();

					errores2.put("idPacienteE",
							"Este paciente no tiene asociada una tarifa aún, por favor asocila para poder terminar la cita");
					return ResponseEntity.badRequest().body(errores2);
				}
				citasRepositorio.save(op);
				Facturas factura = new Facturas(formaPago, op.getPacientes().getTarifas().getPrecio(), op.getFecha(),
						op);
				facturasRepositorio.save(factura);

				Ingresos ingreso = new Ingresos(factura.getImporte(), factura, factura.getFecha());

				ingresosRepositorio.save(ingreso);
			} else {
				citasRepositorio.save(op);
			}
			return ResponseEntity.ok(e);
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Citas> eliminarRegistro(@PathVariable String id) {
		System.out.println(id);
		Citas ing = citasRepositorio.findById(id);
		if (ing != null) {
			citasRepositorio.delete(ing);
			return new ResponseEntity<Citas>(ing, HttpStatus.OK);
		} else {
			return new ResponseEntity<Citas>(ing, HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("cargaAutomatica/{mes}")
	public ResponseEntity<?> cargaAutomaticasCita(@PathVariable String mes, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Empleados emp = empleadosRepositorio.findByidentificador("T01");
		int mesActual = LocalDate.now().getMonthValue();
		int year = LocalDate.now().getYear();
		int mesCi = Integer.parseInt(mes);
		int dia = 1;
		int diaActual = LocalDate.now().getDayOfMonth();
		if (mesCi < mesActual) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("No puedes cargar citas de meses anteriores al actual"));
		}

		List<String> monday = new ArrayList<String>();
		monday.addAll(Arrays.asList("10:00", "11:00", "12:00", "15:00", "16:00", "17:00", "18:00", "19:00"));
		List<String> tuesday = new ArrayList<String>();
		tuesday.addAll(Arrays.asList("09:00", "10:00", "11:00", "12:00", "15:00", "16:00"));
		List<String> wednesday = new ArrayList<String>();
		wednesday.addAll(Arrays.asList("10:00", "11:00", "12:00", "13:00", "16:00", "17:00", "18:00"));
		List<String> thursday = new ArrayList<String>();
		thursday.addAll(Arrays.asList("09:00", "10:00", "16:00", "17:00", "18:00", "19:00"));

		// Crear un objeto LocalDate con el año, mes y día especificados
		LocalDate date = LocalDate.of(year, mesCi, dia);

		// Recorrer todos los días del mes y realizar alguna acción para cada día
		for (int i = 1; i <= date.lengthOfMonth(); i++) {
			LocalDate current = LocalDate.of(year, mesCi, i);
			DayOfWeek dayOfWeek = current.getDayOfWeek();

			switch (dayOfWeek) {
			case MONDAY:
				if (mesActual == mesCi && diaActual > i) {

					break;
				} else {
					for (String hor : monday) {
						if (comprobaciones(current, LocalTime.parse(hor)) == false) {
							Citas cita = new Citas(current, LocalTime.parse(hor), "libre", "fija", emp);
							citasRepositorio.save(cita);
						}
					}

				}
				break;
			case TUESDAY:
				if (mesActual == mesCi && diaActual > i) {

					break;
				} else {
					for (String hor : tuesday) {
						if (comprobaciones(current, LocalTime.parse(hor)) == false) {
							Citas cita = new Citas(current, LocalTime.parse(hor), "libre", "fija", emp);
							citasRepositorio.save(cita);
						}
					}
				}

				break;
			case WEDNESDAY:
				if (mesActual == mesCi && diaActual > i) {
					break;
				} else {
					for (String hor : wednesday) {
						if (comprobaciones(current, LocalTime.parse(hor)) == false) {
							Citas cita = new Citas(current, LocalTime.parse(hor), "libre", "fija", emp);
							citasRepositorio.save(cita);
						}
					}
				}
				break;
			case THURSDAY:
				if (mesActual == mesCi && diaActual > i) {
					break;
				} else {
					for (String hor : thursday) {
						if (comprobaciones(current, LocalTime.parse(hor)) == false) {
							Citas cita = new Citas(current, LocalTime.parse(hor), "libre", "fija", emp);
							citasRepositorio.save(cita);
						}
					}
				}
				break;
			default:
				break;
			}
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Citas cargadas correctamente"));

	}

	public boolean comprobaciones(LocalDate fecha, LocalTime hora) {
		List<Citas> intervalo = citasRepositorio.findCitasByFechaAndHoraBetweenTwoRanges(fecha, hora,
				hora.plusMinutes(59), hora.minusMinutes(59), hora);

		List<Citas> op2 = citasRepositorio.findByFechaAndHora(fecha, hora);

		boolean pasada = fecha.equals(LocalDate.now()) && hora.isBefore(LocalTime.now()) ? true : false;

		if (intervalo.size() > 0 || op2.size() > 0 || pasada == true) {
			return true;
		}

		return false;

	}
}