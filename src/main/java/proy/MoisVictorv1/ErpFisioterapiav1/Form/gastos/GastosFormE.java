package proy.MoisVictorv1.ErpFisioterapiav1.Form.gastos;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class GastosFormE {
	
	@NotEmpty(message = "No puede estar vacío")
	private String fechaE;
	
	@NotEmpty(message = "No puede estar vacío")
	@DecimalMin(value = "0.01", inclusive = true, message = "Debe ser mayor que cero")
	@Digits(integer = 5, fraction = 3, message = "Debe tener un máximo de 5 dígitos enteros y 3 decimales")
	private String importeE;
	
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio")
	private String tipoE;
	
	@NotEmpty(message = "No puede estar vacío")
	private String empleadoE;
	
	private String idE;
	
	public GastosFormE(@NotEmpty(message = "No puede estar vacío") String fechaE,
			@NotEmpty(message = "No puede estar vacío") String importeE,
			@NotEmpty(message = "No puede estar vacío") String tipoE,
			@NotEmpty(message = "No puede estar vacío") String empleadoE, String idE) {
		super();
		this.fechaE = fechaE;
		this.importeE = importeE;
		this.tipoE = tipoE;
		this.empleadoE = empleadoE;
		this.idE = idE;
	}

	public String getIdE() {
		return idE;
	}

	public void setIdE(String idE) {
		this.idE = idE;
	}

	
	
	public String getFechaE() {
		return fechaE;
	}

	public void setFechaE(String fechaE) {
		this.fechaE = fechaE;
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

	public String getEmpleadoE() {
		return empleadoE;
	}

	public void setEmpleadoE(String empleadosE) {
		this.empleadoE = empleadosE;
	}

	


	
	
}
