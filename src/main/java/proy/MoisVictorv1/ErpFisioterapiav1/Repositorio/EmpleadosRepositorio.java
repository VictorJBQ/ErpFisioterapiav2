package proy.MoisVictorv1.ErpFisioterapiav1.Repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Empleados;


public interface EmpleadosRepositorio extends CrudRepository<Empleados,String> {
	Empleados findByidentificador(String identificador);
	 //Optional<Empleados> findByidentificador(String identificador);
	Empleados findByemail(String identificador);
}
