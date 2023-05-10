package proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm;

public class ExcelIngresosForm {
	private String Importe;
	private String Tipo;
	private String IdFactura;
	private String Fecha;
	public String getImporte() {
		return Importe;
	}
	public void setImporte(String importe) {
		Importe = importe;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public String getIdFactura() {
		return IdFactura;
	}
	public void setIdFactura(String idFactura) {
		IdFactura = idFactura;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public ExcelIngresosForm(String importe, String tipo, String idFactura, String fecha) {
		super();
		Importe = importe;
		Tipo = tipo;
		IdFactura = idFactura;
		Fecha = fecha;
	}
	
	
	
}
