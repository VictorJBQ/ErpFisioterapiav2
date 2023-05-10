package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.TarifasRepositorio;

@Controller
public class ControllerTarifas {
	@Autowired
	TarifasRepositorio tarifasRepositorio;
	
	@RequestMapping("/intranet/tarifas/tarifas")
	public String verTarifas(Model model) {
		
		return "/intranet/tarifas/tarifas";
	}
	
	@RequestMapping("/intranet/tarifas/altaTarifas")
	public String addTarifas(Model model) {
		
		return "/intranet/tarifas/altaTarifas";
	}
}
