package proy.MoisVictorv1.ErpFisioterapiav1.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;


public interface CitasRepositorio extends CrudRepository<Citas,Integer>{
	
	
	@Query("SELECT COUNT(c) FROM Citas c WHERE MONTH(c.fecha) = :mes AND YEAR(c.fecha) = :anio")
	  int contarCitasPorMesYAnio(@Param("mes") int mes, @Param("anio") int anio);
	 
	 /*Consulta días trabajados por mes y año */
	@Query("SELECT COUNT(DISTINCT DAY(c.fecha)) FROM Citas c WHERE MONTH(c.fecha) = :mes AND YEAR(c.fecha) = :anio")
	  int contarDiasDistintosPorMesYAnio(@Param("mes") int mes, @Param("anio") int anio);


	  List<Citas> findByEstadoContaining(String estado);
	  
	  Citas  findById(String id);
	  
	  /*Consulta que nos traemos las citas que esten en pendientes o canceladas, que no sean inferior a la fecha actual y hora actual +1*/
	   List<Citas> findByEstadoIn(List<String> estados);
	   
	   
	   /*Consulta traenos las citas x año y mes que esten terminadas o salvadas*/
	   @Query("SELECT c FROM Citas c WHERE c.estado IN ('terminada', 'salvada') AND YEAR(c.fecha) = :anio AND MONTH(c.fecha) = :mes")
	   List<Citas> buscarCitasPorAnoYMesSalvadaTerminada(@Param("mes") int mes, @Param("anio") int anio);
	   
	   
	   /*Consulta citas dadas trabajadas por mes y año*/
	   @Query("SELECT COUNT(c) FROM Citas c WHERE c.estado IN ('terminada', 'salvada') AND YEAR(c.fecha) = :anio AND MONTH(c.fecha) = :mes")
	   Double contarCitasPorMesYAnioTS(@Param("mes") int mes, @Param("anio") int anio);
		 
		 @Query("SELECT COUNT(c) FROM Citas c WHERE c.tipo = 'fija' AND MONTH(c.fecha) = :mes AND YEAR(c.fecha) = :anio")
		  Double contarCitasFijasPorMesYAnio(@Param("mes") int mes, @Param("anio") int anio);
		 
		 /*citas extra*/
		 @Query("SELECT COUNT(c) FROM Citas c WHERE c.tipo = 'extra' AND MONTH(c.fecha) = :mes AND YEAR(c.fecha) = :anio")
		  Double contarCitasExtrasPorMesYAnio(@Param("mes") int mes, @Param("anio") int anio);
		 
		 /*citas libres*/
		 @Query("SELECT COUNT(c) FROM Citas c  WHERE c.estado = 'libre' AND MONTH(c.fecha) = :mes AND YEAR(c.fecha) = :anio")
		  Double contarCitasLibresPorMesYAnio(@Param("mes") int mes, @Param("anio") int anio);
		 
		 /*citas canceladas*/
		 @Query("SELECT COUNT(c) FROM Citas c WHERE c.estado = 'cancelada' AND MONTH(c.fecha) = :mes AND YEAR(c.fecha) = :anio")
		  Double contarCitasCanceladasPorMesYAnio(@Param("mes") int mes, @Param("anio") int anio);
		 
		 /*citas salvadas*/
		 @Query("SELECT COUNT(c) FROM Citas c WHERE c.estado = 'salvada' AND MONTH(c.fecha) = :mes AND YEAR(c.fecha) = :anio")
		  Double contarCitasSalvadasPorMesYAnio(@Param("mes") int mes, @Param("anio") int anio);
		
}
