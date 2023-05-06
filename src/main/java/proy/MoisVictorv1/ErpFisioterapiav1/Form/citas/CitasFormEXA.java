package proy.MoisVictorv1.ErpFisioterapiav1.Form.citas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class CitasFormEXA {

	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String fechaA;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String horaA;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String tipoA;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String idPacienteA;
	
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String estadoCONA;
	
	
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
	public String getIdPacienteA() {
		return idPacienteA;
	}
	public void setIdPacienteA(String idPacienteA) {
		this.idPacienteA = idPacienteA;
	}
	public String getEstadoCONA() {
		return estadoCONA;
	}
	public void setEstadoCONA(String estadoCONA) {
		this.estadoCONA = estadoCONA;
	}
	public CitasFormEXA(@NotEmpty(message = "No puede estar vacío") String fechaA,
			@NotEmpty(message = "No puede estar vacío") String horaA,
			@NotEmpty(message = "No puede estar vacío") String tipoA,
			@NotEmpty(message = "No puede estar vacío") String idPacienteA,
			@NotEmpty(message = "No puede estar vacío") String estadoCONA) {
		super();
		this.fechaA = fechaA;
		this.horaA = horaA;
		this.tipoA = tipoA;
		this.idPacienteA = idPacienteA;
		this.estadoCONA = estadoCONA;
	}

	
	
	
}
