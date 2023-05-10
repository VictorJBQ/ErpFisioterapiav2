package proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm;

public class ExcelTarifasForm {
private String Tipo;
private String Precio;
public String getTipo() {
	return Tipo;
}
public void setTipo(String tipo) {
	Tipo = tipo;
}
public String getPrecio() {
	return Precio;
}
public void setPrecio(String precio) {
	Precio = precio;
}
public ExcelTarifasForm(String tipo, String precio) {
	super();
	Tipo = tipo;
	Precio = precio;
}

}
