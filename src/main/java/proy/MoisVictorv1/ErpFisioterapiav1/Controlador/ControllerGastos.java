package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.GastosRepositorio;

@Controller
public class ControllerGastos {
	@Autowired
	GastosRepositorio gastosRepositorio;
	
	@RequestMapping(path ="intranet/gastos/gastos")
	public String verGastos(Model model) {
		
		return "intranet/gastos/gastos";
	}
	
	@RequestMapping("intranet/gastos/registrarGastos")
	public String addGastos(Model model) {
		
		return "intranet/gastos/registrarGastos";
	}

}
