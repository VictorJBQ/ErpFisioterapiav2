package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Pacientes;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Tarifas;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.TarifasRepositorio;

@Controller
public class ControllerPacientes {
	@Autowired
	PacientesRepositorio pacientesRepositorio;
	
	@Autowired
	TarifasRepositorio tarifasRepositorio;
	
	@Autowired
	CitasRepositorio citasRepositorio;
	
	@RequestMapping(path="intranet/pacientes/pacientes")
	public String verPacientes(Model model) {
		List<Tarifas> pacientes=(List<Tarifas>) tarifasRepositorio.findAll();
		List<Citas> citas = (List<Citas>) citasRepositorio.findByEstadoIn(Arrays.asList("libre", "cancelado"));;
		
		List<Citas> aux =  new ArrayList<Citas>();
	    for(Citas c: citas) {
	    	if ((c.getFecha().isEqual(LocalDate.now()) && c.getHora().isAfter(LocalTime.now())) || c.getFecha().isAfter(LocalDate.now())) {
	    	    aux.add(c);
	    	}
	    }
		
		model.addAttribute("citasLista",aux);
		model.addAttribute("lista",pacientes);
		return "intranet/pacientes/pacientes";
	}
	
	@RequestMapping(path="intranet/pacientes/altaPacientes")
	public String addPacientes(Model model) {
		
		return "intranet/pacientes/altaPacientes";
	}
	
	@RequestMapping(path="p22")
	public String addPff(Model model) {
		
		return "pruebaTablaPacientes";
	}

}
