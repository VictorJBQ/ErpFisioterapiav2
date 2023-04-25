package proy.MoisVictorv1.ErpFisioterapiav1.Form.Ingresos;

import jakarta.validation.constraints.NotEmpty;

public class IngresosFormA {

	@NotEmpty(message = "No puede estar vacío")
	public String importeA;
	@NotEmpty(message = "No puede estar vacío")
	public String tipoA;
	public IngresosFormA(@NotEmpty(message = "No puede estar vacío") String importeA,
			@NotEmpty(message = "No puede estar vacío") String tipoA) {
		super();
		this.importeA = importeA;
		this.tipoA = tipoA;
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
