package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerGeneral {
	@GetMapping("/intranet/inicio")
	public String bienvenida(Model model) {
		model.addAttribute("mensaje",model.getAttribute("mensaje"));
		return "/intranet/inicio";
	}
	@GetMapping("/intranet/Plantilla")
	public String plantilla(Model model) {
		return "/intranet/Plantilla";
	}
	 
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("mensaje",model.getAttribute("mensaje"));
		return "/index";
	}
	 
	    @GetMapping("/login")
	    public String add2() {
	        return "/login";
	    }
	    
	    @PostMapping(path="/login")
	    public String add1() {
	        return "/login";
	    }

	    
	}


