package proy.MoisVictorv1.ErpFisioterapiav1.Form.Calendario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AsignarFormClale2 {
	
	private String idCita;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio")
	private String nomPaciente;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[0-9]+$", message = "Debe contener solo números y no puede tener espacios en blanco")
	@Size(min = 9, max = 9, message = "Debe tener una longitud de 9 caracteres")
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
