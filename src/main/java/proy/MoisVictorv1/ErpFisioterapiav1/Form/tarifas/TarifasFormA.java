package proy.MoisVictorv1.ErpFisioterapiav1.Form.tarifas;

import jakarta.validation.constraints.NotEmpty;

public class TarifasFormA {

	@NotEmpty(message = "No puede estar vacío")
	private String tipoNuevaA;
	
	@NotEmpty(message = "No puede estar vacío")
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
