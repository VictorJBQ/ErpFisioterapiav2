package proy.MoisVictorv1.ErpFisioterapiav1.Repositorio;

import org.springframework.data.repository.CrudRepository;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Ingresos;


public interface IngresosRepositorio extends CrudRepository<Ingresos,Integer> {

	Ingresos findById(String id);
}
