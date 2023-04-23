package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;

@Controller
public class ControllerCalendario {
	@Autowired
	CitasRepositorio citasRepositorio;
	@Autowired
	PacientesRepositorio pacientesRepositorio;
	
	@RequestMapping("/intranet/calendario/calendario")
	public String verCalendario(Model model) {
		
		return "/intranet/calendario/calendario";
	}

}
