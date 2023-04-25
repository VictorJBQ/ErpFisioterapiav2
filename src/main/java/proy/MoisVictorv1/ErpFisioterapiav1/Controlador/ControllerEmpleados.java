package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerEmpleados {
	@RequestMapping(path ="intranet/empleados/empleados")
	public String verEmpleados(Model model) {
		
		return "intranet/empleados/empleados";
	}
	
	@RequestMapping(path ="intranet/empleados/altaEmpleados")
	public String addEmpleados(Model model) {
		
		return "intranet/empleados/altaEmpleados";
	}
}
