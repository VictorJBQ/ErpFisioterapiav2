package proy.MoisVictorv1.ErpFisioterapiav1.Form.empleados;

import jakarta.validation.constraints.NotEmpty;

public class EmpleadosFormE {
	@NotEmpty(message = "No puede estar vacío")
	private String identificadorE;
	
	@NotEmpty(message = "No puede estar vacío")
	private String nombreE;
	
	@NotEmpty(message = "No puede estar vacío")
	private String passwordE;
	
	@NotEmpty(message = "No puede estar vacío")
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
