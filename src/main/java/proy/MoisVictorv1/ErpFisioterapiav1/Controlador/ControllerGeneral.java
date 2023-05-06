package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.validation.Valid;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.inicio.CambioPSForm;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Empleados;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Pacientes;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Tarifas;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.EmpleadosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.TarifasRepositorio;

@Controller
public class ControllerGeneral {
	@Autowired
	CitasRepositorio citasRepositorio;
	@Autowired
	PacientesRepositorio pacientesRepositorio;
	
	@Autowired
	TarifasRepositorio tarifasRepositorio;
	@Autowired
	EmpleadosRepositorio empleadosRepositorio;
	@RequestMapping(path="intranet/inicio")
	public String bienvenida(Model model, Authentication authentication) {
		HashMap<String, String> empleado= new HashMap<>();
		
		 UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		 Empleados emp= empleadosRepositorio.findByidentificador(userDetails.getUsername());
		 Integer in= citasRepositorio.citasTotalEmpleado(emp.getIdentificador());
		 Integer lib= citasRepositorio.citasMesLibEmpleado(emp.getIdentificador(), LocalDate.now().getYear(),LocalDate.now().getMonthValue());
		 Integer canc= citasRepositorio.citasMesCanceladasEmpleado(emp.getIdentificador(), LocalDate.now().getYear(),LocalDate.now().getMonthValue());
		 Integer tol= citasRepositorio.citasMesTotalladasEmpleado(emp.getIdentificador(), LocalDate.now().getYear(),LocalDate.now().getMonthValue());
		 Integer ter= citasRepositorio.citasMesTermidasEmpleado(emp.getIdentificador(), LocalDate.now().getYear(),LocalDate.now().getMonthValue());
		 
		 empleado.put("codigo", emp.getIdentificador());
		 empleado.put("nombre", emp.getNombre());
		 empleado.put("rol", emp.getRoles().getTipo());
		 empleado.put("citas",""+in );
		 empleado.put("citasTotalmes",""+tol );
		 empleado.put("citasCanceladasmes",""+canc );
		 empleado.put("citasLibresmes",""+lib );
		 empleado.put("citasTerminadasmes",""+ter );
		
		 
		 
		 List<Tarifas> tarifas=(List<Tarifas>) tarifasRepositorio.findAll();
		model.addAttribute("tari",tarifas);
		Iterable<Pacientes> itUsu = pacientesRepositorio.findAll();
		List<Pacientes> listaUsuarios = new ArrayList<Pacientes>();
		itUsu.forEach(listaUsuarios::add);
		model.addAttribute("lista", listaUsuarios);
		model.addAttribute("datos", empleado);
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
	    
	    
	    @PostMapping(path = "intranet/cambioPS")
	    public String checkPersonInfo(@Valid CambioPSForm e, BindingResult bindingResult) {
			
			if(bindingResult.hasErrors()) {
				
				return "intranet/inicio";
			}
			return null;
			
	    }
	}


