package proy.MoisVictorv1.ErpFisioterapiav1.Form.tarifas;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class TarifasFormA {

	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio")
	private String tipoNuevaA;
	
	@NotEmpty(message = "No puede estar vacío")
	@DecimalMin(value = "0.01", inclusive = true, message = "Debe ser mayor que cero")
	@Digits(integer = 3, fraction = 2, message = "Debe tener un máximo de 3 dígitos enteros y 2 decimales")
	private String precioA;

	public TarifasFormA(@NotEmpty(message = "No puede estar vacío") String tipoNuevaA,
			@NotEmpty(message = "No puede estar vacío") String precioA) {
		super();
		this.tipoNuevaA = tipoNuevaA;
		this.precioA = precioA;
	}

	public String getTipoNuevaA() {
		return tipoNuevaA;
	}

	public void setTipoNuevaA(String tipoNuevaA) {
		this.tipoNuevaA = tipoNuevaA;
	}

	public String getPrecioA() {
		return precioA;
	}

	public void setPrecioA(String precioA) {
		this.precioA = precioA;
	}
	
	
}
