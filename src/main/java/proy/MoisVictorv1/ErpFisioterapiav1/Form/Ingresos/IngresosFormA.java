package proy.MoisVictorv1.ErpFisioterapiav1.Form.Ingresos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class IngresosFormA {

	@NotEmpty(message = "No puede estar vacío")
	@DecimalMin(value = "0.01", inclusive = true, message = "Debe ser mayor que cero")
	@Digits(integer = 5, fraction = 3, message = "Debe tener un máximo de 5 dígitos enteros y 3 decimales")
	public String importeA;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio")
	public String tipoA;
	@NotBlank(message = "No puede estar vacío")
	public String fechaA;
	
	
	public IngresosFormA(
			@NotEmpty(message = "No puede estar vacío") @DecimalMin(value = "0.01", inclusive = true, message = "Debe ser mayor que cero") @Digits(integer = 5, fraction = 3, message = "Debe tener un máximo de 5 dígitos enteros y 3 decimales") String importeA,
			@NotBlank(message = "No puede estar vacío") @Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio") String tipoA,
			@NotBlank(message = "No puede estar vacío") String fechaA) {
		super();
		this.importeA = importeA;
		this.tipoA = tipoA;
		this.fechaA = fechaA;
	}
	public String getFechaA() {
		return fechaA;
	}
	public void setFechaA(String fechaA) {
		this.fechaA = fechaA;
	}
	public String getImporteA() {
		return importeA;
	}
	public void setImporteA(String importeA) {
		this.importeA = importeA;
	}
	public String getTipoA() {
		return tipoA;
	}
	public void setTipoA(String tipoA) {
		this.tipoA = tipoA;
	}
	
	
}
