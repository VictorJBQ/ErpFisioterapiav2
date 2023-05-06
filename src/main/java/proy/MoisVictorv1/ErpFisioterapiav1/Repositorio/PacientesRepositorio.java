package proy.MoisVictorv1.ErpFisioterapiav1.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Pacientes;

public interface PacientesRepositorio extends CrudRepository<Pacientes,Integer> {
	
	/*Consulta para traernos los pacientes que su fecha de alta fueron los mandados por parametros, y que conocio por referencia interna*/
	@Query("SELECT COUNT(p) FROM Pacientes p WHERE MONTH(p.fechaAlta) = :mes AND YEAR(p.fechaAlta) = :anio AND p.sabeDeMi='interna'")
	  Double contarPacientesINTERNOPorMesYAnio(@Param("mes") int mes, @Param("anio") int anio);
	
	/*Consulta para traernos los pacientes que su fecha de alta fueron los mandados por parametros, y que conocio por referencia externa*/
	@Query("SELECT COUNT(p) FROM Pacientes p WHERE MONTH(p.fechaAlta) = :mes AND YEAR(p.fechaAlta) = :anio AND p.sabeDeMi='externa'")
	  Double contarPacientesEXTERNOPorMesYAnio(@Param("mes") int mes, @Param("anio") int anio);
	
	/*Consulta para traernos los pacientes que su fecha de alta fueron los mandados por parametros, y que conocio por referencia situacion*/
	@Query("SELECT COUNT(p) FROM Pacientes p WHERE MONTH(p.fechaAlta) = :mes AND YEAR(p.fechaAlta) = :anio AND p.sabeDeMi='situacion'")
	  Double contarPacientesSITUACIONPorMesYAnio(@Param("mes") int mes, @Param("anio") int anio);
	
	/*contamos cuantas sesiones han sido con la tarifa amiga, filtrado fecha y mes*/
	@Query("SELECT COUNT(*) FROM Pacientes p JOIN p.citas c WHERE YEAR(c.fecha) = :year AND MONTH(c.fecha) = :month AND p.tarifas = 'amigos' AND c.estado IN ('terminada', 'salvada')")
	Double countCitasAmigos( @Param("month") Integer month,@Param("year") Integer year);
	
	/*contamos cuantas sesiones han sido con la tarifa amiga, filtrado fecha y mes*/
	@Query("SELECT COUNT(*) FROM Pacientes p JOIN citas c on p.id = c.pacientes WHERE YEAR(c.fecha) = :year AND MONTH(c.fecha) = :month AND p.tarifas = 'externa' AND c.estado IN ('terminada', 'salvada')")
	Double countCitasGeneral( @Param("month") Integer month,@Param("year") Integer year);
	
	
	/*contamos cuantas sesiones han sido con la tarifa amiga, filtrado fecha y mes*/
	@Query(" FROM Citas c WHERE YEAR(c.fecha) = :year AND MONTH(c.fecha) = :month  AND c.estado IN ('terminada', 'salvada')")
	List<Citas> countCitasGeneralTodas( @Param("month") Integer month,@Param("year") Integer year);
	
	/*paciente por DNI*/
	Pacientes findByDni(String dni);
	
	Pacientes findById(String dni);
	
	 @Query("SELECT p FROM Pacientes p WHERE p.codigoPostal IS NULL OR p.apellidos IS NULL OR p.dni IS NULL OR p.domicilio IS NULL OR p.poblacion IS NULL OR p.sabeDeMi IS NULL OR p.telefono IS NULL OR p.tarifas IS NULL")
	    List<Pacientes> buscarPacientesConCamposNulos();
	 
	 @Query("SELECT DISTINCT p FROM Pacientes p JOIN FETCH p.citas c WHERE YEAR(c.fecha) = YEAR(CURRENT_DATE)")
	    List<Pacientes> findPacientesWithCitasThisMonth();



}
