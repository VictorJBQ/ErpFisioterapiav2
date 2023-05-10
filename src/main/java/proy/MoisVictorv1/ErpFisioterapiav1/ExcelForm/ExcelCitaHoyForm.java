package proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm;

public class ExcelCitaHoyForm {
private String Hora;
private String Estado;
private String Paciente;
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
public ExcelCitaHoyForm(String hora, String estado, String paciente) {
	super();
	Hora = hora;
	Estado = estado;
	Paciente = paciente;
}


}
