package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Pacientes;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Tarifas;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.TarifasRepositorio;

@Controller
public class ControllerCitas {
	
	@Autowired
	CitasRepositorio citasRepositorio;
	@Autowired
	PacientesRepositorio pacientesRepositorio;
	
	@Autowired
	TarifasRepositorio tarifasRepositorio;
	
	@RequestMapping(path="intranet/citas/citas")
	public String verCitas(Model model) {
		Iterable<Pacientes> itUsu = pacientesRepositorio.findAll();
		List<Pacientes> listaUsuarios = new ArrayList<Pacientes>();
		List<Tarifas> tarifas=(List<Tarifas>) tarifasRepositorio.findAll();
		itUsu.forEach(listaUsuarios::add);
		model.addAttribute("lista", listaUsuarios);
		model.addAttribute("tari",tarifas);
	 
	    return "intranet/citas/citas";
	}
	

	
	@PostMapping(path ="intranet/citas/pruebas")
	public String manejarFormulario(@RequestParam("parametro") Long idCita) {
	    System.out.println(idCita+" dsdasdasd");
	    citasRepositorio.deleteById(idCita.intValue());
	    return "redirect:intranet/citas/pruebas";
	}
	
	@PostMapping(value="intranet/citas/citas")
	public String  save() {
	    // Aquí puedes hacer lo que necesites con los valores recibidos
	    System.out.println("Hola");
	    // Devuelve una respuesta con el objeto citas guardado y el código de estado 200
	    return "redirect:/index";
	}
	@RequestMapping(path="intranet/citas/pruebas")
	public String adddCirr(Model model) {
		  Iterable<Citas> itCitas = citasRepositorio.findAll();
		    List<Citas> listaCitas = new ArrayList<Citas>();
		    itCitas.forEach(listaCitas::add);
		    model.addAttribute("listaCitas",listaCitas);
		return "intranet/citas/pruebas";
	}
	
	@RequestMapping(path="intranet/citas/altaCitas")
	public String addCitas(Model model) {
		Iterable<Pacientes> itUsu = pacientesRepositorio.findAll();
		List<Pacientes> listaUsuarios = new ArrayList<Pacientes>();
		itUsu.forEach(listaUsuarios::add);
		model.addAttribute("lista", listaUsuarios);
		
		return "intranet/citas/altaCitas";
	}
	
	

	
	@GetMapping(path="intranet/citas/asignarCitas")
	public String asignarCitas(Model model) {
		Iterable<Pacientes> itUsu = pacientesRepositorio.findAll();
		List<Pacientes> listaUsuarios = new ArrayList<Pacientes>();
		itUsu.forEach(listaUsuarios::add);
		model.addAttribute("lista", listaUsuarios);
		
		return "intranet/citas/asignarCitas";
	}
}
