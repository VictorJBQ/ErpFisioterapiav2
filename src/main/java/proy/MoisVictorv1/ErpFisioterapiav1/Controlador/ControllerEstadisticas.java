package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.GastosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.IngresosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;

@Controller
public class ControllerEstadisticas {
	@Autowired
	CitasRepositorio citasRepositorio;
	
	@Autowired
	PacientesRepositorio pacientesRepositorio;
	
	@Autowired
	IngresosRepositorio ingresosRepositorio;
	
	@Autowired
	GastosRepositorio gastosRepositorio;
	
	@RequestMapping(path ="intranet/estadisticas/diasTrabajados")
	public String diasTrabajados(Model model) {
		
		return "intranet/estadisticas/diasTrabajados";
	}
	
	@RequestMapping(path ="intranet/estadisticas/distribucionSesiones")
	public String distribucionSesiones(Model model) {
		
		return "intranet/estadisticas/distribucionSesiones";
	}
	
	@RequestMapping(path ="intranet/estadisticas/divisionPacientes")
	public String divisionPacientes(Model model) {
		
		return "intranet/estadisticas/divisionPacientes";
	}
	
	@RequestMapping(path ="intranet/estadisticas/gastosTotales")
	public String gastosTotales(Model model) {
		
		return "intranet/estadisticas/gastosTotales";
	}
	
	@RequestMapping(path ="intranet/estadisticas/ingresosTotales")
	public String ingresosTotales(Model model) {
		
		return "intranet/estadisticas/ingresosTotales";
	}
	
	@RequestMapping(path ="intranet/estadisticas/metodologiaPago")
	public String metodologiaPago(Model model) {
		
		return "intranet/estadisticas/metodologiaPago";
	}
	
	@RequestMapping(path ="intranet/estadisticas/numeroPacientes")
	public String numeroPacientes(Model model) {
		
		return "intranet/estadisticas/numeroPacientes";
	}
	
	@RequestMapping(path ="intranet/estadisticas/preciosSesiones")
	public String preciosSesiones(Model model) {
		
		return "intranet/estadisticas/preciosSesiones";
	}
	
	@RequestMapping(path ="intranet/estadisticas/procedenciaPacientes")
	public String procedenciaPacientes(Model model) {
		
		return "intranet/estadisticas/procedenciaPacientes";
	}

}
