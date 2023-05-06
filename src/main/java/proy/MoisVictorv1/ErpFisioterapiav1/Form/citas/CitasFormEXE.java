package proy.MoisVictorv1.ErpFisioterapiav1.Form.citas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class CitasFormEXE {

	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String fechaE;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String idE;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String horaE;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String tipoE;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String idPacienteE;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String estadoEE;
	
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
	public String getIdPacienteE() {
		return idPacienteE;
	}
	public void setIdPacienteE(String idPacienteE) {
		this.idPacienteE = idPacienteE;
	}
	public String getEstadoE() {
		return estadoEE;
	}
	public void setEstadoE(String estadoEE) {
		this.estadoEE = estadoEE;
	}
	
	
	public CitasFormEXE(@NotEmpty(message = "No puede estar vacío") String fechaE,
			@NotEmpty(message = "No puede estar vacío") String idE,
			@NotEmpty(message = "No puede estar vacío") String horaE,
			@NotEmpty(message = "No puede estar vacío") String tipoE,
			@NotEmpty(message = "No puede estar vacío") String idPacienteE,
			@NotEmpty(message = "No puede estar vacío") String estadoEE) {
		super();
		this.fechaE = fechaE;
		this.idE = idE;
		this.horaE = horaE;
		this.tipoE = tipoE;
		this.idPacienteE = idPacienteE;
		this.estadoEE = estadoEE;
	}
	
	
	
}
