package proy.MoisVictorv1.ErpFisioterapiav1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Empleados;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Roles;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.EmpleadosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.RolesRepositorio;



@SpringBootApplication
public class ErpFisioterapiav1Application {
	@Autowired
	EmpleadosRepositorio empleadosRepositorio;
	@Autowired
	RolesRepositorio repositorio;

	public static void main(String[] args) {
		SpringApplication.run(ErpFisioterapiav1Application.class, args);
	}
	
	
	@Profile({"dev","prod"})
	@Bean //Cargamos independientemente

	public CommandLineRunner demo(EmpleadosRepositorio empleados) {

	return (args)->{
		
	
		
		  var encoders = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		  Roles rol= new Roles("ADMIN"); repositorio.save(rol);
		  
		  Empleados emp= new Empleados("T01","Carlos",encoders.encode("123"),rol);
		  empleadosRepositorio.save(emp);
		 



	};

	}


}
