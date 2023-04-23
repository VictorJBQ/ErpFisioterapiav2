package proy.MoisVictorv1.ErpFisioterapiav1.Model;

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
public class Facturas {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String formaPago;
	private Double importe;
	
	
	   @OneToOne
	    @JoinColumn(name = "cita_id")
	    private Citas cita;
	   
	   @OneToOne(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Ingresos ingreso;


	
	
}
