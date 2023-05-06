package proy.MoisVictorv1.ErpFisioterapiav1.Repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Facturas;

public interface FacturasRepositorio extends CrudRepository<Facturas,Integer> {
	
	/*contamos cuantas sesiones han sido pagadas en efectivo*/
	@Query("SELECT COUNT(*) FROM Facturas p WHERE YEAR(p.fecha) = :year AND MONTH(p.fecha) = :month AND p.formaPago LIKE '%Efectivo%'")
	Double countFacturasEfectivo( @Param("month") Integer month,@Param("year") Integer year);
	
	/*contamos cuantas sesiones han sido pagadas en efectivo*/
	@Query("SELECT COUNT(*) FROM Facturas p WHERE YEAR(p.fecha) = :year AND MONTH(p.fecha) = :month AND p.formaPago LIKE '%Bizum%'")
	Double countFacturasBizum( @Param("month") Integer month,@Param("year") Integer year);
	

}
