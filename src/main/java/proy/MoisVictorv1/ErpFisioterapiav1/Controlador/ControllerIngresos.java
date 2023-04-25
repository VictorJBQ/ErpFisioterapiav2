package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.IngresosRepositorio;

@Controller
public class ControllerIngresos {

	@Autowired
	IngresosRepositorio ingresosRepositorio;
	
	@RequestMapping(path ="intranet/ingresos/ingresos")
	public String verIngresos(Model model) {
		
		return "intranet/ingresos/ingresos";
	}
	
	@RequestMapping(path ="intranet/ingresos/registrarIngresos")
	public String addIngresos(Model model) {
		
		return "intranet/ingresos/registrarIngresos";
	}
}
