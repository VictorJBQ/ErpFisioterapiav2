package proy.MoisVictorv1.ErpFisioterapiav1.Repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Ingresos;


public interface IngresosRepositorio extends CrudRepository<Ingresos,Integer> {

	Ingresos findById(String id);
	
	@Query("SELECT SUM(i.importe) FROM Ingresos i WHERE YEAR(i.fecha) = :year AND MONTH(i.fecha) = :month")
	Double sumImporteByYearAndMonth(@Param("month") Integer month,@Param("year") Integer year);
	
	
}
