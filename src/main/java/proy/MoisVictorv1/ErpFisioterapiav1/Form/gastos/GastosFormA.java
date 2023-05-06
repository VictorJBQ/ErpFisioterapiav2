package proy.MoisVictorv1.ErpFisioterapiav1.Form.gastos;



import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class GastosFormA {
	
	@NotEmpty(message = "No puede estar vacío")
	private String fechaA;
	
	@NotEmpty(message = "No puede estar vacío")
	@DecimalMin(value = "0.01", inclusive = true, message = "Debe ser mayor que cero")
	@Digits(integer = 5, fraction = 3, message = "Debe tener un máximo de 5 dígitos enteros y 3 decimales")
	private String importeA;
	
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^(?=\\S)(?!.*\\s{2})(\\S\\s*\\S){0,9}\\S?$", message = "Debe tener un máximo de 20 caracteres, el primer y último carácter no pueden ser en blanco y no puede haber dos espacios en blanco seguidos en medio")
	private String tipoA;
	
	@NotEmpty(message = "No puede estar vacío")
	private String empleadoA;

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

	public String getEmpleadoA() {
		return empleadoA;
	}

	public void setEmpleadoA(String empleadoA) {
		this.empleadoA = empleadoA;
	}

	public GastosFormA(@NotEmpty(message = "No puede estar vacío") String fechaA,
			@NotEmpty(message = "No puede estar vacío") String importeA,
			@NotEmpty(message = "No puede estar vacío") String tipoA,
			@NotEmpty(message = "No puede estar vacío") String empleadoA) {
		super();
		this.fechaA = fechaA;
		this.importeA = importeA;
		this.tipoA = tipoA;
		this.empleadoA = empleadoA;
	}




	
	
	
	
}
