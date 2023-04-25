package proy.MoisVictorv1.ErpFisioterapiav1;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Empleados;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Pacientes;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Roles;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Tarifas;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.EmpleadosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.RolesRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.TarifasRepositorio;



@SpringBootApplication
public class ErpFisioterapiav1Application {
	@Autowired
	EmpleadosRepositorio empleadosRepositorio;
	
	@Autowired
	CitasRepositorio citasRepositorio;
	@Autowired
	RolesRepositorio repositorio; 
	
	@Autowired
	PacientesRepositorio pacientesRepositorio;
	
	@Autowired
	TarifasRepositorio tarifasRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(ErpFisioterapiav1Application.class, args);
	}
	
	@Bean //Cargamos independientemente

	public CommandLineRunner demo(EmpleadosRepositorio empleados) {

	return (args)->{
		Roles rol = new Roles("ADMIN");
		repositorio.save(rol);
		var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		
	Empleados empleado= new Empleados("T01","Carlos",encoder.encode("123"),rol);
		empleadosRepositorio.save(empleado);
	
	//Citas cita= new Citas(LocalDate.now(), LocalTime.now(), "libre", "fija", empleado);
////	
//citasRepositorio.save(cita);
		//Pacientes paciente= new Pacientes("Catalina",66565565);
	//pacientesRepositorio.save(paciente);
	
	Tarifas tarifa= new Tarifas("amigos",20.0);
	Tarifas tarifa2= new Tarifas("interna",40.0);
	Tarifas tarifa3= new Tarifas("externa",45.0);
	tarifasRepositorio.save(tarifa);
	tarifasRepositorio.save(tarifa2);
	tarifasRepositorio.save(tarifa3);
		
		
		


	};

	}


}
