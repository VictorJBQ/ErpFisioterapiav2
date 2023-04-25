package proy.MoisVictorv1.ErpFisioterapiav1.Model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"ingreso"})
public class Facturas {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String formaPago;
	private Double importe;
	private LocalDate fecha;
	
	
	   @OneToOne
	    @JoinColumn(name = "cita_id")
	    private Citas cita;
	   
	   @OneToOne(mappedBy = "factura")
	    private Ingresos ingreso;

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Citas getCita() {
		return cita;
	}

	public void setCita(Citas cita) {
		this.cita = cita;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Facturas(String formaPago, Double importe, LocalDate fecha, Citas cita) {
		super();
		this.formaPago = formaPago;
		this.importe = importe;
		this.fecha = fecha;
		this.cita = cita;
	}
	
	

	public Facturas(int id, String formaPago, Double importe, LocalDate fecha, Citas cita) {
		super();
		this.id = id;
		this.formaPago = formaPago;
		this.importe = importe;
		this.fecha = fecha;
		this.cita = cita;
	}

	public Facturas() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	
	
}
