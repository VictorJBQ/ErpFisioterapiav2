package proy.MoisVictorv1.ErpFisioterapiav1.Form.citas;

import jakarta.validation.constraints.NotEmpty;

public class CitasFormSinE {

	@NotEmpty(message = "No puede estar vacío")
	private String fechaE;
	@NotEmpty(message = "No puede estar vacío")
	private String idE;
	@NotEmpty(message = "No puede estar vacío")
	private String horaE;
	@NotEmpty(message = "No puede estar vacío")
	private String tipoE;
	public String getFechaE() {
		return fechaE;
	}
	public void setFechaE(String fechaE) {
		this.fechaE = fechaE;
	}
	public String getIdE() {
		return idE;
	}
	public void setIdE(String idE) {
		this.idE = idE;
	}
	public String getHoraE() {
		return horaE;
	}
	public void setHoraE(String horaE) {
		this.horaE = horaE;
	}
	public String getTipoE() {
		return tipoE;
	}
	public void setTipoE(String tipoE) {
		this.tipoE = tipoE;
	}
	public CitasFormSinE(@NotEmpty(message = "No puede estar vacío") String fechaE,
			@NotEmpty(message = "No puede estar vacío") String idE,
			@NotEmpty(message = "No puede estar vacío") String horaE,
			@NotEmpty(message = "No puede estar vacío") String tipoE) {
		super();
		this.fechaE = fechaE;
		this.idE = idE;
		this.horaE = horaE;
		this.tipoE = tipoE;
	}
	
	
	
	
	
}
