package proy.MoisVictorv1.ErpFisioterapiav1.Form.pacientes;

import jakarta.validation.constraints.NotEmpty;

public class PacientesFormA {

	@NotEmpty(message = "No puede estar vacío")
	private String nombreA;
	@NotEmpty(message = "No puede estar vacío")
	private String apellidosA;
	@NotEmpty(message = "No puede estar vacío")
	private String dniA;
	@NotEmpty(message = "No puede estar vacío")
	private String domicilioA;
	@NotEmpty(message = "No puede estar vacío")
	private String cpA;
	@NotEmpty(message = "No puede estar vacío")
	private String poblaA;
	@NotEmpty(message = "No puede estar vacío")
	private String telA; 
	@NotEmpty(message = "No puede estar vacío")
	private String conoceA;

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
			String tarifaA) {
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
