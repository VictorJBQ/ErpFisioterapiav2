package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Empleados;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Roles;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.EmpleadosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.GastosRepositorio;

@Controller
public class ControllerGastos {
	@Autowired
	GastosRepositorio gastosRepositorio;
	
	@Autowired
	EmpleadosRepositorio empleadosRepositorio;
	
	@RequestMapping(path="intranet/gastos/gastos")
	public String verGastos(Model model) {
		List<Empleados> empleados=(List<Empleados>) empleadosRepositorio.findAll();
		model.addAttribute("lista",empleados);
		return "intranet/gastos/gastos";
	}
	
	@RequestMapping(path="intranet/gastos/registrarGastos")
	public String addGastos(Model model) {
		
		return "intranet/gastos/registrarGastos";
	}

}
