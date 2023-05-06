function mostrarCampos(valor) {
	var pendientes = document.getElementById("tablaPendientes");
	var tablaTodos = document.getElementById("tablaTodos");
	var hoy = document.getElementById("tablaHoy+");
	if (valor=='pendiente') {
		pendientes.style.display = "block";
		tablaTodos.style.display = "none";
		hoy.style.display = "none";
	} else if (valor=='todos') {
		pendientes.style.display = "none";
		tablaTodos.style.display = "block";
		hoy.style.display = "none";
	
}else if (valor=='hoy') {
		pendientes.style.display = "none";
		tablaTodos.style.display = "none";
		hoy.style.display = "block";
	
	} 
}
var modaladd;
var modaledit;
var modalasig;

var app = {

	/** creo una variable que contenga la url donde hace la llamada, que lo que hace es iniciar la tabla
	 * la tabla la inicia en la funcion init que es llamada y le pasa el id de la tabla
	 */
	backend: '/api-pct',
	table: null,
	table2:null,
	table3:null,
	init: function() {
		app.initDatatable('#pacientes');
		app.initDatatable2('#pendientes');
		app.initDatatable3('#hoy');
		$("#editar").click(function() {
			
			app.editar({
				idE: $('#idE').val(),
				nombreE: $('#nombreE').val(),
				apellidosE: $('#apellidosE').val(),
				dniE: $('#dniE').val(),
				dniViejoE: $('#dniViejoE').val(),
				domicilioE: $('#domicilioE').val(),
				cpE: $('#cpE').val(),
				poblaE: $('#poblaE').val(),
				telE: $('#telE').val(),
				tarifaE: $('#tarifaE').val(),
				conoceE: $('#conoceE').val(),
				
				
				
			});
		});
		
		
		
		$("#asignar").click(function() {
		
			app.asignar({
				idCita: $('#citaDar').val(),
				idPaciente: $('#idCitar').val(),
				
				
				
				
				
			});
		});


		$("#guardar").click(function() {

			app.guardar({
				nombreA: $('#nombreA').val(),
				apellidosA: $('#apellidosA').val(),
				dniA: $('#dniA').val(),
				domicilioA: $('#domicilioA').val(),
				cpA: $('#cpA').val(),
				poblaA: $('#poblaA').val(),
				telA: $('#telA').val(),
				tarifaA: $('#tarifaA').val(),
				conoceA: $('#conoceA').val(),
			});
		});

	/**Cierre modal : Cuando los madales se cierren, ponemos los input a ' ' y quitamos el inalid */
		$('#crearPaci').on('hidden.bs.modal', function() {
			$(this).find('input').removeClass('is-invalid');
			$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
				 $('#nombreA').val(''),
				 $('#apellidosA').val(''),
				 $('#dniE').val(''),
				 $('#domicilioA').val(''),
				 $('#cpA').val(''),
				 $('#poblaA').val(''),
				 $('#telA').val(''),
				 $('#conoceA').val('')
		});
		
		$('#editarPaci').on('hidden.bs.modal', function() {
			$(this).find('input').removeClass('is-invalid');
			$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
		});

/**Fin cierre modal */


/**Escribir input: cuando el campo input no esta vacío se quita el invalid */

	
		
		$('#nombreA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#apellidosA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		
		$('#dniA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
	
		$('#domicilioA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#cpA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#poblaA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#telA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		$('#conoceA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#nombreE').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#apellidosE').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		
		$('#dniE').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
	
		$('#domicilioE').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#cpE').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#poblaE').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#telE').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		$('#conoceE').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
	/**FIN esctibir */	
		


	},
	initDatatable: function(id) {
		/**Esto se encarfa de crear la tabla cargando el json que recibe de la bbdd 
		 * que lo recibe en formato json, lo retornas a la llamada etc
		 */
			
		
		app.table = $(id).DataTable({

			ajax: {
				url: app.backend + '/all',
				dataSrc: function(json) {
					console.log(json)
					return json;
				}
			},
			dom: 'Bfrtip',
			columns: [
					{
					// Agregar una columna para el botón Editar
					data: null,
					render: function(data, type, row) {
						return '<button type="button" class="btn btn-smD btn-outline-primary editar"><i class="bi bi-plus-circle"></i></i>' +
							'</button>';
					},
					orderable: false,
					searchable: false
				},
					{
					// Agregar una columna para el botón Editar
					data: null,
					render: function(data, type, row) {
						return '<button type="button" class="btn btn-sm btn-outline-primary editar" ><i class="bi bi-pencil-fill"></i>' +
							'</button>&nbsp;<button class="btn btn-danger btn-sm2" data-id="' + row.id +
							'"><i class="bi bi-trash-fill"></i></i></button>';
					},
					orderable: false,
					searchable: false
				},
				
				{ data: "nombre" },
				{ data: "apellidos", render: function (data) {  return data || "N/A"; } },
				{ data: "dni", render: function (data) {  return data || "N/A"; } },
				{ data: "fechaAlta" },
				{ data: "domicilio", render: function (data) {  return data || "N/A"; } },
				{ data: "codigoPostal", render: function (data) {  return data || "N/A"; } },
				{ data: "poblacion", render: function (data) {  return data || "N/A"; } },
				{ data: "telefono" },
				{ data: "sabeDeMi", render: function (data) {  return data || "N/A"; } },
				{ data: "tarifas.tipo", render: function (data) {  return data || "N/A"; } },
			
			
			],
			buttons: [
				{
					text: 'Nuevo paciente',
					action: function() {
						confirmarTodo();
					}
				}
			],
				responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.childRowImmediate,
                type: 'none',
                target: ''
            }
        }
		});

		// Agregar una acción para el botón Editar
		$('#pacientes tbody').on('click', '.btn-sm', function() {
			 $("#msg").text('').removeClass('alert')
			var data = app.table.row($(this).parents('tr')).data();
			app.setDataToModal(data);
			
			var myModalEl = document.getElementById('pacienteEditar')
			modaledit = new bootstrap.Modal(myModalEl)
			modaledit.show();
			
			
			//$('#pacienteEditar').modal();
		});
		
			// Agregar una acción para el botón citar
		$('#pacientes tbody').on('click', '.btn-smD', function() {
			 $("#msg").text('').removeClass('alert')
			var data = app.table.row($(this).parents('tr')).data();
				if (data.tarifas && data.tarifas.tipo !== null) {
 					 $('#tarifaE').val(data.tarifas.tipo);
						}
			app.setDataToModal2(data);
		//	$('#pacienteCitar').modal();
			var myModalEl = document.getElementById('pacienteCitar')
			modalasig = new bootstrap.Modal(myModalEl)
			modalasig.show();
			$('.select2').select2({
        	dropdownParent: $('#pacienteCitar .modal-body')
            });
		});
		
		// Agregar una acción para el botón eliminar
		$('#pacientes tbody').on('click', '.btn-sm2', function() {
			 $("#msg").text('').removeClass('alert')
			var data = app.table.row($(this).parents('tr')).data();
			app.eliminar(data.id)
			
		});

		
		/**Para crear registros nuevos */
		function confirmarTodo() {
			 $("#msg").text('').removeClass('alert')
			 	var myModalEl = document.getElementById('crearPaci')
			 modaladd = new bootstrap.Modal(myModalEl)
			modaladd.show();
			



		}




		/** Esta función de aqui es para hacer la celda seleccionada o no  */
		$('#pacientes tbody').on('click', 'tr', function() {
			if ($(this).hasClass('table-active')) {
				$(this).removeClass('table-active');
				$(this).css('background-color', null);
			} else {
				app.table.$('tr.table-active').removeClass('table-active');
				$(this).addClass('table-active');

			}
		});


	},
	
		initDatatable2: function(id) {
		/**Esto se encarfa de crear la tabla cargando el json que recibe de la bbdd 
		 * que lo recibe en formato json, lo retornas a la llamada etc
		 */
			
		
		app.table2 = $(id).DataTable({

			ajax: {
				url: app.backend + '/allpendi',
				dataSrc: function(json) {
					console.log(json)
					return json;
				}
			},
			dom: 'Bfrtip',
			columns: [
						{
					// Agregar una columna para el botón Editar
					data: null,
					render: function(data, type, row) {
						return '<button type="button" class="btn btn-smDP btn-outline-primary editar"><i class="bi bi-plus-circle"></i></i>' +
							'</button>';
					},
					orderable: false,
					searchable: false
				},
					{
					// Agregar una columna para el botón Editar
					data: null,
					render: function(data, type, row) {
						return '<button type="button" class="btn btn-smP btn-outline-primary editar" ><i class="bi bi-pencil-fill"></i>' +
							'</button>&nbsp;<button class="btn btn-danger btn-sm2P" data-id="' + row.id +
							'"><i class="bi bi-trash-fill"></i></i></button>';
					},
					orderable: false,
					searchable: false
				},
				
				{ data: "nombre" },
				{ data: "apellidos", render: function (data) {  return data || "N/A"; } },
				{ data: "dni", render: function (data) {  return data || "N/A"; } },
				{ data: "fechaAlta" },
				{ data: "domicilio", render: function (data) {  return data || "N/A"; } },
				{ data: "codigoPostal", render: function (data) {  return data || "N/A"; } },
				{ data: "poblacion", render: function (data) {  return data || "N/A"; } },
				{ data: "telefono" },
				{ data: "sabeDeMi", render: function (data) {  return data || "N/A"; } },
				{ data: "tarifas.tipo", render: function (data) {  return data || "N/A"; } },
			
		
			],
			buttons: [
				
			],
				responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.childRowImmediate,
                type: 'none',
                target: ''
            }
        }
		});

		// Agregar una acción para el botón Editar
		$('#Pendientes tbody').on('click', '.btn-smP', function() {
			 $("#msg").text('').removeClass('alert')
			var data = app.table2.row($(this).parents('tr')).data();
			app.setDataToModal(data);
			
			var myModalEl = document.getElementById('pacienteEditar')
			modaledit = new bootstrap.Modal(myModalEl)
			modaledit.show();
			
			
			//$('#pacienteEditar').modal();
		});
		
			// Agregar una acción para el botón citar
		$('#pendientes tbody').on('click', '.btn-smDP', function() {
			 $("#msg").text('').removeClass('alert')
			var data = app.table2.row($(this).parents('tr')).data();
				if (data.tarifas && data.tarifas.tipo !== null) {
 					 $('#tarifaE').val(data.tarifas.tipo);
						}
			app.setDataToModal2(data);
		//	$('#pacienteCitar').modal();
			var myModalEl = document.getElementById('pacienteCitar')
			modalasig = new bootstrap.Modal(myModalEl)
			modalasig.show();
			$('.select2').select2({
        	dropdownParent: $('#pacienteCitar .modal-body')
            });
		});
		
		// Agregar una acción para el botón eliminar
		$('#pendientes tbody').on('click', '.btn-sm2P', function() {
			 $("#msg").text('').removeClass('alert')
			var data = app.table2.row($(this).parents('tr')).data();
			app.eliminar(data.id)
			
		});

		
		/**Para crear registros nuevos */
		function confirmarTodo() {
			 $("#msg").text('').removeClass('alert')
			 	var myModalEl = document.getElementById('crearPaci')
			 modaladd = new bootstrap.Modal(myModalEl)
			modaladd.show();
			



		}


		
			/** Esta función de aqui es para hacer la celda seleccionada o no  */
		$('#pendientes tbody').on('click', 'tr', function() {
			if ($(this).hasClass('table-active')) {
				$(this).removeClass('table-active');
				$(this).css('background-color', null);
			} else {
				app.table2.$('tr.table-active').removeClass('table-active');
				$(this).addClass('table-active');

			}
		});


	},
	
	initDatatable3: function(id) {
		/**Esto se encarfa de crear la tabla cargando el json que recibe de la bbdd 
		 * que lo recibe en formato json, lo retornas a la llamada etc
		 */
			
		
		app.table3 = $(id).DataTable({

			ajax: {
				url: app.backend + '/allmescurso',
				dataSrc: function(json) {
					console.log(json)
					return json;
				}
			},
			dom: 'Bfrtip',
			columns: [
				{
					// Agregar una columna para el botón Editar
					data: null,
					render: function(data, type, row) {
						return '<button type="button" class="btn btn-smDH btn-outline-primary editar"><i class="bi bi-plus-circle"></i></i>' +
							'</button>';
					},
					orderable: false,
					searchable: false
				},
					{
					// Agregar una columna para el botón Editar
					data: null,
					render: function(data, type, row) {
						return '<button type="button" class="btn btn-smH btn-outline-primary editar" ><i class="bi bi-pencil-fill"></i>' +
							'</button>&nbsp;<button class="btn btn-danger btn-sm2H" data-id="' + row.id +
							'"><i class="bi bi-trash-fill"></i></i></button>';
					},
					orderable: false,
					searchable: false
				},
				{ data: "nombre" },
				{ data: "apellidos", render: function (data) {  return data || "N/A"; } },
				{ data: "dni", render: function (data) {  return data || "N/A"; } },
				{ data: "fechaAlta" },
				{ data: "domicilio", render: function (data) {  return data || "N/A"; } },
				{ data: "codigoPostal", render: function (data) {  return data || "N/A"; } },
				{ data: "poblacion", render: function (data) {  return data || "N/A"; } },
				{ data: "telefono" },
				{ data: "sabeDeMi", render: function (data) {  return data || "N/A"; } },
				{ data: "tarifas.tipo", render: function (data) {  return data || "N/A"; } },
			
				
			],
			buttons: [
				
			],
				responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.childRowImmediate,
                type: 'none',
                target: ''
            }
        }
		});

		// Agregar una acción para el botón Editar
		$('#hoy tbody').on('click', '.btn-smH', function() {
			 $("#msg").text('').removeClass('alert')
			var data = app.table3.row($(this).parents('tr')).data();
			app.setDataToModal(data);
			
			var myModalEl = document.getElementById('pacienteEditar')
			modaledit = new bootstrap.Modal(myModalEl)
			modaledit.show();
			
			
			//$('#pacienteEditar').modal();
		});
		
			// Agregar una acción para el botón citar
		$('#hoy tbody').on('click', '.btn-smDH', function() {
			 $("#msg").text('').removeClass('alert')
			var data = app.table3.row($(this).parents('tr')).data();
				if (data.tarifas && data.tarifas.tipo !== null) {
 					 $('#tarifaE').val(data.tarifas.tipo);
						}
			app.setDataToModal2(data);
		//	$('#pacienteCitar').modal();
			var myModalEl = document.getElementById('pacienteCitar')
			modalasig = new bootstrap.Modal(myModalEl)
			modalasig.show();
			$('.select2').select2({
        	dropdownParent: $('#pacienteCitar .modal-body')
            });
		});
		
		// Agregar una acción para el botón eliminar
		$('#hoy tbody').on('click', '.btn-sm2H', function() {
			 $("#msg").text('').removeClass('alert')
			var data = app.table3.row($(this).parents('tr')).data();
			app.eliminar(data.id)
			
		});

		
		/**Para crear registros nuevos */
		function confirmarTodo() {
			 $("#msg").text('').removeClass('alert')
			 	var myModalEl = document.getElementById('crearPaci')
			 modaladd = new bootstrap.Modal(myModalEl)
			modaladd.show();
			



		}


		
			/** Esta función de aqui es para hacer la celda seleccionada o no  */
		$('#hoy tbody').on('click', 'tr', function() {
			if ($(this).hasClass('table-active')) {
				$(this).removeClass('table-active');
				$(this).css('background-color', null);
			} else {
				app.table3.$('tr.table-active').removeClass('table-active');
				$(this).addClass('table-active');

			}
		});


	},
	
	
	
	
	
	setDataToModal(data) {
		
	
		
				 $('#idE').val(data.id);
				 $('#nombreE').val(data.nombre);
				 $('#apellidosE').val(data.apellidos);
				 $('#dniE').val(data.dni);
				 $('#dniViejoE').val(data.dni);
				 $('#domicilioE').val(data.domicilio);
				 $('#cpE').val(data.codigoPostal);
				 $('#poblaE').val(data.poblacion);
				 $('#telE').val(data.telefono);
				
				 $('#conoceE').val(data.sabeDeMi);
				 
				 
			if (data.tarifas === null || data.tarifas === '') {
   				$('#tarifaE').val('');
  			}else{
				  $('#tarifaE').val(data.tarifas.tipo);
			  }



	},
	
	setDataToModal2(data) {
			
				 $('#idCitar').val(data.id)
	

	},

	editar: function(data) {
		if (confirm('¿Estás seguro de que editar este paciente')) {

			app.table.ajax.reload();
			$.ajax({
				url: app.backend + '/editar',
				data: JSON.stringify(data),
				method: 'POST',
				dataType: 'json',
				contentType: "application/json; charset=utf-8",
				success: function(json) {
					$("#msg").text('Se editó paciente correctamente');
					$("#msg").show();
					
						modaledit.hide();
			
					app.table.ajax.reload();
				},
			 error: function(xhr, status, error) {
            var response = JSON.parse(xhr.responseText);
            var errores = response;
     
            for (var campo in errores) {
			
                 if (errores.hasOwnProperty(campo)) {
                    var input = $('#' + campo);
                    input.addClass('is-invalid');
                     input.after('<div class="invalid-feedback invalid-feedback-crear">' + errores[campo] + '</div>');
                }
            }
           
        }
			})
		}
	},
	
		asignar: function(data) {
		
		if (confirm('¿Estás seguro de que asignar esta cita?')) {

			app.table.ajax.reload();
			$.ajax({
				url: app.backend + '/asignar',
				data: JSON.stringify(data),
				method: 'POST',
				dataType: 'json',
				contentType: "application/json; charset=utf-8",
				success: function(json) {
					$("#msg").text('Se asigno la cita correctamente');
					$("#msg").show();
					$('#pacienteCitar').modal('hide');
					app.table.ajax.reload();
						modalasig.hide();
				
					setTimeout(function() {
  							location.reload();
					}, 3000);
		
				},
			 	error: function(error) {
					$("#msg").text('Error al asignar cita');
					$("#msg").removeClass('alert-primary').addClass('alert-danger');
					$("#msg").show();
				
					app.table.ajax.reload();
				}
			})
		}
	},
	


	guardar: function(data) {
		
		app.table.ajax.reload();
		$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
		$.ajax({
			url: app.backend + '/add',
			data: JSON.stringify(data),
			method: 'POST',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			success: function(json) {
				$('#crearPaci').modal('hide');
				$("#msg").text('Ingreso añadido correctamente');
				$("#msg").show();
			 	modaladd.hide();
				app.table.ajax.reload();
				
			},
			 error: function(xhr, status, error) {
            var response = JSON.parse(xhr.responseText);
            var errores = response;
     
            for (var campo in errores) {
				
                 if (errores.hasOwnProperty(campo)) {
                    var input = $('#' + campo);
                    input.addClass('is-invalid');
                     input.after('<div class="invalid-feedback invalid-feedback-crear">' + errores[campo] + '</div>');
                
                }
            }
           
        }
    })

	},
	
	eliminar: function(id) {
		if (confirm('¿Estás seguro de que eliminar este paciente')) {

			app.table.ajax.reload();
			$.ajax({
				url: app.backend + '/delete/'+id,
				method: 'DELETE',
				success: function(json) {
					
					$("#msg").text('Se eliminó correctamente');
					$("#msg").show();
					app.table.ajax.reload();
				},
				error: function(error) {
					$("#msg").text('Error al eliminar el ingreso');
					$("#msg").removeClass('alert-primary').addClass('alert-danger');
					$("#msg").show();
					app.table.ajax.reload();
				}
			})
		}
	},
	
	
	
	
}








$(document).ready(function() {
	app.init();
});