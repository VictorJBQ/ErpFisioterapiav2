package proy.MoisVictorv1.ErpFisioterapiav1.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Ingresos {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String tipo;
	private Double importe;
	private LocalDate fecha;
	
	
	 public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	@OneToOne
	    @JoinColumn(name = "factura_id")
	    private Facturas factura;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public Facturas getFactura() {
		return factura;
	}
	public void setFactura(Facturas factura) {
		this.factura = factura;
	}
	public Ingresos(Double importe, Facturas factura) {
		super();
		this.tipo="sesión cobrada";
		this.importe = importe;
		this.factura = factura;
	}
	
	public Ingresos(Double importe, Facturas factura, LocalDate fecha) {
		super();
		this.tipo="sesión cobrada";
		this.importe = importe;
		this.factura = factura;
		this.fecha = fecha;
	}
	public Ingresos(String tipo, Double importe) {
		super();
		this.tipo = tipo;
		this.importe = importe;
	}
	
	public Ingresos(String tipo, Double importe,LocalDate fecha) {
		super();
		this.tipo = tipo;
		this.importe = importe;
		this.fecha = fecha;
	}
	public Ingresos() {
		super();
	}
	
	
	 
	 
	
	 
	 
}
