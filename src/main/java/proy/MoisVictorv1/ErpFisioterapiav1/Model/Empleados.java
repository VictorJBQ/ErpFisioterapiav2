package proy.MoisVictorv1.ErpFisioterapiav1.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Empleados {
	
	@Id
	private String identificador;
	private String nombre;
	private String password;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="roles_tipo")
	private Roles roles;
	
	@OneToMany(mappedBy="id")
	private List<Gastos> gastos;
	
	@OneToMany(mappedBy="id")
	private List<Citas> citas;

	public List<Citas> getCitas() {
		return citas;
	}

	public void setCitas(List<Citas> citas) {
		this.citas = citas;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public List<Gastos> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gastos> gastos) {
		this.gastos = gastos;
	}

	public Empleados(String identificador, String nombre, String password, @NotNull Roles roles) {
		super();
		this.identificador = identificador;
		this.nombre = nombre;
		this.password = password;
		this.roles = roles;
	}

	public Empleados() {
		super();
	}

	@Override
	public String toString() {
		return "Empleados [identificador=" + identificador + ", nombre=" + nombre + ", password=" + password
				+ ", roles=" + roles + ", gastos=" + gastos + "]";
	}
	
	
	
	

}
