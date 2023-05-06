package proy.MoisVictorv1.ErpFisioterapiav1.Form.tarifas;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class TarifasFormE {

	@NotEmpty(message = "No puede estar vacío")
	private String tipoViejaE;
	
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio")
	private String tipoNuevaE;
	
	@NotEmpty(message = "No puede estar vacío")
	@DecimalMin(value = "0.01", inclusive = true, message = "Debe ser mayor que cero")
	@Digits(integer = 3, fraction = 2, message = "Debe tener un máximo de 3 dígitos enteros y 2 decimales")
	private String precioE;

	public TarifasFormE(@NotEmpty(message = "No puede estar vacío") String tipoViejaE,
			@NotEmpty(message = "No puede estar vacío") String tipoNuevaE,
			@NotEmpty(message = "No puede estar vacío") String precioE) {
		super();
		this.tipoViejaE = tipoViejaE;
		this.tipoNuevaE = tipoNuevaE;
		this.precioE = precioE;
	}

	public String getTipoViejaE() {
		return tipoViejaE;
	}

	public void setTipoViejaE(String tipoViejaE) {
		this.tipoViejaE = tipoViejaE;
	}

	public String getTipoNuevaE() {
		return tipoNuevaE;
	}

	public void setTipoNuevaE(String tipoNuevaE) {
		this.tipoNuevaE = tipoNuevaE;
	}

	public String getPrecioE() {
		return precioE;
	}

	public void setPrecioE(String precioE) {
		this.precioE = precioE;
	}
	
	
}
