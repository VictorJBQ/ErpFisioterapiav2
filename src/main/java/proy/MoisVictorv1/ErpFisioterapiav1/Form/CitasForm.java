package proy.MoisVictorv1.ErpFisioterapiav1.Form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class CitasForm {
	
	@NotNull
	private LocalDate fecha;
	@NotNull 
	private String hora;
	@NotNull
	private String tipo;
	@NotNull
	private String idEmpleado;
	@NotNull
	private String idPaciente;
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}
	
	
	
}
