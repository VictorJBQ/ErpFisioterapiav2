package proy.MoisVictorv1.ErpFisioterapiav1.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"empleado"})
public class Roles {
	
	@Id
	private String tipo;

	@OneToMany(mappedBy="roles")
	private List<Empleados> empleado;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Empleados> getEmpleado() {
		return empleado;
	}

	public void setEmpleado(List<Empleados> empleado) {
		this.empleado = empleado;
	}

	public Roles(String tipo) {
		super();
		this.tipo = tipo;
	}

	public Roles() {
		super();
	}


	
	

}
