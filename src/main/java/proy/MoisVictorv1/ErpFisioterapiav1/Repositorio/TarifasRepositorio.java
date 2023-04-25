package proy.MoisVictorv1.ErpFisioterapiav1.Repositorio;

import org.springframework.data.repository.CrudRepository;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Tarifas;


public interface TarifasRepositorio extends CrudRepository<Tarifas,String>  {

	Tarifas findByTipo (String id);
}
