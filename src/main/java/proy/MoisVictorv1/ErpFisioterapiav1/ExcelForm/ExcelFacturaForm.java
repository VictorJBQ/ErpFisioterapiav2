package proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm;

public class ExcelFacturaForm {
	private String FormaPago;
	private String Importe;
	private String Paciente;
	private String Fecha;
	public String getFormaPago() {
		return FormaPago;
	}
	public void setFormaPago(String formaPago) {
		FormaPago = formaPago;
	}
	public String getImporte() {
		return Importe;
	}
	public void setImporte(String importe) {
		Importe = importe;
	}
	public String getPaciente() {
		return Paciente;
	}
	public void setPaciente(String paciente) {
		Paciente = paciente;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public ExcelFacturaForm(String formaPago, String importe, String paciente, String fecha) {
		super();
		FormaPago = formaPago;
		Importe = importe;
		Paciente = paciente;
		Fecha = fecha;
	}
	
}
