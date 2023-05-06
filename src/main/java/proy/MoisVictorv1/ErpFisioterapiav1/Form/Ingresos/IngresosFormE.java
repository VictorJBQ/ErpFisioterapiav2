package proy.MoisVictorv1.ErpFisioterapiav1.Form.Ingresos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class IngresosFormE {

	public String idE;
	@NotEmpty(message = "No puede estar vacío")
	@DecimalMin(value = "0.01", inclusive = true, message = "Debe ser mayor que cero")
	@Digits(integer = 5, fraction = 3, message = "Debe tener un máximo de 5 dígitos enteros y 3 decimales")
	public String importeE;
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio")
	public String tipoE;
	
	@NotBlank(message = "No puede estar vacío")
	public String fechaE;
	
	
	public IngresosFormE(String idE,
			@NotEmpty(message = "No puede estar vacío") @DecimalMin(value = "0.01", inclusive = true, message = "Debe ser mayor que cero") @Digits(integer = 5, fraction = 3, message = "Debe tener un máximo de 5 dígitos enteros y 3 decimales") String importeE,
			@NotBlank(message = "No puede estar vacío") @Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio") String tipoE,
			@NotBlank(message = "No puede estar vacío") String fechaE) {
		super();
		this.idE = idE;
		this.importeE = importeE;
		this.tipoE = tipoE;
		this.fechaE = fechaE;
	}
	public String getFechaE() {
		return fechaE;
	}
	public void setFechaE(String fechaE) {
		this.fechaE = fechaE;
	}
	public String getIdE() {
		return idE;
	}
	public void setIdE(String idE) {
		this.idE = idE;
	}
	public String getImporteE() {
		return importeE;
	}
	public void setImporteE(String importeE) {
		this.importeE = importeE;
	}
	public String getTipoE() {
		return tipoE;
	}
	public void setTipoE(String tipoE) {
		this.tipoE = tipoE;
	}
	
	
}
