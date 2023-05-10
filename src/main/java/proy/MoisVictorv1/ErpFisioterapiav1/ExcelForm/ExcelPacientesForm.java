package proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm;

public class ExcelPacientesForm {
	private String Nombre;
	private String Apellidos;
	private String Dni;
	private String FechaAlta;
	private String Domicilio;
	private String CodigoPostal;
	private String Poblacion;
	private String Telefono;
	private String Conoce;
	private String Tarifa;
	public ExcelPacientesForm(String nombre, String apellidos, String dni, String fechaAlta, String domicilio,
			String codigoPostal, String poblacion, String telefono, String conoce, String tarifa) {
		super();
		Nombre = (nombre != null) ? nombre : "NA";
		Apellidos = (apellidos != null) ? apellidos : "NA";
		Dni = (dni != null) ? dni : "NA";
		FechaAlta = (fechaAlta != null) ? fechaAlta : "NA";
		Domicilio = (domicilio != null) ? domicilio : "NA";
		CodigoPostal = (codigoPostal != null) ? codigoPostal : "NA";
		Poblacion = (poblacion != null) ? poblacion : "NA";
		Telefono = (telefono != null) ? telefono : "NA";
		Conoce = (conoce != null) ? conoce : "NA";
		Tarifa = (tarifa != null) ? tarifa : "NA";
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	public String getDni() {
		return Dni;
	}
	public void setDni(String dni) {
		Dni = dni;
	}
	public String getFechaAlta() {
		return FechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		FechaAlta = fechaAlta;
	}
	public String getDomicilio() {
		return Domicilio;
	}
	public void setDomicilio(String domicilio) {
		Domicilio = domicilio;
	}
	public String getCodigoPostal() {
		return CodigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		CodigoPostal = codigoPostal;
	}
	public String getPoblacion() {
		return Poblacion;
	}
	public void setPoblacion(String poblacion) {
		Poblacion = poblacion;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getConoce() {
		return Conoce;
	}
	public void setConoce(String conoce) {
		Conoce = conoce;
	}
	public String getTarifa() {
		return Tarifa;
	}
	public void setTarifa(String tarifa) {
		Tarifa = tarifa;
	}
	
	
}
