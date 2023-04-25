package proy.MoisVictorv1.ErpFisioterapiav1.Model;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.EmpleadosRepositorio;

@Entity
public class Gastos {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	private LocalDate fecha;
	private String tipo;
	private Double importe;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_empleado")
	private Empleados empleados;

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Empleados getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Empleados empleados) {
		this.empleados = empleados;
	}

	public Gastos(int id, LocalDate fecha, String tipo, Double importe, @NotNull Empleados empleados) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tipo = tipo;
		this.importe = importe;
		this.empleados = empleados;
	}
	
	
	

	public Gastos(LocalDate fecha, String tipo, Double importe, @NotNull Empleados empleados) {
		super();
		this.fecha = fecha;
		this.tipo = tipo;
		this.importe = importe;
		this.empleados = empleados;
	}

	public Gastos() {
		super();
	}


	
	
	
}
