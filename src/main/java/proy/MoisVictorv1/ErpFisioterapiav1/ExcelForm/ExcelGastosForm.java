package proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm;

public class ExcelGastosForm {
	private String Fecha;
	private String Importe;
	private String Tipo;
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
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
	public ExcelGastosForm(String fecha, String importe, String tipo) {
		super();
		Fecha = fecha;
		Importe = importe;
		Tipo = tipo;
	}
	
	
}
