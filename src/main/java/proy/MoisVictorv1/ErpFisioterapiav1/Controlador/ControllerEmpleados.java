package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Roles;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.RolesRepositorio;

@Controller
public class ControllerEmpleados {
	
	@Autowired 
	RolesRepositorio rolesRepositorio;
	@RequestMapping("/intranet/empleados/empleados")
	public String verEmpleados(Model model) {
	
		return "/intranet/empleados/empleados";
	}
	
	@RequestMapping("/intranet/empleados/altaEmpleados")
	public String addEmpleados(Model model) {
		List<Roles> roles=(List<Roles>) rolesRepositorio.findAll();
		model.addAttribute("lista",roles);
		return "/intranet/empleados/altaEmpleados";
	}
}
