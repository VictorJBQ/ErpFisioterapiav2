package proy.MoisVictorv1.ErpFisioterapiav1.Form.Calendario;

import jakarta.validation.constraints.NotBlank;

public class AsignarFomrClale {

	private String idPaciente;
	private String idCita;
	@NotBlank(message = "No puede estar vacío")
	private String estadoE;
	public String getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getIdCita() {
		return idCita;
	}
	public void setIdCita(String idCita) {
		this.idCita = idCita;
	}
	public String getEstadoE() {
		return estadoE;
	}
	public void setEstadoE(String estadoE) {
		this.estadoE = estadoE;
	}
	public AsignarFomrClale(String idPaciente, String idCita,
			@NotBlank(message = "No puede estar vacío") String estadoE) {
		super();
		this.idPaciente = idPaciente;
		this.idCita = idCita;
		this.estadoE = estadoE;
	}

	
	

	
	
}
