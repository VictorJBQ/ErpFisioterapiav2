package proy.MoisVictorv1.ErpFisioterapiav1.Form;

import jakarta.validation.constraints.NotNull;

public class TarifasForm {
	@NotNull
	private String tipo;
	@NotNull
	private String precio;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
}
