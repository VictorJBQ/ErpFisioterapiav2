package proy.MoisVictorv1.ErpFisioterapiav1.Form;

import jakarta.validation.constraints.NotNull;

public class EmpleadosForm {
	@NotNull
	private String id;
	@NotNull
	private String nombre;
	@NotNull
	private String password;
	@NotNull
	private String rol;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
