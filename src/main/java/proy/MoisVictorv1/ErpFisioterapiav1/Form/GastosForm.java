package proy.MoisVictorv1.ErpFisioterapiav1.Form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class GastosForm {
	@NotNull
	private LocalDate fecha;
	@NotNull
	private String importe;
	@NotNull
	private String tipo;
	@NotNull
	private String idEmpleado;
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
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
	
	
}
