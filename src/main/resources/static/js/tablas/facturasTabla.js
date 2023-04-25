var modaltipo;
var modaldato;
var app = {

	/** creo una variable que contenga la url donde hace la llamada, que lo que hace es iniciar la tabla
	 * la tabla la inicia en la funcion init que es llamada y le pasa el id de la tabla
	 */
	backend: '/api-fct',
	table: null,
	init: function() {
		app.initDatatable('#facturas');
		
		$("#datosB").click(function() {
			$('#facturaDatos').modal('hide');
			modaldato.hide();
		});
		
			$("#tipoData").click(function() {
		modaltipo.hide();
		});
		
	
	
	/**Cierre modal : Cuando los madales se cierren, ponemos los input a ' ' y quitamos el inalid */
		$('#facturaTipo').on('hidden.bs.modal', function() {
			$(this).find('input').removeClass('is-invalid');
			$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
			$('#facT').val('');
			$('#fechaT').val('');
			$('#nomapT').val('');
			$('#dniT').val('');
			$('#DomicilioT').val('');
			$('#PoblacionT').val('');
			$('#cpT').val('');
			$('#importeT').val('');
			$('#pagoT').val('');
			
		});
		
		$('#facturaDatos').on('hidden.bs.modal', function() {
			$(this).find('input').removeClass('is-invalid');
			$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
		});

/**Fin cierre modal */


/**Escribir input: cuando el campo input no esta vacío se quita el invalid */

	
		
		$('#importeA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#tipoA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		
		$('#idE').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
	
		$('#importeE').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#tipoE').on('input', function () {
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
				
				{ data: "formaPago" },
				{ data: "importe" },
				{ data: "cita.pacientes.dni" },
				{ data: "fecha" },
				{
					// Agregar una columna para el botón Editar
					data: null,
					render: function(data, type, row) {
						return '<button type="button" class="btn btn-sm btn-outline-primary editar"><i class="bi bi-receipt"></i>' +
							'</button>';
					},
					orderable: false,
					searchable: false
				}
			],
			buttons: [
				{
					text: 'Factura tipo',
					action: function() {
						confirmarTodo();
					}
				}
			]
		});

		// Agregar una acción para el botón Editar
		$('#facturas tbody').on('click', '.btn-sm', function() {
			 $("#msg").text('').removeClass('alert')
			var data = app.table.row($(this).parents('tr')).data();
			app.setDataToModal(data);
			var myModalEl = document.getElementById('facturaDatos')
			modaldato = new bootstrap.Modal(myModalEl)
			modaldato.show();
			
		});
		

		
		/**Para crear registros nuevos */
		function confirmarTodo() {
			 $("#msg").text('').removeClass('alert')
			 	var myModalEl = document.getElementById('facturaTipo')
			modaldato = new bootstrap.Modal(myModalEl)
			modaldato.show();



		}




		/** Esta función de aqui es para hacer la celda seleccionada o no  */
		$('#ingresos tbody').on('click', 'tr', function() {
			if ($(this).hasClass('table-active')) {
				$(this).removeClass('table-active');
				$(this).css('background-color', null);
			} else {
				app.table.$('tr.table-active').removeClass('table-active');
				$(this).addClass('table-active');

			}
		});


	},
	setDataToModal(data) {
		$('#fechaC').val(data.fecha);
		$('#domicilioC').val(data.cita.pacientes.domicilio);
		$('#codigoC').val(data.cita.pacientes.codigoPostal);
		$('#dniC').val(data.cita.pacientes.dni);
		$('#apellidosC').val(data.cita.pacientes.apellidos);
		$('#nombreC').val(data.cita.pacientes.nombre);
		$('#poblacionC').val(data.cita.pacientes.poblacion);
		$('#formaPagoC').val(data.formaPago);
		$('#precioC').val(data.importe);
		



	},

	
	
	
}








$(document).ready(function() {
	app.init();
});