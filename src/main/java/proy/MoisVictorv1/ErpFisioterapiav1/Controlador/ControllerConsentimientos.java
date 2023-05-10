package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerConsentimientos {

	@RequestMapping("/intranet/consentimientos/consentimientoD")
	public String tratamientoDatos(Model model) {
		
		return "/intranet/consentimientos/consentimientoD";
	}
	
	@RequestMapping("/intranet/consentimientos/consentimientoP")
	public String puncionSeca(Model model) {
		
		return "/intranet/consentimientos/consentimientoP";
}
}