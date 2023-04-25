package proy.MoisVictorv1.ErpFisioterapiav1.ApiCRUD;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Facturas;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.FacturasRepositorio;

@RestController
@RequestMapping(value="/api-fct/")//esto es para dar la RUtA al controlador como de origen y lo de abajo se le añade,
@CrossOrigin("*")
public class FacturasAPI {

	@Autowired
	FacturasRepositorio facturasRepositorio;
	
	@GetMapping("all")
	public List<Facturas> getAll(){
		
		return (List<Facturas>) facturasRepositorio.findAll();
		
	}
}
