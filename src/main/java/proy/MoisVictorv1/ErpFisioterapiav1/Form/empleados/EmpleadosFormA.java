package proy.MoisVictorv1.ErpFisioterapiav1.Form.empleados;

import jakarta.validation.constraints.NotEmpty;


public class EmpleadosFormA {

	@NotEmpty(message = "No puede estar vacío")
	private String identificadorA;
	
	@NotEmpty(message = "No puede estar vacío")
	private String nombreA;
	
	@NotEmpty(message = "No puede estar vacío")
	private String passwordA;
	
	@NotEmpty(message = "No puede estar vacío")
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
