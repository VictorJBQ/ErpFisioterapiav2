package proy.MoisVictorv1.ErpFisioterapiav1.Form.inicio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CambioPSForm {

	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String nueva;
	
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String nuevaConfirmada;
	
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String vieja;
	
	private String idEmpleado;
	

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNueva() {
		return nueva;
	}

	public void setNueva(String nueva) {
		this.nueva = nueva;
	}

	public String getNuevaConfirmada() {
		return nuevaConfirmada;
	}

	public void setNuevaConfirmada(String nuevaConfirmada) {
		this.nuevaConfirmada = nuevaConfirmada;
	}

	public String getVieja() {
		return vieja;
	}

	public void setVieja(String vieja) {
		this.vieja = vieja;
	}



	public CambioPSForm(
			@NotBlank(message = "No puede estar vacío") @Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco") String nueva,
			@NotBlank(message = "No puede estar vacío") @Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco") String nuevaConfirmada,
			@NotBlank(message = "No puede estar vacío") @Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco") String vieja,
			String idEmpleado) {
		super();
		this.nueva = nueva;
		this.nuevaConfirmada = nuevaConfirmada;
		this.vieja = vieja;
		this.idEmpleado = idEmpleado;
	}

	public CambioPSForm() {
		super();
	}
	
	
}
