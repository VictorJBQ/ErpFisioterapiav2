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

	public EmpleadosFormA(@NotEmpty(message = "El campo nombre no puede estar vacío") String identificador,
			@NotEmpty(message = "El campo nombre no puede estar vacío") String nombre,
			@NotEmpty(message = "El campo nombre no puede estar vacío") String password,
			@NotEmpty(message = "El campo nombre no puede estar vacío") String roles) {
		super();
		this.identificadorA = identificador;
		this.nombreA = nombre;
		this.passwordA = password;
		this.rolesA = roles;
	}
	
	
	
}
