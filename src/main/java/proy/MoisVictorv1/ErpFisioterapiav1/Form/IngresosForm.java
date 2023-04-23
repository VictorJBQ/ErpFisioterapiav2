package proy.MoisVictorv1.ErpFisioterapiav1.Form;

import jakarta.validation.constraints.NotNull;

public class IngresosForm {
	@NotNull
	private String importe;
	@NotNull
	private String tipo;
	@NotNull
	private String idFactura;
	
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}
	
	
	
}
