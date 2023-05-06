package proy.MoisVictorv1.ErpFisioterapiav1.Repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Gastos;

public interface GastosRepositorio extends CrudRepository<Gastos,Integer> {
	Gastos findById(String id);
	
	@Query("SELECT SUM(i.importe) FROM Gastos i WHERE YEAR(i.fecha) = :year AND MONTH(i.fecha) = :month")
	Double sumImporteByYearAndMonth(@Param("month") Integer month,@Param("year") Integer year);
}
