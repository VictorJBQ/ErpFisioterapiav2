package proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm;

public class ExcelCitaPenConfirmarForm {
	private String Fecha;
	private String Hora;
	private String Estado;
	private String Paciente;
	private String Tipo;
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public String getHora() {
		return Hora;
	}
	public void setHora(String hora) {
		Hora = hora;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getPaciente() {
		return Paciente;
	}
	public void setPaciente(String paciente) {
		Paciente = paciente;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public ExcelCitaPenConfirmarForm(String fecha, String hora, String estado, String paciente, String tipo) {
		super();
		Fecha = fecha;
		Hora = hora;
		Estado = estado;
		Paciente = paciente;
		Tipo = tipo;
	}
	
	
}
