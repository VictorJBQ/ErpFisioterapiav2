package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.FacturasRepositorio;

@Controller
public class ControllerFacturas {
 @Autowired
 FacturasRepositorio facturasRepositorio;
 
	@RequestMapping("/intranet/facturas/facturas")
	public String verFacturas(Model model) {
		
		return "/intranet/facturas/facturas";
	}

}
