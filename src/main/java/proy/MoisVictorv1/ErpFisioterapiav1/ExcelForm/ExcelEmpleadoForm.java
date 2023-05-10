package proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ExcelEmpleadoForm {
	private String Identificador;

	private String Nombre;
	private String Email;

	private String Rol;

	public String getIdentificador() {
		return Identificador;
	}

	public void setIdentificador(String identificador) {
		this.Identificador = identificador;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public String getRol() {
		return Rol;
	}

	public void setRol(String rol) {
		this.Rol = rol;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public ExcelEmpleadoForm(String identificador, String nombre,String email, String rol) {
		super();
		this.Identificador = identificador;
		this.Nombre = nombre;
		this.Email=email;
		this.Rol = rol;
	}
	
}
