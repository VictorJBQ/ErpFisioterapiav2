package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;

@Controller
public class ControllerPacientes {
	@Autowired
	PacientesRepositorio pacientesRepositorio;
	
	@RequestMapping("/intranet/pacientes/pacientes")
	public String verPacientes(Model model) {
		
		return "/intranet/pacientes/pacientes";
	}
	
	@RequestMapping("/intranet/pacientes/altaPacientes")
	public String addPacientes(Model model) {
		
		return "/intranet/pacientes/altaPacientes";
	}

}
