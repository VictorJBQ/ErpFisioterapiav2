package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;

@Controller
public class ControllerCitas {
	
	@Autowired
	CitasRepositorio citasRepositorio;
	@RequestMapping("/intranet/citas/citas")
	public String verCitas(Model model) {
		
		return "/intranet/citas/citas";
	}
	
	@RequestMapping("/intranet/citas/altaCitas")
	public String addCitas(Model model) {
		
		return "/intranet/citas/altaCitas";
	}
	
	@RequestMapping("/intranet/citas/asignarCitas")
	public String asignarCitas(Model model) {
		
		return "/intranet/citas/asignarCitas";
	}
}
