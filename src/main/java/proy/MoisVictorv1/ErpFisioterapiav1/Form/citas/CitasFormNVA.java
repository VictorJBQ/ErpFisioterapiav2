package proy.MoisVictorv1.ErpFisioterapiav1.Form.citas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CitasFormNVA {

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
	@Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio")
	private String nombreA;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[0-9]+$", message = "Debe contener solo números y no puede tener espacios en blanco")
	@Size(min = 9, max = 9, message = "Debe tener una longitud de 9 caracteres")
	private String telefonoA;
	
	@NotEmpty(message = "No puede estar vacío")
	private String estadoNUA;
	
	public String getEstadoNUA() {
		return estadoNUA;
	}
	public void setEstadoNUA(String estadoNUA) {
		this.estadoNUA = estadoNUA;
	}
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
	public String getNombreA() {
		return nombreA;
	}
	public void setNombreA(String nombreA) {
		this.nombreA = nombreA;
	}
	public String getTelefonoA() {
		return telefonoA;
	}
	public void setTelefonoA(String telefonoA) {
		this.telefonoA = telefonoA;
	}
	public CitasFormNVA(@NotEmpty(message = "No puede estar vacío") String fechaA,
			@NotEmpty(message = "No puede estar vacío") String horaA,
			@NotEmpty(message = "No puede estar vacío") String tipoA,
			@NotEmpty(message = "No puede estar vacío") String nombreA,
			@NotEmpty(message = "No puede estar vacío") String telefonoA,
			@NotEmpty(message = "No puede estar vacío") String estadoNUA) {
		super();
		this.fechaA = fechaA;
		this.horaA = horaA;
		this.tipoA = tipoA;
		this.nombreA = nombreA;
		this.telefonoA = telefonoA;
		this.estadoNUA = estadoNUA;
	}

	
	
}
