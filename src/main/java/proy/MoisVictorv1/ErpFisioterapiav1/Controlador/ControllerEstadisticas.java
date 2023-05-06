     package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.FacturasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.GastosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.IngresosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;

@Controller
public class ControllerEstadisticas {
	@Autowired
	CitasRepositorio citasRepositorio;
	
	@Autowired
	PacientesRepositorio pacientesRepositorio;
	
	@Autowired
	IngresosRepositorio ingresosRepositorio;
	
	@Autowired
	GastosRepositorio gastosRepositorio;
	
	@Autowired
	FacturasRepositorio facturasRepositorio;
	

	
	@RequestMapping(path="intranet/estadisticas/diasTrabajados")
	public String diasTrabajados(Model model) {
		
		return "intranet/estadisticas/diasTrabajados";
	}
	
	
	@GetMapping("api/comparar-genero")
	  
	  @ResponseBody public Map<String, Map<String, Integer>> compararGenero(@RequestParam String opcion1, @RequestParam String opcion2) {
	 
		 int mes=1;
		 int any1M=0;
		 int any2M=0;
	  Map<String, Map<String, Integer>> datos = new HashMap<>();
	  
	  Map<String,Integer> enero = new HashMap<>();
	  Map<String,Integer> febrero = new HashMap<>();
	  Map<String,Integer> marzo = new HashMap<>();
	  Map<String,Integer> abril = new HashMap<>();
	  Map<String,Integer> mayo = new HashMap<>();
	  Map<String,Integer> junio = new HashMap<>();
	  Map<String,Integer> julio = new HashMap<>();
	  Map<String,Integer> agosto = new HashMap<>();
	  Map<String,Integer> septiembre = new HashMap<>();
	  Map<String,Integer> octubre = new HashMap<>();
	  Map<String,Integer> noviembre = new HashMap<>();
	  Map<String,Integer> diciembre = new HashMap<>();
	 
	  
	  
	  List<Map<String,Integer>> lista= new ArrayList<Map<String,Integer>>();
	  lista.add(enero);
	  lista.add(febrero);
	  lista.add(marzo);
	  lista.add(abril);
	  lista.add(mayo);
	  lista.add(junio);
	  lista.add(julio);
	  lista.add(agosto);
	  lista.add(septiembre);
	  lista.add(octubre);
	  lista.add(noviembre);
	  lista.add(diciembre);
	 
	System.out.println(opcion1);
	  for(Map<String,Integer> enti: lista) {
		 
		  System.out.println(mes);
		  int any1= citasRepositorio.contarDiasDistintosPorMesYAnio(mes, Integer.parseInt(opcion1));
		  int any2= citasRepositorio.contarDiasDistintosPorMesYAnio(mes, Integer.parseInt(opcion2));
		  enti.put("anyo1", any1);
		  enti.put("anyo2", any2);
		  datos.put("mes"+mes, enti);
		  mes++;
		  
		  any1M+=any1;
		  any2M+=any2;
		  
	  }
	  Map<String,Integer> media= new HashMap<>();
	  media.put("anyo1", Double.valueOf(any1M/12).intValue());
	  media.put("anyo2", Double.valueOf(any2M/12).intValue());
	  datos.put("media", media);
	  
	  
	  return datos; }
	
	
	
	/*gastos totales*/
	@GetMapping("api/gastos-totales")
	  
	  @ResponseBody public Map<String, Map<String, Double>> gastosTotales(@RequestParam String opcion1, @RequestParam String opcion2) {
	 
		 int mes=1;
		 Double any1M=0.0;
		 Double any2M=0.0;
	  Map<String, Map<String, Double>> datos = new HashMap<>();
	  
	  Map<String,Double> enero = new HashMap<>();
	  Map<String,Double> febrero = new HashMap<>();
	  Map<String,Double> marzo = new HashMap<>();
	  Map<String,Double> abril = new HashMap<>();
	  Map<String,Double> mayo = new HashMap<>();
	  Map<String,Double> junio = new HashMap<>();
	  Map<String,Double> julio = new HashMap<>();
	  Map<String,Double> agosto = new HashMap<>();
	  Map<String,Double> septiembre = new HashMap<>();
	  Map<String,Double> octubre = new HashMap<>();
	  Map<String,Double> noviembre = new HashMap<>();
	  Map<String,Double> diciembre = new HashMap<>();
	 
	  
	  
	  List<Map<String,Double>> lista= new ArrayList<Map<String,Double>>();
	  lista.add(enero);
	  lista.add(febrero);
	  lista.add(marzo);
	  lista.add(abril);
	  lista.add(mayo);
	  lista.add(junio);
	  lista.add(julio);
	  lista.add(agosto);
	  lista.add(septiembre);
	  lista.add(octubre);
	  lista.add(noviembre);
	  lista.add(diciembre);
	 
	System.out.println(opcion1);
	  for(Map<String,Double> enti: lista) {
		 
		  System.out.println(mes);
		  Double any1= gastosRepositorio.sumImporteByYearAndMonth(mes, Integer.parseInt(opcion1));
		  Double any2= gastosRepositorio.sumImporteByYearAndMonth(mes, Integer.parseInt(opcion2));
		  
		  if(any1==null) {
			  any1=0.0;
		  }
		  if(any2==null) {
			  any2=0.0;
		  }
		  
		  System.out.println(any1);
		  enti.put("anyo1", any1);
		  enti.put("anyo2", any2);
		  datos.put("mes"+mes, enti);
		  mes++;
		  any1M+=any1;
		  any2M+=any2;
		  
	  }
	  Map<String,Double> media= new HashMap<>();
	  media.put("anyo1", Double.valueOf(any1M/12));
	  media.put("anyo2", Double.valueOf(any2M/12));
	  datos.put("media", media);
	  
	  Map<String,Double> total= new HashMap<>();
	  total.put("anyo1", any1M);
	  total.put("anyo2", any2M);
	  datos.put("total", total);
	  
	  
	  return datos; }
	
	
	/*Sesiones realizadas*/
	
	@GetMapping("api/sesiones-totales")
	  
	  @ResponseBody public Map<String, Map<String, Double>> sesionesTotales(@RequestParam String opcion1, @RequestParam String opcion2) {
	 
		 int mes=1;
		 Double any1M=0.0;
		 Double any2M=0.0;
	  Map<String, Map<String, Double>> datos = new HashMap<>();
	  
	  Map<String,Double> enero = new HashMap<>();
	  Map<String,Double> febrero = new HashMap<>();
	  Map<String,Double> marzo = new HashMap<>();
	  Map<String,Double> abril = new HashMap<>();
	  Map<String,Double> mayo = new HashMap<>();
	  Map<String,Double> junio = new HashMap<>();
	  Map<String,Double> julio = new HashMap<>();
	  Map<String,Double> agosto = new HashMap<>();
	  Map<String,Double> septiembre = new HashMap<>();
	  Map<String,Double> octubre = new HashMap<>();
	  Map<String,Double> noviembre = new HashMap<>();
	  Map<String,Double> diciembre = new HashMap<>();
	 
	  
	  
	  List<Map<String,Double>> lista= new ArrayList<Map<String,Double>>();
	  lista.add(enero);
	  lista.add(febrero);
	  lista.add(marzo);
	  lista.add(abril);
	  lista.add(mayo);
	  lista.add(junio);
	  lista.add(julio);
	  lista.add(agosto);
	  lista.add(septiembre);
	  lista.add(octubre);
	  lista.add(noviembre);
	  lista.add(diciembre);
	 
	System.out.println(opcion1);
	  for(Map<String,Double> enti: lista) {
		 
		  System.out.println(mes);
		  Double any1= citasRepositorio.contarTerminadaSalvadaMyA(mes, Integer.parseInt(opcion1));
		  Double any2= citasRepositorio.contarTerminadaSalvadaMyA(mes, Integer.parseInt(opcion2));
		  
		  if(any1==null) {
			  any1=0.0;
		  }
		  if(any2==null) {
			  any2=0.0;
		  }
		  
		  System.out.println(any1);
		  enti.put("anyo1", any1);
		  enti.put("anyo2", any2);
		  datos.put("mes"+mes, enti);
		  mes++;
		  any1M+=any1;
		  any2M+=any2;
		  
	  }
	  Map<String,Double> media= new HashMap<>();
	  media.put("anyo1", Double.valueOf(any1M/12));
	  media.put("anyo2", Double.valueOf(any2M/12));
	  datos.put("media", media);
	  
	  Map<String,Double> total= new HashMap<>();
	  total.put("anyo1", any1M);
	  total.put("anyo2", any2M);
	  datos.put("total", total);
	  
	  
	  return datos; }
	
	/*gastos totales*/
	@GetMapping("api/ingresos-totales")
	  
	  @ResponseBody public Map<String, Map<String, Double>> ingresosTotales(@RequestParam String opcion1, @RequestParam String opcion2) {
	 
		 int mes=1;
		 Double any1M=0.0;
		 Double any2M=0.0;
	  Map<String, Map<String, Double>> datos = new HashMap<>();
	  
	  Map<String,Double> enero = new HashMap<>();
	  Map<String,Double> febrero = new HashMap<>();
	  Map<String,Double> marzo = new HashMap<>();
	  Map<String,Double> abril = new HashMap<>();
	  Map<String,Double> mayo = new HashMap<>();
	  Map<String,Double> junio = new HashMap<>();
	  Map<String,Double> julio = new HashMap<>();
	  Map<String,Double> agosto = new HashMap<>();
	  Map<String,Double> septiembre = new HashMap<>();
	  Map<String,Double> octubre = new HashMap<>();
	  Map<String,Double> noviembre = new HashMap<>();
	  Map<String,Double> diciembre = new HashMap<>();
	 
	  
	  
	  List<Map<String,Double>> lista= new ArrayList<Map<String,Double>>();
	  lista.add(enero);
	  lista.add(febrero);
	  lista.add(marzo);
	  lista.add(abril);
	  lista.add(mayo);
	  lista.add(junio);
	  lista.add(julio);
	  lista.add(agosto);
	  lista.add(septiembre);
	  lista.add(octubre);
	  lista.add(noviembre);
	  lista.add(diciembre);
	 
	System.out.println(opcion1);
	  for(Map<String,Double> enti: lista) {
		 
		  System.out.println(mes);
		  Double any1= ingresosRepositorio.sumImporteByYearAndMonth(mes, Integer.parseInt(opcion1));
		  Double any2= ingresosRepositorio.sumImporteByYearAndMonth(mes, Integer.parseInt(opcion2));
		  
		  if(any1==null) {
			  any1=0.0;
		  }
		  if(any2==null) {
			  any2=0.0;
		  }
		  
		  System.out.println(any1);
		  enti.put("anyo1", any1);
		  enti.put("anyo2", any2);
		  datos.put("mes"+mes, enti);
		  mes++;
		  any1M+=any1;
		  any2M+=any2;
		  
	  }
	  Map<String,Double> media= new HashMap<>();
	  media.put("anyo1", Double.valueOf(any1M/12));
	  media.put("anyo2", Double.valueOf(any2M/12));
	  datos.put("media", media);
	  
	  Map<String,Double> total= new HashMap<>();
	  total.put("anyo1", any1M);
	  total.put("anyo2", any2M);
	  datos.put("total", total);
	  
	  
	  return datos; }
	
	
	@GetMapping("api/divison-pacientes")
	  
	  @ResponseBody public Map<String, Map<String, Integer>> divisionPacientes(@RequestParam String opcion1) {
	 
		 int mes=1;
		 int mediaAm=0;
		 int mediaCom=0;
		 int mediaNu=0;
		 int mediaTo=0;
		 
	  Map<String, Map<String, Integer>> datos = new HashMap<>();
	  
	  Map<String,Integer> enero = new HashMap<>();
	  Map<String,Integer> febrero = new HashMap<>();
	  Map<String,Integer> marzo = new HashMap<>();
	  Map<String,Integer> abril = new HashMap<>();
	  Map<String,Integer> mayo = new HashMap<>();
	  Map<String,Integer> junio = new HashMap<>();
	  Map<String,Integer> julio = new HashMap<>();
	  Map<String,Integer> agosto = new HashMap<>();
	  Map<String,Integer> septiembre = new HashMap<>();
	  Map<String,Integer> octubre = new HashMap<>();
	  Map<String,Integer> noviembre = new HashMap<>();
	  Map<String,Integer> diciembre = new HashMap<>();
	  
	  List<Map<String,Integer>> lista= new ArrayList<Map<String,Integer>>();
	  lista.add(enero);
	  lista.add(febrero);
	  lista.add(marzo);
	  lista.add(abril);
	  lista.add(mayo);
	  lista.add(junio);
	  lista.add(julio);
	  lista.add(agosto);
	  lista.add(septiembre);
	  lista.add(octubre);
	  lista.add(noviembre);
	  lista.add(diciembre);
	  
	  datos.put("mes1",new HashMap<>());
	  datos.put("mes2",new HashMap<>());
	  datos.put("mes3",new HashMap<>());
	  datos.put("mes4",new HashMap<>());
	  
	  datos.put("mes5",new HashMap<>());
	  datos.put("mes6",new HashMap<>());
	  datos.put("mes7",new HashMap<>());
	  datos.put("mes8",new HashMap<>());
	  
	  datos.put("mes9",new HashMap<>());
	  datos.put("mes10",new HashMap<>());
	  datos.put("mes11",new HashMap<>());
	  datos.put("mes12",new HashMap<>());
	 
	  for(Map<String,Integer> enti: lista) {
		  int nuevo=0;
		  int comunes=0;
		  int amigos=0;
		  int totales=0;
		 
		  List<Citas> citas=citasRepositorio.buscarCitasPorAnoYMesSalvadaTerminada(mes,Integer.parseInt(opcion1));
		  List<Integer> pacientesNuevos= new ArrayList<>();
		  
		  for(Citas c: citas) {
			  int nt =0;
			  int ct=0;
			  int at=0;
			  
			  System.out.println(c.getPacientes().getTarifas().getTipo());
			  if(c.getPacientes().getTarifas().getTipo().equals("amigos")) {
				  amigos++;
				  
				  at++;
			  }else if(!pacientesNuevos.contains(c.getPacientes().getId()) && c.getPacientes().getFechaAlta().getYear()==Integer.parseInt(opcion1) && 
					  c.getPacientes().getFechaAlta().getMonthValue()==mes) {
				  nuevo++;
				  nt++;
				  pacientesNuevos.add(c.getPacientes().getId());
				  	if(c.getPacientes().getCitas().size()>1) {
				  		comunes+=c.getPacientes().getCitas().size()-1;
				  		ct=c.getPacientes().getCitas().size()-1;
				  		
				  	}
				  
			  }else {
				  comunes++;
				  ct++;
			  }
			  totales+= at+ct+nt;
			 
			  mediaAm+=amigos;
			  mediaCom+=comunes;
			  mediaNu+=nuevo;
			  mediaTo+=totales;
			  
			 
		  }
		  System.out.println(totales+" del mes "+mes);
		  enti.put("nuevos", nuevo);
		  enti.put("comunes", comunes);
		  enti.put("amigos", amigos);
		  enti.put("totales", totales);
		  datos.put("mes"+mes, enti);
		  mes++;
		 
		  
	  }
	  Map<String,Integer> media= new HashMap<>();
	  media.put("nuevosm", Double.valueOf(mediaNu/12).intValue());
	  media.put("comunesm", Double.valueOf(mediaCom/12).intValue());
	  media.put("amigosm", Double.valueOf(mediaAm/12).intValue());
	  media.put("totalesm", Double.valueOf(mediaTo/12).intValue());
	  datos.put("media", media);
	  
	  
	  return datos; }
	
	
	@RequestMapping(path="intranet/estadisticas/distribucionSesiones")
	public String distribucionSesiones(Model model) {
		
		return "intranet/estadisticas/distribucionSesiones";
		
	}
	
	@GetMapping("api/distribucion-sesiones")
	  
	  @ResponseBody public Map<String, Map<String, Double>> distribucionSesiones(@RequestParam String opcion1) {
		 int mes=1;
		 Double cubiertasM=0.0;
		 Double fijasM=0.0;
		 Double extrasM=0.0;
		 Double libresM=0.0;
		 Double canceladasM=0.0;
		 Double salvadasM=0.0;
		  Map<String, Map<String, Double>> datos = new HashMap<>();
		  
		  Map<String,Double> enero = new HashMap<>();
		  Map<String,Double> febrero = new HashMap<>();
		  Map<String,Double> marzo = new HashMap<>();
		  Map<String,Double> abril = new HashMap<>();
		  Map<String,Double> mayo = new HashMap<>();
		  Map<String,Double> junio = new HashMap<>();
		  Map<String,Double> julio = new HashMap<>();
		  Map<String,Double> agosto = new HashMap<>();
		  Map<String,Double> septiembre = new HashMap<>();
		  Map<String,Double> octubre = new HashMap<>();
		  Map<String,Double> noviembre = new HashMap<>();
		  Map<String,Double> diciembre = new HashMap<>();
		 
		  
		  
		  List<Map<String,Double>> lista= new ArrayList<Map<String,Double>>();
		  lista.add(enero);
		  lista.add(febrero);
		  lista.add(marzo);
		  lista.add(abril);
		  lista.add(mayo);
		  lista.add(junio);
		  lista.add(julio);
		  lista.add(agosto);
		  lista.add(septiembre);
		  lista.add(octubre);
		  lista.add(noviembre);
		  lista.add(diciembre);
		 
		System.out.println(opcion1);
		  for(Map<String,Double> enti: lista) {
			 
			  System.out.println(mes);
			  Double cubiertas= citasRepositorio.contarCitasPorMesYAnioTS(mes, Integer.parseInt(opcion1));
			  Double fijas= citasRepositorio.contarCitasFijasPorMesYAnio(mes, Integer.parseInt(opcion1));
			  Double extras= citasRepositorio.contarCitasExtrasPorMesYAnio(mes, Integer.parseInt(opcion1));
			  Double libres= citasRepositorio.contarCitasLibresPorMesYAnio(mes, Integer.parseInt(opcion1));
			  Double canceladas= citasRepositorio.contarCitasCanceladasPorMesYAnio(mes, Integer.parseInt(opcion1));
			  Double salvadas= citasRepositorio.contarCitasSalvadasPorMesYAnio(mes, Integer.parseInt(opcion1));
			  
			  enti.put("cubiertas", cubiertas);
			  enti.put("fijas", fijas);
			  
			  enti.put("extras", extras);
			  enti.put("libres", libres);
			  
			  enti.put("canceladas", canceladas);
			  enti.put("salvadas", salvadas);
			  
			  datos.put("mes"+mes, enti);
			  mes++;
			  
			   cubiertasM+=cubiertas;
				  fijasM+=fijas;
				  extrasM+=extras;
				  libresM+=libres;
				  canceladasM+=canceladas;
				  salvadasM+=salvadas;
			  
		  }
		  Map<String,Double> media= new HashMap<>();
		  media.put("cubiertasm", Double.valueOf(cubiertasM/12));
		  media.put("fijasm", Double.valueOf(fijasM/12));
		  media.put("extrasm", Double.valueOf(extrasM/12));
		  media.put("libresm", Double.valueOf(libresM/12));
		  media.put("canceladasm", Double.valueOf(canceladasM/12));
		  media.put("salvadasm", Double.valueOf(salvadasM/12));
		  datos.put("media", media);
		  
		  
		  return datos; }
	
	@RequestMapping(path="intranet/estadisticas/divisionPacientes")
	public String divisionPacientes(Model model) {
	
		
		return "intranet/estadisticas/divisionPacientes";
	}
	
	@RequestMapping(path="intranet/estadisticas/gastosTotales")
	public String gastosTotales(Model model) {
		
		return "intranet/estadisticas/gastosTotales";
	}
	
	@RequestMapping(path="intranet/estadisticas/ingresosTotales")
	public String ingresosTotales(Model model) {
		
		return "intranet/estadisticas/ingresosTotales";
	}
	
	@RequestMapping(path="intranet/estadisticas/metodologiaPago")
	public String metodologiaPago(Model model) {
		
		return "intranet/estadisticas/metodologiaPago";
	}
	
	@RequestMapping(path="intranet/estadisticas/numeroPacientes")
	public String numeroPacientes(Model model) {
		
		return "intranet/estadisticas/numeroPacientes";
	}
	
	@RequestMapping(path="intranet/estadisticas/preciosSesiones")
	public String preciosSesiones(Model model) {
		
		return "intranet/estadisticas/preciosSesiones";
	}
	
	
	@GetMapping("api/precio-sesiones")
	  
	  @ResponseBody public Map<String, Map<String, Double>> precioSesiones(@RequestParam String opcion1) {
	 
		 int mes=1;
		 int reducido1M=0;
		 int generalM=0;
	  Map<String, Map<String, Double>> datos = new HashMap<>();
	  
	  Map<String,Double> enero = new HashMap<>();
	  Map<String,Double> febrero = new HashMap<>();
	  Map<String,Double> marzo = new HashMap<>();
	  Map<String,Double> abril = new HashMap<>();
	  Map<String,Double> mayo = new HashMap<>();
	  Map<String,Double> junio = new HashMap<>();
	  Map<String,Double> julio = new HashMap<>();
	  Map<String,Double> agosto = new HashMap<>();
	  Map<String,Double> septiembre = new HashMap<>();
	  Map<String,Double> octubre = new HashMap<>();
	  Map<String,Double> noviembre = new HashMap<>();
	  Map<String,Double> diciembre = new HashMap<>();
	 
	  
	  
	  List<Map<String,Double>> lista= new ArrayList<Map<String,Double>>();
	  lista.add(enero);
	  lista.add(febrero);
	  lista.add(marzo);
	  lista.add(abril);
	  lista.add(mayo);
	  lista.add(junio);
	  lista.add(julio);
	  lista.add(agosto);
	  lista.add(septiembre);
	  lista.add(octubre);
	  lista.add(noviembre);
	  lista.add(diciembre);
	 
	//System.out.println(opcion1);
	  for(Map<String,Double> enti: lista) {
		 
		  Double ge= 0.0;
		  Double red= 0.0;
		  
		  List<Citas> lt= pacientesRepositorio.countCitasGeneralTodas(mes, Integer.parseInt(opcion1));
		  
		  for(Citas ci: lt) {
			  if(ci.getPacientes().getTarifas().getTipo().equals("amigos")) {
				  red++;
			  }else if(ci.getPacientes().getTarifas().getTipo().equals("externa")) {
				  ge++;
			  }
		  }
		  
	System.out.println(ge+" mes "+mes);
		  enti.put("ge", ge);
		  enti.put("red", red);
		  datos.put("mes"+mes, enti);
		  mes++;
		  
		  reducido1M+=red;
		  generalM+=ge;
		  
	  }
	  Map<String,Double> media= new HashMap<>();
	  media.put("ge", Double.valueOf(generalM/12));
	  media.put("red", Double.valueOf(reducido1M/12));
	  datos.put("media", media);
	  
	  
	  return datos; }
	
	
	
	@RequestMapping(path="intranet/estadisticas/procedenciaPacientes")
	public String procedenciaPacientes(Model model) {
		
		return "intranet/estadisticas/procedenciaPacientes";
	}
	
	@GetMapping("api/procedencia-pacientes")
	  
	  @ResponseBody public Map<String, Map<String, Double>> procedenciaPacientes(@RequestParam String opcion1) {
		 int mes=1;
		 Double situacionM=0.0;
		 Double externaM=0.0;
		 Double internasM=0.0;
		  Map<String, Map<String, Double>> datos = new HashMap<>();
		  
		  Map<String,Double> enero = new HashMap<>();
		  Map<String,Double> febrero = new HashMap<>();
		  Map<String,Double> marzo = new HashMap<>();
		  Map<String,Double> abril = new HashMap<>();
		  Map<String,Double> mayo = new HashMap<>();
		  Map<String,Double> junio = new HashMap<>();
		  Map<String,Double> julio = new HashMap<>();
		  Map<String,Double> agosto = new HashMap<>();
		  Map<String,Double> septiembre = new HashMap<>();
		  Map<String,Double> octubre = new HashMap<>();
		  Map<String,Double> noviembre = new HashMap<>();
		  Map<String,Double> diciembre = new HashMap<>();
		 
		  
		  
		  List<Map<String,Double>> lista= new ArrayList<Map<String,Double>>();
		  lista.add(enero);
		  lista.add(febrero);
		  lista.add(marzo);
		  lista.add(abril);
		  lista.add(mayo);
		  lista.add(junio);
		  lista.add(julio);
		  lista.add(agosto);
		  lista.add(septiembre);
		  lista.add(octubre);
		  lista.add(noviembre);
		  lista.add(diciembre);
		 
		System.out.println(opcion1);
		  for(Map<String,Double> enti: lista) {
			 
			Double externa= pacientesRepositorio.contarPacientesEXTERNOPorMesYAnio(mes, Integer.parseInt(opcion1));
			Double interna= pacientesRepositorio.contarPacientesINTERNOPorMesYAnio(mes, Integer.parseInt(opcion1));
			Double situacion= pacientesRepositorio.contarPacientesSITUACIONPorMesYAnio(mes, Integer.parseInt(opcion1));
			
			System.out.println(situacion);
			  
			  enti.put("externa", externa);
			  enti.put("interna", interna);
			  
			  enti.put("situacion", situacion);
			
			  
			  datos.put("mes"+mes, enti);
			  mes++;
			  
			  externaM+=externa;
			  internasM+=interna;
			  situacionM+=situacion;
			
			  
		  }
		  Map<String,Double> media= new HashMap<>();
		  media.put("externam", Double.valueOf(externaM/12));
		  media.put("internam", Double.valueOf(internasM/12));
		  media.put("situacionm", Double.valueOf(situacionM/12));

		  datos.put("media", media);
		  
		  
		  return datos; }
	
	
	
	@GetMapping("api/forma-pagos")
	  
	  @ResponseBody public Map<String, Map<String, Double>> formaPagos(@RequestParam String opcion1) {
		 int mes=1;
		 Double externaM=0.0;
		 Double internasM=0.0;
		  Map<String, Map<String, Double>> datos = new HashMap<>();
		  
		  Map<String,Double> enero = new HashMap<>();
		  Map<String,Double> febrero = new HashMap<>();
		  Map<String,Double> marzo = new HashMap<>();
		  Map<String,Double> abril = new HashMap<>();
		  Map<String,Double> mayo = new HashMap<>();
		  Map<String,Double> junio = new HashMap<>();
		  Map<String,Double> julio = new HashMap<>();
		  Map<String,Double> agosto = new HashMap<>();
		  Map<String,Double> septiembre = new HashMap<>();
		  Map<String,Double> octubre = new HashMap<>();
		  Map<String,Double> noviembre = new HashMap<>();
		  Map<String,Double> diciembre = new HashMap<>();
		 
		  
		  
		  List<Map<String,Double>> lista= new ArrayList<Map<String,Double>>();
		  lista.add(enero);
		  lista.add(febrero);
		  lista.add(marzo);
		  lista.add(abril);
		  lista.add(mayo);
		  lista.add(junio);
		  lista.add(julio);
		  lista.add(agosto);
		  lista.add(septiembre);
		  lista.add(octubre);
		  lista.add(noviembre);
		  lista.add(diciembre);
		 
		System.out.println(opcion1);
		  for(Map<String,Double> enti: lista) {
			 
			Double externa= facturasRepositorio.countFacturasEfectivo(mes, Integer.parseInt(opcion1));
			Double interna= facturasRepositorio.countFacturasBizum(mes, Integer.parseInt(opcion1));
			
			  
			  enti.put("externa", externa);
			  enti.put("interna", interna);
			  
			
			  
			  datos.put("mes"+mes, enti);
			  mes++;
			  
			  externaM+=externa;
			  internasM+=interna;
			
			  
		  }
		  Map<String,Double> media= new HashMap<>();
		  media.put("externam", Double.valueOf(externaM/12));
		  media.put("internam", Double.valueOf(internasM/12));

		  datos.put("media", media);
		  
		  
		  return datos; }
	

}
