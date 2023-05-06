package proy.MoisVictorv1.ErpFisioterapiav1.Repositorio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
		 
		 @Query("SELECT c FROM Citas c WHERE c.fecha = :fecha AND FUNCTION('TIME_FORMAT', c.hora, '%H:%i') = :hora")
		    Citas findCitasByFechaAndHora(@Param("fecha") LocalDate fecha, @Param("hora") LocalTime hora);
		 
		 
		 List<Citas> findByFechaAndHora(LocalDate fecha, LocalTime hora);

		 
		 
		 /*Citas con fecha actual o superior*/
		 @Query("SELECT c FROM Citas c WHERE c.fecha >= current_date")
		    List<Citas> findByFechaActualOrFutura();
		 
		 /*Traenos las citas que estan pendiente de confirmar y queda un día para la cita*/
		 @Query("SELECT c FROM Citas c WHERE c.fecha = :fecha AND c.estado = 'pendiente-Confirmar'")
		    List<Citas> findByFechaMenosUnoYEstadoPendienteConfirmar(@Param("fecha") LocalDate fecha);
		 
		 
		 @Query("SELECT c FROM Citas c WHERE  c.estado = 'reservada'")
		    List<Citas> findByEstadoReservada();
		 
		 @Query("SELECT c FROM Citas c WHERE  c.estado IN ('confirmada', 'salvada-pendiente')")
		    List<Citas> findByEstadoConfirmada();
		 
		 @Query("SELECT COUNT(c) FROM Citas c WHERE  c.estado IN ('terminada', 'salvada')AND MONTH(c.fecha) = :mes AND YEAR(c.fecha) = :anio")
		 Double contarTerminadaSalvadaMyA(@Param("mes") int mes, @Param("anio") int anio);
		 
		 /*Fechas de cita en intervalos, para que no se solapen*/
		 @Query("SELECT c FROM Citas c WHERE c.fecha = :fecha AND ((c.hora >= :horaDesde1 AND c.hora <= :horaHasta1) OR (c.hora >= :horaDesde2 AND c.hora <= :horaHasta2))")
		    List<Citas> findCitasByFechaAndHoraBetweenTwoRanges(LocalDate fecha, LocalTime horaDesde1, LocalTime horaHasta1, LocalTime horaDesde2, LocalTime horaHasta2);
		 
		 
		 @Query("SELECT c FROM Citas c WHERE DATE(c.fecha) = CURRENT_DATE")
		  List<Citas> getCitasDiaActual();
		 
		 @Query("SELECT COUNT(c) FROM Citas c WHERE  c.estado IN ('terminada', 'salvada')AND c.empleados.identificador= :emp ")
		 Integer citasTotalEmpleado(@Param("emp") String emp);
		 
		 @Query("SELECT COUNT(c) FROM Citas c WHERE c.fecha >= CURRENT_DATE AND c.estado IN ('libre', 'cancelada') AND c.empleados.identificador= :id AND YEAR(c.fecha) = :year AND MONTH(c.fecha) = :month AND c.hora > CURRENT_TIME")
		 Integer citasMesLibEmpleado(@Param("id")String id,@Param("year") Integer year, @Param("month") Integer month);
		 
		 
		 /*citas canceladas*/
		 @Query("SELECT COUNT(c) FROM Citas c WHERE c.estado ='cancelada' AND c.empleados.identificador= :id AND YEAR(c.fecha) = :year AND MONTH(c.fecha) = :month")
		 Integer citasMesCanceladasEmpleado(@Param("id")String id,@Param("year") Integer year, @Param("month") Integer month);
		 
		 /*total citas del mes*/
		 /*citas canceladas*/
		 @Query("SELECT COUNT(c) FROM Citas c WHERE  c.empleados.identificador= :id AND YEAR(c.fecha) = :year AND MONTH(c.fecha) = :month")
		 Integer citasMesTotalladasEmpleado(@Param("id")String id,@Param("year") Integer year, @Param("month") Integer month);
		 
		 /*Citas del mes terminadas*/
		 @Query("SELECT COUNT(c) FROM Citas c WHERE c.estado IN ('terminada', 'salvada') AND c.empleados.identificador= :id AND YEAR(c.fecha) = :year AND MONTH(c.fecha) = :month")
		 Integer citasMesTermidasEmpleado(@Param("id")String id,@Param("year") Integer year, @Param("month") Integer month);
		
}
