package proy.MoisVictorv1.ErpFisioterapiav1.Form;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;

public class Prueba {
	private Citas cita;

	public Citas getCita() {
		return cita;
	}

	public void setCita(Citas cita) {
		this.cita = cita;
	}
	
	

	public Prueba() {
		super();
	}

	public Prueba(Citas cita) {
		super();
		this.cita = cita;
	}

	@Override
	public String toString() {
		return "Prueba [cita=" + cita + "]";
	}
	
	
}
