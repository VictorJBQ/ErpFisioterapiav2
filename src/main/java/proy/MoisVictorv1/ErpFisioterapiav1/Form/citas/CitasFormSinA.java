package proy.MoisVictorv1.ErpFisioterapiav1.Form.citas;

import jakarta.validation.constraints.NotEmpty;

public class CitasFormSinA {

	@NotEmpty(message = "No puede estar vacío")
	private String fechaA;
	@NotEmpty(message = "No puede estar vacío")
	private String horaA;
	@NotEmpty(message = "No puede estar vacío")
	private String tipoA;
	public String getFechaA() {
		return fechaA;
	}
	public void setFechaA(String fechaA) {
		this.fechaA = fechaA;
	}
	public String getHoraA() {
		return horaA;
	}
	public void setHoraA(String horaA) {
		this.horaA = horaA;
	}
	public String getTipoA() {
		return tipoA;
	}
	public void setTipoA(String tipoA) {
		this.tipoA = tipoA;
	}
	public CitasFormSinA(@NotEmpty(message = "No puede estar vacío") String fechaA,
			@NotEmpty(message = "No puede estar vacío") String horaA,
			@NotEmpty(message = "No puede estar vacío") String tipoA) {
		super();
		this.fechaA = fechaA;
		this.horaA = horaA;
		this.tipoA = tipoA;
	}
	
	
}
