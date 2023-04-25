package proy.MoisVictorv1.ErpFisioterapiav1.Form.Ingresos;

import jakarta.validation.constraints.NotEmpty;

public class IngresosFormE {

	public String idE;
	@NotEmpty(message = "No puede estar vacío")
	public String importeE;
	@NotEmpty(message = "No puede estar vacío")
	public String tipoE;
	public IngresosFormE(String idE, @NotEmpty(message = "No puede estar vacío") String importeE,
			@NotEmpty(message = "No puede estar vacío") String tipoE) {
		super();
		this.idE = idE;
		this.importeE = importeE;
		this.tipoE = tipoE;
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
