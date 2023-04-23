package proy.MoisVictorv1.ErpFisioterapiav1.Form;

import jakarta.validation.constraints.NotNull;

public class PacientesForm {
	
	private String id;
	private String dni;
	private String nombre;
	private String apellidos;
	private String domicilio;
	private String codigoPostal;
	@NotNull
	private int telefono;
	private String about;
	private String Tarifa;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getTarifa() {
		return Tarifa;
	}
	public void setTarifa(String tarifa) {
		Tarifa = tarifa;
	}
	
	
	
}
