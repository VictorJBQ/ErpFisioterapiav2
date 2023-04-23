package proy.MoisVictorv1.ErpFisioterapiav1.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Citas {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private LocalDate fecha;
	private LocalTime hora;
	private String estado;
	private String tipo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_Paciente")
	private Pacientes pacientes;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_empleado")
	private Empleados empleados;
	
	 @OneToOne(mappedBy = "cita")
	    private Facturas factura;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Pacientes getPacientes() {
		return pacientes;
	}

	public void setPacientes(Pacientes pacientes) {
		this.pacientes = pacientes;
	}

	public Empleados getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Empleados empleados) {
		this.empleados = empleados;
	}

	public Facturas getFactura() {
		return factura;
	}

	public void setFactura(Facturas factura) {
		this.factura = factura;
	}

	public Citas(LocalDate fecha, LocalTime hora, String estado, String tipo, @NotNull Pacientes pacientes,
			@NotNull Empleados empleados) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.estado = estado;
		this.tipo = tipo;
		this.pacientes = pacientes;
		this.empleados = empleados;
	}

	public Citas(LocalDate fecha, LocalTime hora, String estado, String tipo, @NotNull Empleados empleados) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.estado = estado;
		this.tipo = tipo;
		this.empleados = empleados;
	}
	
	
	
}
