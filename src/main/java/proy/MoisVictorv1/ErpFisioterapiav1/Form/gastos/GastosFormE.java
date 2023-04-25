package proy.MoisVictorv1.ErpFisioterapiav1.Form.gastos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;

public class GastosFormE {
	
	@NotEmpty(message = "No puede estar vacío")
	private String fechaE;
	
	@NotEmpty(message = "No puede estar vacío")
	private String importeE;
	
	@NotEmpty(message = "No puede estar vacío")
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
