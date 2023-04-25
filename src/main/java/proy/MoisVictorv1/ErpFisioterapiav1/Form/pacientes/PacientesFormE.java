package proy.MoisVictorv1.ErpFisioterapiav1.Form.pacientes;

import jakarta.validation.constraints.NotEmpty;

public class PacientesFormE {

	private String idE;
	
	@NotEmpty(message = "No puede estar vacío")
	private String nombreE;
	@NotEmpty(message = "No puede estar vacío")
	private String apellidosE;
	@NotEmpty(message = "No puede estar vacío")
	private String dniE;
	private String dniViejoE;
	@NotEmpty(message = "No puede estar vacío")
	private String domicilioE;
	@NotEmpty(message = "No puede estar vacío")
	private String cpE;
	@NotEmpty(message = "No puede estar vacío")
	private String poblaE;
	@NotEmpty(message = "No puede estar vacío")
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
