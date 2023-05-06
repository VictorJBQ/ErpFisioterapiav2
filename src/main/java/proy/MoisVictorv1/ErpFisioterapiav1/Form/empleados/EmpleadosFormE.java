package proy.MoisVictorv1.ErpFisioterapiav1.Form.empleados;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class EmpleadosFormE {
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]{1,10}$", message = "No puede contener espacios en blanco y debe tener una longitud máxima de 10 caracteres")
	private String identificadorE;
	
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio")
	private String nombreE;
	
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String passwordE;
	
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String rolesE;

	public String getIdentificador() {
		return identificadorE;
	}

	public void setIdentificador(String identificadorE) {
		this.identificadorE = identificadorE;
	}

	public String getNombre() {
		return nombreE;
	}

	public void setNombre(String nombreA) {
		this.nombreE = nombreA;
	}

	public String getPassword() {
		return passwordE;
	}

	public void setPassword(String passwordE) {
		this.passwordE = passwordE;
	}

	public String getRoles() {
		return rolesE;
	}

	public void setRoles(String roles) {
		this.rolesE = roles;
	}

	public EmpleadosFormE(@NotEmpty(message = "El campo nombre no puede estar vacío") String identificador,
			@NotEmpty(message = "El campo nombre no puede estar vacío") String nombre,
			@NotEmpty(message = "El campo nombre no puede estar vacío") String password,
			@NotEmpty(message = "El campo nombre no puede estar vacío") String roles) {
		super();
		this.identificadorE = identificador;
		this.nombreE = nombre;
		this.passwordE = password;
		this.rolesE = roles;
	}
	
	
	
}
