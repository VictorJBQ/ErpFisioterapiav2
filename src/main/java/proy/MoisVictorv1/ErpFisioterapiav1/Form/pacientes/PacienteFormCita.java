package proy.MoisVictorv1.ErpFisioterapiav1.Form.pacientes;

public class PacienteFormCita {

	String idCita;
	String idPaciente;
	public String getIdCita() {
		return idCita;
	}
	public void setIdCita(String idCita) {
		this.idCita = idCita;
	}
	public String getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}
	public PacienteFormCita(String idCita, String idPaciente) {
		super();
		this.idCita = idCita;
		this.idPaciente = idPaciente;
	}
	
	
}
