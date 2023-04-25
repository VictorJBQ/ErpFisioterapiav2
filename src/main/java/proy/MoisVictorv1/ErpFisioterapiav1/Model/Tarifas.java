package proy.MoisVictorv1.ErpFisioterapiav1.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"pacientes"})
public class Tarifas {


	@Id
	private String tipo;
	private Double precio;
	
	@OneToMany(mappedBy="tarifas")
	private List<Pacientes> pacientes;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Tarifas(String tipo, Double precio) {
		super();
		this.tipo = tipo;
		this.precio = precio;
	}

	public Tarifas() {
		super();
	}
	

}
