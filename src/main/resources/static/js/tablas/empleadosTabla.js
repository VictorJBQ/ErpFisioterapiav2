var modaladd;
var modaledit;
var app = {

	/** creo una variable que contenga la url donde hace la llamada, que lo que hace es iniciar la tabla
	 * la tabla la inicia en la funcion init que es llamada y le pasa el id de la tabla
	 */
	
	
	backend: '/api-emp',
	table: null,
	init: function() {
		console.log("entro en la 2")
		app.initDatatable('#citas');
		$("#editar").click(function() {
			
			app.editar({
				identificador: $('#identificadorE').val(),
				nombre: $('#nombreE').val(),
				password: $('#passwordE').val(),
				roles: $('#rolesE').val(),
			});
		});
		
		


		$("#guardar").click(function() {

			app.guardar({
				identificador: $('#identificadorA').val(),
				nombre: $('#nombreA').val(),
				password: $('#passwordA').val(),
				roles: $('#rolesA').val(),
			});
		});

	/**Cierre modal : Cuando los madales se cierren, ponemos los input a ' ' y quitamos el inalid */
		$('#crearEmpl').on('hidden.bs.modal', function() {
			$(this).find('input').removeClass('is-invalid');
			$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
			$('#identificadorA').val('');
			$('#passwordA').val('');
			$('#nombreA').val('');
		});
		
		$('#personaModal').on('hidden.bs.modal', function() {
			$(this).find('input').removeClass('is-invalid');
			$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
		});

/**Fin cierre modal */


/**Escribir input: cuando el campo input no esta vacío se quita el invalid */

		$('#nombreA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		$('#identificadorA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		$('#passwordA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#rolesA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		
		$('#nombreE').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		$('#identificadorE').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
	
		
		$('#rolesE').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
	/**FIN esctibir */	
		


	},
	initDatatable: function(id) {
		console.log(app)
		/**Esto se encarfa de crear la tabla cargando el json que recibe de la bbdd 
		 * que lo recibe en formato json, lo retornas a la llamada etc
		 */
		app.table = $(id).DataTable({

			ajax: {
				url: app.backend + '/allemple',
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
						return '<button type="button" class="btn btn-sm btn-outline-primary editar"><i class="bi bi-pencil-fill"></i>' +
							'</button>&nbsp;<button class="btn btn-danger btn-sm2" data-id="' + row.id +
							'"><i class="bi bi-trash-fill"></i></i></button>';
					},
					orderable: false,
					searchable: false
				},
				{ data: "identificador" },
				{ data: "nombre" },
				{ data: "roles.tipo" },
			
			],
			buttons: [
				{
					text: 'Nuevo empleado',
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
		$('#citas tbody').on('click', '.btn-sm', function() {
			 $("#msg").text('').removeClass('alert')
			var data = app.table.row($(this).parents('tr')).data();
			app.setDataToModal(data);
			var myModalEl = document.getElementById('personaModal')
			modaledit = new bootstrap.Modal(myModalEl)
			modaledit.show();

		//	$('#personaModal').modal();
		});
		
		// Agregar una acción para el botón eliminar
		$('#citas tbody').on('click', '.btn-sm2', function() {
			 $("#msg").text('').removeClass('alert')
			var data = app.table.row($(this).parents('tr')).data();
			app.eliminar(data.identificador)
			
		});

		
		/**Para crear registros nuevos */
		function confirmarTodo() {
			 $("#msg").text('').removeClass('alert')
			var myModalEl = document.getElementById('crearEmpl')
			modaladd = new bootstrap.Modal(myModalEl)
			modaladd.show();
			//$('#crearEmpl').modal();



		}




		/** Esta función de aqui es para hacer la celda seleccionada o no  */
		$('#citas tbody').on('click', 'tr', function() {
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

		$('#identificadorE').val(data.identificador);
		$('#rolesE').val(data.roles.tipo);
		$('#nombreE').val(data.nombre);
		$('#passwordE').val(data.password);



	},

	editar: function(data) {
		if (confirm('¿Estás seguro de que editar este empleado?')) {

			app.table.ajax.reload();
			$.ajax({
				url: app.backend + '/editarEmple',
				data: JSON.stringify(data),
				method: 'POST',
				dataType: 'json',
				contentType: "application/json; charset=utf-8",
				success: function(json) {
					$("#msg").text('Se editó el empleado correctamente');
					$("#msg").show();
					modaledit.hide();
					app.table.ajax.reload();
				},
			 error: function(xhr, status, error) {
            var response = JSON.parse(xhr.responseText);
            var errores = response;
     
            for (var campo in errores) {
				console.log(campo)
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

	guardar: function(data) {

		app.table.ajax.reload();
		$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
		$.ajax({
			url: app.backend + '/addEmple',
			data: JSON.stringify(data),
			method: 'POST',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			success: function(json) {
				$('#crearEmpl').modal('hide');
				$("#msg").text('Empleado añadido correctamente');
				$("#msg").show();
				modaladd.hide();
				app.table.ajax.reload();
				
			},
			 error: function(xhr, status, error) {
            var response = JSON.parse(xhr.responseText);
            var errores = response;
     
            for (var campo in errores) {
				console.log(campo)
                 if (errores.hasOwnProperty(campo)) {
                    var input = $('#' + campo);
                    input.addClass('is-invalid');
                     input.after('<div class="invalid-feedback invalid-feedback-crear">' + errores[campo] + '</div>');
                    console.log(errores[campo])
                }
            }
           
        }
    })

	},
	
	eliminar: function(id) {
		if (confirm('¿Estás seguro de que eliminar este empleado?')) {

			app.table.ajax.reload();
			$.ajax({
				url: app.backend + '/deleteEm/'+id,
				method: 'DELETE',
				success: function(json) {
					
					$("#msg").text('Se eliminó correctamente');
					$("#msg").show();
					app.table.ajax.reload();
				},
				error: function(error) {
					$("#msg").text('Error al eliminar el empleado');
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