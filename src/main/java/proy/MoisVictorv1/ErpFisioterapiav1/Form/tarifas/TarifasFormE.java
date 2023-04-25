package proy.MoisVictorv1.ErpFisioterapiav1.Form.tarifas;

import jakarta.validation.constraints.NotEmpty;

public class TarifasFormE {

	@NotEmpty(message = "No puede estar vacío")
	private String tipoViejaE;
	
	@NotEmpty(message = "No puede estar vacío")
	private String tipoNuevaE;
	
	@NotEmpty(message = "No puede estar vacío")
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
