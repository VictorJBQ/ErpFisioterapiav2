package proy.MoisVictorv1.ErpFisioterapiav1.Controlador;

import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm.ExcelCitaHoyForm;
import proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm.ExcelCitaPenConfirmarForm;
import proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm.ExcelEmpleadoForm;
import proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm.ExcelFacturaForm;
import proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm.ExcelGastosForm;
import proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm.ExcelIngresosForm;
import proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm.ExcelPacientesForm;
import proy.MoisVictorv1.ErpFisioterapiav1.ExcelForm.ExcelTarifasForm;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Citas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Empleados;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Facturas;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Gastos;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Ingresos;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Pacientes;
import proy.MoisVictorv1.ErpFisioterapiav1.Model.Tarifas;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.CitasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.EmpleadosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.FacturasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.GastosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.IngresosRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.PacientesRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.TarifasRepositorio;
import proy.MoisVictorv1.ErpFisioterapiav1.Utilidades.DescargaExcel;

@Controller
public class ControllerExcel {
    
	@Autowired
	CitasRepositorio citasRepositorio;

	@Autowired
	PacientesRepositorio pacientesRepositorio;

	@Autowired
	EmpleadosRepositorio empleadosRepositorio;
	
	@Autowired
	FacturasRepositorio facturasRepositorio;
	
	@Autowired
	IngresosRepositorio ingresosRepositorio;
	
	@Autowired
	TarifasRepositorio tarifasRepositorio;
	@Autowired
	GastosRepositorio gastosRespositorio;
    
    @PostMapping(value="descargarEmpleados-excel")
    public void descargarEmpleadosExcel(HttpServletResponse response,@Param("nombre") String nombre) throws Exception {
    	List<Empleados> aa =(List<Empleados>) empleadosRepositorio.findAll();
        List<ExcelEmpleadoForm> lista = new ArrayList<ExcelEmpleadoForm>();
       if(!aa.isEmpty()) {
    	   for(Empleados a : aa) {
    		   lista.add(new ExcelEmpleadoForm(a.getIdentificador(), a.getNombre(),a.getEmail(), a.getRoles().getTipo()));
    	   }
       DescargaExcel descargaExcel = new DescargaExcel();
	descargaExcel.descargarExcel(response, lista,nombre);
	}else {
		response.sendRedirect("empleados/altaEmpleados");
	}
       
    }
    
    @PostMapping(value="descargarCitasHoy-excel")
    public void descargarCitasHoyExcel(HttpServletResponse response, @Param("nombre") String nombre) throws Exception {
       DescargaExcel descargaExcel = new DescargaExcel();
      List<Citas> a = (List<Citas>) citasRepositorio.getCitasDiaActual();
      List<ExcelCitaHoyForm> aa = new ArrayList<ExcelCitaHoyForm>();
     if(!a.isEmpty()) {

	for(Citas a1 : a) {
		String nombrea = "";
		if(a1.getPacientes()==null) {
			nombrea ="NA";
		}
		else {
			nombrea = a1.getPacientes().getTelefono()+"-"+a1.getPacientes().getNombre();
		}
		aa.add(new ExcelCitaHoyForm(a1.getHora().toString(), a1.getEstado(), nombrea));
		
	}
	 descargaExcel.descargarExcel(response, aa,nombre);
	}else {
		response.sendRedirect("intranet/inicio");
	}
	 


      }
    @PostMapping(value="descargarCitaConfirmar-excel")
    public void descargarCitaConfirmarExcel(HttpServletResponse response,@Param("nombre") String nombre) throws Exception {
    	  LocalDate fecha = LocalDate.now().plusDays(1);
    	List<Citas> aa =(List<Citas>) citasRepositorio.findByFechaMenosUnoYEstadoPendienteConfirmar(fecha);
        List<ExcelCitaPenConfirmarForm> lista = new ArrayList<ExcelCitaPenConfirmarForm>();
       if(!aa.isEmpty()) {
    	   for(Citas a1 : aa) {
    		   String nombrea = "";
    			if(a1.getPacientes()==null) {
    				nombrea ="NA";
    			}
    			else {
    				nombrea = a1.getPacientes().getTelefono()+"-"+a1.getPacientes().getNombre();
    			}
    		   lista.add(new ExcelCitaPenConfirmarForm(a1.getFecha().toString(), a1.getHora().toString(), a1.getEstado(),nombrea ,a1.getTipo()));
    	   }
       DescargaExcel descargaExcel = new DescargaExcel();
	descargaExcel.descargarExcel(response, lista,nombre);
	}else {
		response.sendRedirect("intranet/citas/citas");
	}

       
    }
    
    @PostMapping(value="descargarCitaActualizar-excel")
    public void descargarCitaActualizarExcel(HttpServletResponse response,@Param("nombre") String nombre) throws Exception {
    	List<Citas> aa =(List<Citas>) citasRepositorio.findByEstadoReservada();
        List<ExcelCitaPenConfirmarForm> lista = new ArrayList<ExcelCitaPenConfirmarForm>();
        if(!aa.isEmpty()) {
     	   for(Citas a1 : aa) {
     		   String nombrea = "";
     			if(a1.getPacientes()==null) {
     				nombrea ="NA";
     			}
     			else {
     				nombrea = a1.getPacientes().getTelefono()+"-"+a1.getPacientes().getNombre();
     			}
     		   lista.add(new ExcelCitaPenConfirmarForm(a1.getFecha().toString(), a1.getHora().toString(), a1.getEstado(),nombrea ,a1.getTipo()));
     	   }
        DescargaExcel descargaExcel = new DescargaExcel();
 	descargaExcel.descargarExcel(response, lista,nombre);
 	}else {
 		response.sendRedirect("intranet/citas/citas");
 	}

       
    }
    
    @PostMapping(value="descargarCitaTerminar-excel")
    public void descargarCitaTerminarExcel(HttpServletResponse response,@Param("nombre") String nombre) throws Exception {
    	List<Citas> aa =(List<Citas>)citasRepositorio.findByEstadoConfirmada();
        List<ExcelCitaPenConfirmarForm> lista = new ArrayList<ExcelCitaPenConfirmarForm>();
        if(!aa.isEmpty()) {
     	   for(Citas a1 : aa) {
     		   String nombrea = "";
     			if(a1.getPacientes()==null) {
     				nombrea ="NA";
     			}
     			else {
     				nombrea = a1.getPacientes().getTelefono()+"-"+a1.getPacientes().getNombre();
     			}
     		   lista.add(new ExcelCitaPenConfirmarForm(a1.getFecha().toString(), a1.getHora().toString(), a1.getEstado(),nombrea ,a1.getTipo()));
     	   }
        DescargaExcel descargaExcel = new DescargaExcel();
 	descargaExcel.descargarExcel(response, lista,nombre);
 	}else {
 		response.sendRedirect("intranet/citas/citas");
 	}

       
    }
    
    @PostMapping(value="descargarCitaActualidad-excel")
    public void descargarCitaActualidadExcel(HttpServletResponse response,@Param("nombre") String nombre) throws Exception {
    	List<Citas> aa =(List<Citas>)citasRepositorio.findByFechaActualOrFutura();
        List<ExcelCitaPenConfirmarForm> lista = new ArrayList<ExcelCitaPenConfirmarForm>();
        if(!aa.isEmpty()) {
     	   for(Citas a1 : aa) {
     		   String nombrea = "";
     			if(a1.getPacientes()==null) {
     				nombrea ="NA";
     			}
     			else {
     				nombrea = a1.getPacientes().getTelefono()+"-"+a1.getPacientes().getNombre();
     			}
     		   lista.add(new ExcelCitaPenConfirmarForm(a1.getFecha().toString(), a1.getHora().toString(), a1.getEstado(),nombrea ,a1.getTipo()));
     	   }
        DescargaExcel descargaExcel = new DescargaExcel();
 	descargaExcel.descargarExcel(response, lista,nombre);
 	}else {
 		response.sendRedirect("intranet/citas/citas");
 	}

       
    }
    @PostMapping(value="descargarCitas-excel")
    public void descargarCitas(HttpServletResponse response,@Param("nombre") String nombre) throws Exception {
    	List<Citas> aa =(List<Citas>)citasRepositorio.findAll();
        List<ExcelCitaPenConfirmarForm> lista = new ArrayList<ExcelCitaPenConfirmarForm>();
        if(!aa.isEmpty()) {
     	   for(Citas a1 : aa) {
     		   String nombrea = "";
     			if(a1.getPacientes()==null) {
     				nombrea ="NA";
     			}
     			else {
     				nombrea = a1.getPacientes().getTelefono()+"-"+a1.getPacientes().getNombre();
     			}
     		   lista.add(new ExcelCitaPenConfirmarForm(a1.getFecha().toString(), a1.getHora().toString(), a1.getEstado(),nombrea ,a1.getTipo()));
     	   }
        DescargaExcel descargaExcel = new DescargaExcel();
 	descargaExcel.descargarExcel(response, lista,nombre);
 	}else {
 		response.sendRedirect("intranet/citas/altaCitas");
 	}

       
    }
    @PostMapping(value="descargarFacturas-excel")
    public void descargarFacturas(HttpServletResponse response,@Param("nombre") String nombre) throws Exception {
    	List<Facturas> aa =(List<Facturas>)facturasRepositorio.findAll();
        List<ExcelFacturaForm> lista = new ArrayList<ExcelFacturaForm>();
        if(!aa.isEmpty()) {
     	   for(Facturas a1 : aa) {
     		   String nombrea = "";
     			if(a1.getCita().getPacientes()==null) {
     				nombrea ="NA";
     			}
     			else {
     				nombrea = a1.getCita().getPacientes().getTelefono()+"-"+a1.getCita().getPacientes().getNombre();
     			}
     		   lista.add(new ExcelFacturaForm(a1.getFormaPago(), a1.getImporte().toString(),nombrea ,a1.getFecha().toString()));
     	   }
        DescargaExcel descargaExcel = new DescargaExcel();
 	descargaExcel.descargarExcel(response, lista,nombre);
 	}else {
 		response.sendRedirect("intranet/facturas/facturas");
 	}
    }
    @PostMapping(value="descargarIngresos-excel")
    public void descargarIngresos(HttpServletResponse response,@Param("nombre") String nombre) throws Exception {
    	List<Ingresos> aa =(List<Ingresos>)ingresosRepositorio.findAll();
        List<ExcelIngresosForm> lista = new ArrayList<ExcelIngresosForm>();
        if(!aa.isEmpty()) {
     	   for(Ingresos a1 : aa) {
     		   String nombrea = "";
     		   Integer id = (Integer) null;
     			if(a1.getFactura()==null) {
     				nombrea ="NA";
     			}
     			else {
     				id = a1.getFactura().getId();
     				nombrea= id.toString();
     			}
     		   lista.add(new ExcelIngresosForm(a1.getImporte().toString(), a1.getTipo(),nombrea ,a1.getFecha().toString()));
     	   }
        DescargaExcel descargaExcel = new DescargaExcel();
 	descargaExcel.descargarExcel(response, lista,nombre);
 	}else {
 		response.sendRedirect("intranet/ingresos/ingresos");
 	}
    }
    
    @PostMapping(value="descargarGastos-excel")
    public void descargarGastos(HttpServletResponse response,@Param("nombre") String nombre) throws Exception {
    	List<Gastos> aa =(List<Gastos>)gastosRespositorio.findAll();
        List<ExcelGastosForm> lista = new ArrayList<ExcelGastosForm>();
        if(!aa.isEmpty()) {
     	   for(Gastos a1 : aa) {
     		  
     		   lista.add(new ExcelGastosForm(a1.getFecha().toString(), a1.getImporte().toString() ,a1.getTipo()));
     	   }
        DescargaExcel descargaExcel = new DescargaExcel();
 	descargaExcel.descargarExcel(response, lista,nombre);
 	}else {
 		response.sendRedirect("intranet/gastos/gastos");
 	}
    }
    @PostMapping(value="descargarTarifas-excel")
    public void descargarTarifas(HttpServletResponse response,@Param("nombre") String nombre) throws Exception {
    	List<Tarifas> aa =(List<Tarifas>)tarifasRepositorio.findAll();
        List<ExcelTarifasForm> lista = new ArrayList<ExcelTarifasForm>();
        if(!aa.isEmpty()) {
     	   for(Tarifas a1 : aa) {
     		  
     		   lista.add(new ExcelTarifasForm(a1.getTipo(), a1.getPrecio().toString()));
     	   }
        DescargaExcel descargaExcel = new DescargaExcel();
 	descargaExcel.descargarExcel(response, lista,nombre);
 	}else {
 		response.sendRedirect("intranet/tarifas/tarifas");
 	}
    }
    @PostMapping(value="descargarPacientesTodos-excel")
    public void descargarPacientesTodos(HttpServletResponse response,@Param("nombre") String nombre) throws Exception {
    	List<Pacientes> aa =(List<Pacientes>)pacientesRepositorio.findAll();
        List<ExcelPacientesForm> lista = new ArrayList<ExcelPacientesForm>();
        if(!aa.isEmpty()) {
     	   for(Pacientes a1 : aa) {
     		  Integer t= a1.getTelefono();
     		  String tel = t.toString();
     		   lista.add(new ExcelPacientesForm(a1.getNombre(), a1.getApellidos(),a1.getDni(),a1.getFechaAlta().toString(),a1.getDomicilio(),a1.getCodigoPostal(),a1.getPoblacion(),tel,a1.getSabeDeMi(),a1.getTarifas().getTipo()));
     	   }
        DescargaExcel descargaExcel = new DescargaExcel();
 	descargaExcel.descargarExcel(response, lista,nombre);
 	}else {
 		response.sendRedirect("intranet/pacientes/pacientes");
 	}
        
    }
    @PostMapping(value="descargarPacientesPendientes-excel")
    public void descargarPacientesPendientes(HttpServletResponse response,@Param("nombre") String nombre) throws Exception {
    	List<Pacientes> aa =(List<Pacientes>)pacientesRepositorio.buscarPacientesConCamposNulos();
        List<ExcelPacientesForm> lista = new ArrayList<ExcelPacientesForm>();
        if(!aa.isEmpty()) {
     	   for(Pacientes a1 : aa) {
     		  Integer t= a1.getTelefono();
     		  String tel = t.toString();
     		   lista.add(new ExcelPacientesForm(a1.getNombre(), a1.getApellidos(),a1.getDni(),a1.getFechaAlta().toString(),a1.getDomicilio(),a1.getCodigoPostal(),a1.getPoblacion(),tel,a1.getSabeDeMi(),a1.getTarifas().getTipo()));
     	   }
        DescargaExcel descargaExcel = new DescargaExcel();
 	descargaExcel.descargarExcel(response, lista,nombre);
 	}else {
 		response.sendRedirect("intranet/pacientes/pacientes");
 	}
        
    }
    
    @PostMapping(value="descargarPacientesCurso-excel")
    public void descargarPacientesCurso(HttpServletResponse response,@Param("nombre") String nombre) throws Exception {
    	List<Pacientes> aa =(List<Pacientes>)pacientesRepositorio.findPacientesWithCitasThisMonth();
        List<ExcelPacientesForm> lista = new ArrayList<ExcelPacientesForm>();
        if(!aa.isEmpty()) {
     	   for(Pacientes a1 : aa) {
     		  Integer t= a1.getTelefono();
     		  String tel = t.toString();
     		   lista.add(new ExcelPacientesForm(a1.getNombre(), a1.getApellidos(),a1.getDni(),a1.getFechaAlta().toString(),a1.getDomicilio(),a1.getCodigoPostal(),a1.getPoblacion(),tel,a1.getSabeDeMi(),a1.getTarifas().getTipo()));
     	   }
        DescargaExcel descargaExcel = new DescargaExcel();
 	descargaExcel.descargarExcel(response, lista,nombre);
 	}else {
 		response.sendRedirect("intranet/pacientes/pacientes");
 	}
        
    }
    
   }
    


