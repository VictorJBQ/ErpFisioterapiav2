package proy.MoisVictorv1.ErpFisioterapiav1.Form.pacientes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PacientesFormE {

	private String idE;
	
	@NotBlank(message = "No puede estar vacío")
	private String nombreE;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?!\\s)[\\p{L}\\s]{1,48}(?<!\\s)$", message = "Debe tener un máximo de 50 caracteres, no puede empezar ni terminar con un espacio en blanco")
	private String apellidosE;
	@NotEmpty(message = "No puede estar vacío")
	@Size(min = 9, max = 9, message = "El DNI/NIE debe tener 9 caracteres")
	private String dniE;
	private String dniViejoE;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?!\\s)[\\p{L}0-9\\s]{1,48}(?<!\\s)$", message = "Debe tener un máximo de 50 caracteres, no puede empezar ni terminar con un espacio en blanco")
	private String domicilioE;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[0-9]+$", message = "Debe contener solo números y no puede tener espacios en blanco")
	@Size(min = 5, max = 5, message = "Debe tener una longitud de 5 caracteres")
	private String cpE;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio")
	private String poblaE;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[0-9]+$", message = "Debe contener solo números y no puede tener espacios en blanco")
	@Size(min = 9, max = 9, message = "Debe tener una longitud de 9 caracteres")
	private String telE; 
	@NotEmpty(message = "No puede estar vacío")
	private String conoceE;
	@NotEmpty(message = "No puede estar vacío")
	private String tarifaE;
	public String getIdE() {
		return idE;
	}
	public void setIdE(String idE) {
		this.idE = idE;
	}
	public String getNombreE() {
		return nombreE;
	}
	public void setNombreE(String nombreE) {
		this.nombreE = nombreE;
	}
	public String getApellidosE() {
		return apellidosE;
	}
	public void setApellidosE(String apellidosE) {
		this.apellidosE = apellidosE;
	}
	public String getDniE() {
		return dniE;
	}
	public void setDniE(String dniE) {
		this.dniE = dniE;
	}
	public String getDomicilioE() {
		return domicilioE;
	}
	public void setDomicilioE(String domicilioE) {
		this.domicilioE = domicilioE;
	}
	public String getCpE() {
		return cpE;
	}
	public void setCpE(String cpE) {
		this.cpE = cpE;
	}
	public String getPoblaE() {
		return poblaE;
	}
	public void setPoblaE(String poblaE) {
		this.poblaE = poblaE;
	}
	public String getTelE() {
		return telE;
	}
	public void setTelE(String telE) {
		this.telE = telE;
	}
	public String getConoceE() {
		return conoceE;
	}
	public void setConoceE(String conoceE) {
		this.conoceE = conoceE;
	}
	public String getTarifaE() {
		return tarifaE;
	}
	public void setTarifaE(String tarifaE) {
		this.tarifaE = tarifaE;
	}
	public PacientesFormE(String idE, @NotEmpty(message = "No puede estar vacío") String nombreE,
			@NotEmpty(message = "No puede estar vacío") String apellidosE,
			@NotEmpty(message = "No puede estar vacío") String dniE, String dniViejoE,
			@NotEmpty(message = "No puede estar vacío") String domicilioE,
			@NotEmpty(message = "No puede estar vacío") String cpE,
			@NotEmpty(message = "No puede estar vacío") String poblaE,
			@NotEmpty(message = "No puede estar vacío") String telE,
			@NotEmpty(message = "No puede estar vacío") String conoceE,
			@NotEmpty(message = "No puede estar vacío") String tarifaE) {
		super();
		this.idE = idE;
		this.nombreE = nombreE;
		this.apellidosE = apellidosE;
		this.dniE = dniE;
		this.dniViejoE = dniViejoE;
		this.domicilioE = domicilioE;
		this.cpE = cpE;
		this.poblaE = poblaE;
		this.telE = telE;
		this.conoceE = conoceE;
		this.tarifaE = tarifaE;
	}
	public String getDniViejoE() {
		return dniViejoE;
	}
	public void setDniViejoE(String dniViejoE) {
		this.dniViejoE = dniViejoE;
	}
	
	
	
}
