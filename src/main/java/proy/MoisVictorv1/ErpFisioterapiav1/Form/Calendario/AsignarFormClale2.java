package proy.MoisVictorv1.ErpFisioterapiav1.Form.Calendario;

import jakarta.validation.constraints.NotEmpty;

public class AsignarFormClale2 {
	
	private String idCita;
	@NotEmpty(message = "No puede estar vacío")
	private String nomPaciente;
	@NotEmpty(message = "No puede estar vacío")
	private String telPaciente;
	public String getIdCita() {
		return idCita;
	}
	public void setIdCita(String idCita) {
		this.idCita = idCita;
	}
	public String getNomPaciente() {
		return nomPaciente;
	}
	public void setNomPaciente(String nomPaciente) {
		this.nomPaciente = nomPaciente;
	}
	public String getTelPaciente() {
		return telPaciente;
	}
	public void setTelPaciente(String telPaciente) {
		this.telPaciente = telPaciente;
	}
	public AsignarFormClale2(String idCita, @NotEmpty(message = "No puede estar vacío") String nomPaciente,
			@NotEmpty(message = "No puede estar vacío") String telPaciente) {
		super();
		this.idCita = idCita;
		this.nomPaciente = nomPaciente;
		this.telPaciente = telPaciente;
	}
	
	

}
