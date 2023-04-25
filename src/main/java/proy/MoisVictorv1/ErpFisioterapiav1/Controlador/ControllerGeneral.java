package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerGeneral {
	@RequestMapping(path="intranet/inicio")
	public String bienvenida(Model model) {
		model.addAttribute("mensaje",model.getAttribute("mensaje"));
		return "intranet/inicio";
	}
	 
	@RequestMapping(path="index")
	public String index(Model model) {
		model.addAttribute("mensaje",model.getAttribute("mensaje"));
		return "index";
	}
	 
	    @GetMapping(path="login")
	    public String add2() {
	        return "login";
	    }
	    
	    @PostMapping(path="login")
	    public String add1() {
	        return "login";
	    }

	    
	}


