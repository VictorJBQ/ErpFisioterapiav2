package proy.MoisVictorv1.ErpFisioterapiav1.Form.pacientes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PacientesFormA {

	@NotBlank(message = "No puede estar vacío")
	private String nombreA;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?!\\s)[\\p{L}\\s]{1,48}(?<!\\s)$", message = "Debe tener un máximo de 50 caracteres, no puede empezar ni terminar con un espacio en blanco")
	private String apellidosA;
	@NotEmpty(message = "No puede estar vacío")
	@Size(min = 9, max = 9, message = "El DNI/NIE debe tener 9 caracteres")
	private String dniA;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?!\\s)[\\p{L}0-9\\s]{1,48}(?<!\\s)$", message = "Debe tener un máximo de 50 caracteres, no puede empezar ni terminar con un espacio en blanco")
	private String domicilioA;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[0-9]+$", message = "Debe contener solo números y no puede tener espacios en blanco")
	@Size(min = 5, max = 5, message = "Debe tener una longitud de 5 caracteres")
	private String cpA;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio")
	private String poblaA;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[0-9]+$", message = "Debe contener solo números y no puede tener espacios en blanco")
	@Size(min = 9, max = 9, message = "Debe tener una longitud de 9 caracteres")
	private String telA; 
	@NotEmpty(message = "No puede estar vacío")
	private String conoceA;

	@NotEmpty(message = "No puede estar vacío")
	private String tarifaA;
	
	public String getNombreA() {
		return nombreA;
	}
	public void setNombreA(String nombreA) {
		this.nombreA = nombreA;
	}
	public String getApellidosA() {
		return apellidosA;
	}
	public void setApellidosA(String apellidosA) {
		this.apellidosA = apellidosA;
	}
	public String getDniA() {
		return dniA;
	}
	public void setDniA(String dniA) {
		this.dniA = dniA;
	}
	public String getDomicilioA() {
		return domicilioA;
	}
	public void setDomicilioA(String domicilioA) {
		this.domicilioA = domicilioA;
	}
	public String getCpA() {
		return cpA;
	}
	public void setCpA(String cpA) {
		this.cpA = cpA;
	}
	public String getPoblaA() {
		return poblaA;
	}
	public void setPoblaA(String poblaA) {
		this.poblaA = poblaA;
	}
	public String getTelA() {
		return telA;
	}
	public void setTelA(String telA) {
		this.telA = telA;
	}
	public String getConoceA() {
		return conoceA;
	}
	public void setConoceA(String conoceA) {
		this.conoceA = conoceA;
	}
	public String getTarifaA() {
		return tarifaA;
	}
	public void setTarifaA(String tarifaA) {
		this.tarifaA = tarifaA;
	}
	public PacientesFormA(@NotEmpty(message = "No puede estar vacío") String nombreA,
			@NotEmpty(message = "No puede estar vacío") String apellidosA,
			@NotEmpty(message = "No puede estar vacío") String dniA,
			@NotEmpty(message = "No puede estar vacío") String domicilioA,
			@NotEmpty(message = "No puede estar vacío") String cpA,
			@NotEmpty(message = "No puede estar vacío") String poblaA,
			@NotEmpty(message = "No puede estar vacío") String telA,
			@NotEmpty(message = "No puede estar vacío") String conoceA,
			@NotEmpty(message = "No puede estar vacío") String tarifaA) {
		super();
		this.nombreA = nombreA;
		this.apellidosA = apellidosA;
		this.dniA = dniA;
		this.domicilioA = domicilioA;
		this.cpA = cpA;
		this.poblaA = poblaA;
		this.telA = telA;
		this.conoceA = conoceA;
		this.tarifaA = tarifaA;
	}
	
	
}
