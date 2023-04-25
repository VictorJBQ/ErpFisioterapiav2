package proy.MoisVictorv1.ErpFisioterapiav1.Repositorio;

import org.springframework.data.repository.CrudRepository;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Gastos;

public interface GastosRepositorio extends CrudRepository<Gastos,Integer> {
	Gastos findById(String id);
}
