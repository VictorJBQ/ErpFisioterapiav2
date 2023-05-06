package proy.MoisVictorv1.ErpFisioterapiav1.Form.citas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CitasFormNVE {

	@NotEmpty(message = "No puede estar vacío")
	private String fechaE;
	@NotEmpty(message = "No puede estar vacío")
	private String idE;
	@NotEmpty(message = "No puede estar vacío")
	private String horaE;
	@NotEmpty(message = "No puede estar vacío")
	private String tipoE;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio")
	private String nombreE;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[0-9]+$", message = "Debe contener solo números y no puede tener espacios en blanco")
	@Size(min = 9, max = 9, message = "Debe tener una longitud de 9 caracteres")
	private String telefonoE;
	@NotEmpty(message = "No puede estar vacío")
	private String estadoE;
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
	public String getNombreE() {
		return nombreE;
	}
	public void setNombreE(String nombreE) {
		this.nombreE = nombreE;
	}
	public String getTelefonoE() {
		return telefonoE;
	}
	public void setTelefonoE(String telefonoE) {
		this.telefonoE = telefonoE;
	}
	public String getEstadoE() {
		return estadoE;
	}
	public void setEstadoE(String estadoE) {
		this.estadoE = estadoE;
	}
	public CitasFormNVE(@NotEmpty(message = "No puede estar vacío") String fechaE,
			@NotEmpty(message = "No puede estar vacío") String idE,
			@NotEmpty(message = "No puede estar vacío") String horaE,
			@NotEmpty(message = "No puede estar vacío") String tipoE,
			@NotEmpty(message = "No puede estar vacío") String nombreE,
			@NotEmpty(message = "No puede estar vacío") String telefonoE,
			@NotEmpty(message = "No puede estar vacío") String estadoE) {
		super();
		this.fechaE = fechaE;
		this.idE = idE;
		this.horaE = horaE;
		this.tipoE = tipoE;
		this.nombreE = nombreE;
		this.telefonoE = telefonoE;
		this.estadoE = estadoE;
	}
	
	
	
}
