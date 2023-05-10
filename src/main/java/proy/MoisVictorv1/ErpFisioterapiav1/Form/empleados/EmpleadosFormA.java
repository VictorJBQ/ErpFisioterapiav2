package proy.MoisVictorv1.ErpFisioterapiav1.Form.empleados;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;


public class EmpleadosFormA {

	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]{1,10}$", message = "No puede contener espacios en blanco y debe tener una longitud máxima de 10 caracteres")
	private String identificadorA;
	
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio")
	private String nombreA;
	
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String passwordA;
	
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String rolesA;
	
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String emailA;
	

	public String getEmailA() {
		return emailA;
	}

	public void setEmailA(String emailA) {
		this.emailA = emailA;
	}

	public String getIdentificador() {
		return identificadorA;
	}

	public void setIdentificador(String identificadorA) {
		this.identificadorA = identificadorA;
	}

	public String getNombre() {
		return nombreA;
	}

	public void setNombre(String nombreA) {
		this.nombreA = nombreA;
	}

	public String getPassword() {
		return passwordA;
	}

	public void setPassword(String passwordA) {
		this.passwordA = passwordA;
	}

	public String getRoles() {
		return rolesA;
	}

	public void setRoles(String roles) {
		this.rolesA = roles;
	}

	public EmpleadosFormA(
			@NotBlank(message = "No puede estar vacío") @Pattern(regexp = "^[^\\s]{1,10}$", message = "No puede contener espacios en blanco y debe tener una longitud máxima de 10 caracteres") String identificadorA,
			@NotBlank(message = "No puede estar vacío") @Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio") String nombreA,
			@NotBlank(message = "No puede estar vacío") @Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco") String passwordA,
			@NotBlank(message = "No puede estar vacío") @Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco") String rolesA,
			@NotBlank(message = "No puede estar vacío") @Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco") String emailA) {
		super();
		this.identificadorA = identificadorA;
		this.nombreA = nombreA;
		this.passwordA = passwordA;
		this.rolesA = rolesA;
		this.emailA = emailA;
	}

	
	
	
	
}
