package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import proy.MoisVictorv1.ErpFisioterapiav1.Form.Prueba;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Pacientes;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;
@Controller
public class CONTROL2 {

	@Autowired
	private CitasRepositorio citasRepositorio;
	@Autowired
	private PacientesRepositorio pacientesRepositorio;
	
		@GetMapping(path ="pruebatablas")
		public String verGastos(Model model) {
		
		return "pruebatablas";
		}
		
		@GetMapping(path ="s2")
		public String vedrGastos(Model model) {
			Iterable<Pacientes> itUsu = pacientesRepositorio.findAll();
			List<Pacientes> listaUsuarios = new ArrayList<Pacientes>();
			itUsu.forEach(listaUsuarios::add);
			model.addAttribute("lista", listaUsuarios);
		return "s2";
	}
		
		@PostMapping(path = "s2")
		public String ch(@RequestParam("miSelect")String pb) {
			
			System.out.println(pb);
			return "s2";
			
		}
		
		 @PutMapping(path ="confirmarTodo")
		 public ResponseEntity<Void> confirmarTodo() {
			    System.out.println("ERERE");
			    // Lógica para confirmar todas las citas
			    return ResponseEntity.ok().build();
			}
}
