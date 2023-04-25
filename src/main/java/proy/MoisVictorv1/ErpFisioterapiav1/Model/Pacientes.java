package proy.MoisVictorv1.ErpFisioterapiav1.Model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"citas"})
public class Pacientes {


	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String dni;
	private String nombre;
	private String apellidos;
	private String domicilio;
	private String codigoPostal;
	private int telefono;
	private LocalDate fechaAlta;
	private String sabeDeMi;
	private String poblacion;
	
	
	@ManyToOne
	@JoinColumn(name="tarifa")
	private Tarifas tarifas;
	
	@OneToMany(mappedBy="id")
	private List<Citas> citas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getSabeDemi() {
		return sabeDeMi;
	}

	public void setSabeDemi(String sabeDemi) {
		this.sabeDeMi = sabeDemi;
	}

	public Tarifas getTarifas() {
		return tarifas;
	}

	public void setTarifas(Tarifas tarifas) {
		this.tarifas = tarifas;
	}

	public List<Citas> getCitas() {
		return citas;
	}

	public void setCitas(List<Citas> citas) {
		this.citas = citas;
	}



	public String getSabeDeMi() {
		return sabeDeMi;
	}

	public void setSabeDeMi(String sabeDeMi) {
		this.sabeDeMi = sabeDeMi;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public Pacientes(String dni, String nombre, String apellidos, String domicilio, String codigoPostal, int telefono,
			 String sabeDeMi, String poblacion, Tarifas tarifas) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.fechaAlta = LocalDate.now();
		this.sabeDeMi = sabeDeMi;
		this.poblacion = poblacion;
		this.tarifas = tarifas;
	}

	public Pacientes(String nombre, int telefono) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.fechaAlta= LocalDate.now();
	}

	public Pacientes() {
		super();
	}
	
	
	
	

}
