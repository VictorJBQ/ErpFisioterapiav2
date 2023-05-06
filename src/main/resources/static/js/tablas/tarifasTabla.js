var modaladd;
var modaledit;
var app = {

	/** creo una variable que contenga la url donde hace la llamada, que lo que hace es iniciar la tabla
	 * la tabla la inicia en la funcion init que es llamada y le pasa el id de la tabla
	 */
	backend: '/api-trf',
	table: null,
	init: function() {
		app.initDatatable('#tarifas');
		$("#editar").click(function() {
			
			app.editar({
				tipoViejaE: $('#tipoViejaE').val(),
				tipoNuevaE: $('#tipoNuevaE').val(),
				precioE: $('#precioE').val(),
				
			});
		});


		$("#guardar").click(function() {

			app.guardar({
				tipoNuevaA: $('#tipoNuevaA').val(),
				precioA: $('#precioA').val(),
			});
		});

	/**Cierre modal : Cuando los madales se cierren, ponemos los input a ' ' y quitamos el inalid */
		$('#crearTarifa').on('hidden.bs.modal', function() {
			$(this).find('input').removeClass('is-invalid');
			$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
			$('#tipoNuevaA').val('');
			$('#precioA').val('');
		});
		
		$('#editarTarifa').on('hidden.bs.modal', function() {
			$(this).find('input').removeClass('is-invalid');
			$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
		});

/**Fin cierre modal */


/**Escribir input: cuando el campo input no esta vacío se quita el invalid */

	
		
		$('#tipoNuevaA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#precioA').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
	
	
		$('#precioE').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#tipoNuevaE').on('input', function () {
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
						return '<button type="button" class="btn btn-sm btn-outline-primary editar"><i class="bi bi-pencil-fill"></i>' +
							'</button>&nbsp;<button class="btn btn-danger btn-sm2" data-id="' + row.id +
							'"><i class="bi bi-trash-fill"></i></i></button>';
					},
					orderable: false,
					searchable: false
				},
				{ data: "tipo" },
				{ data: "precio" },
				
			],
			buttons: [
				{
					text: 'Nueva tarifa',
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
		$('#tarifas tbody').on('click', '.btn-sm', function() {
			 $("#msg").text('').removeClass('alert')
			var data = app.table.row($(this).parents('tr')).data();
			app.setDataToModal(data);
			var myModalEl = document.getElementById('editarTarifa')
			modaledit = new bootstrap.Modal(myModalEl)
			modaledit.show();
			
		});
		
		// Agregar una acción para el botón eliminar
		$('#tarifas tbody').on('click', '.btn-sm2', function() {
			 $("#msg").text('').removeClass('alert')
			var data = app.table.row($(this).parents('tr')).data();
			app.eliminar(data.tipo)
			
		});

		
		/**Para crear registros nuevos */
		function confirmarTodo() {
			 $("#msg").text('').removeClass('alert')
			 var myModalEl = document.getElementById('crearTarifa')
			modaladd = new bootstrap.Modal(myModalEl)
			modaladd.show();
		



		}




		/** Esta función de aqui es para hacer la celda seleccionada o no  */
		$('#tarifas tbody').on('click', 'tr', function() {
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
console.log(data)
		$('#tipoViejaE').val(data.tipo);
		$('#tipoNuevaE').val(data.tipo);
		$('#precioE').val(data.precio);



	},

	editar: function(data) {
		if (confirm('¿Estás seguro de que editar esta tarifa?')) {

			app.table.ajax.reload();
			$.ajax({
				url: app.backend + '/editar',
				data: JSON.stringify(data),
				method: 'POST',
				dataType: 'json',
				contentType: "application/json; charset=utf-8",
				success: function(json) {
					$("#msg").text('Se editó tarifa correctamente');
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
		console.log(data)
		app.table.ajax.reload();
		$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
		$.ajax({
			url: app.backend + '/add',
			data: JSON.stringify(data),
			method: 'POST',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			success: function(json) {
				$('#crearTarifa').modal('hide');
				$("#msg").text('Tarifa añadida correctamente');
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
		if (confirm('¿Estás seguro de que eliminar esta tarifa')) {

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
					$("#msg").text('Error al eliminar tarifa');
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