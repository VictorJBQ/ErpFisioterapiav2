package proy.MoisVictorv1.ErpFisioterapiav1.Form.gastos;



import jakarta.validation.constraints.NotEmpty;

public class GastosFormA {
	
	@NotEmpty(message = "No puede estar vacío")
	private String fechaA;
	
	@NotEmpty(message = "No puede estar vacío")
	private String importeA;
	
	@NotEmpty(message = "No puede estar vacío")
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
