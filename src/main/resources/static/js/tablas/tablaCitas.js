function mostrarCampos() {
	var nuevo = document.getElementById("campos-nuevo");
	var existente = document.getElementById("campos-existente");
	if (document.querySelector('input[name="paciente"]:checked').value == "nuevo") {
		nuevo.style.display = "block";
		existente.style.display = "none";
	} else if (document.querySelector('input[name="paciente"]:checked').value == "sin-paciente") {
		nuevo.style.display = "none";
		existente.style.display = "none";
	} else {
		nuevo.style.display = "none";
		existente.style.display = "block";
	}
}


function mostrarCampos2() {
	var nuevo2 = document.getElementById("campos-nuevoedit");
	var existente2 = document.getElementById("campos-existenteedit");
	if (document.querySelector('input[name="pacienteEDIT"]:checked').value == "nuevoEDIT") {
		nuevo2.style.display = "block";
		existente2.style.display = "none";
	} else if (document.querySelector('input[name="pacienteEDIT"]:checked').value == "sin-pacienteEDIT") {
		nuevo2.style.display = "none";
		existente2.style.display = "none";
	} else {
	
		nuevo2.style.display = "none";
		existente2.style.display = "block";
	}
}

var miSelect = document.getElementById("opcionesee");
var modaladd;
var modaledit;
var modalcarga;

console.log(miSelect)



var app = {

	/** creo una variable que contenga la url donde hace la llamada, que lo que hace es iniciar la tabla
	 * la tabla la inicia en la funcion init que es llamada y le pasa el id de la tabla
	 */
	backend: '/api-ct',
	table1: null,
	table2: null,
	 

	init: function() {
		app.initDatatable('#citas');
		$("#editar").click(function() {
			var opcion = document.querySelector('input[name="pacienteEDIT"]:checked').value;
			if (opcion == 'sin-pacienteEDIT') {
				console.log($('#fechaE').val())
				app.editarSIN({
					fechaE: $('#fechaE').val(),
					idE: $('#idE').val(),
					horaE: $('#horaE').val(),
					tipoE: $('#tipoE').val(),
				})
			

			} else if (opcion == 'existenteEDIT') {
			
				app.editarExistente({
					fechaE: $('#fechaE').val(),
					idE: $('#idE').val(),
					horaE: $('#horaE').val(),
					tipoE: $('#tipoE').val(),
					idPacienteE: $('#idPacienteE').val(),
					estadoEE: $('#estadoEE').val(),
				})
				
			} else if (opcion == "nuevoEDIT") {
				app.editarNUEVO({
					fechaE: $('#fechaE').val(),
					idE: $('#idE').val(),
					horaE: $('#horaE').val(),
					tipoE: $('#tipoE').val(),
					nombreE: $('#nombreE').val(),
					telefonoE: $('#telefonoE').val(),
					estadoE: $('#estadoE').val(),
				})
			}
		});


		$("#guardar").click(function() {
		
			var opcion = document.querySelector('input[name="paciente"]:checked').value;
			if (opcion == 'sin-paciente') {
				app.guardarSIN({
					fechaA: $('#fechaA').val(),
					horaA: $('#horaA').val(),
					tipoA: $('#tipoA').val(),
				})

			} else if (opcion == 'existente') {
				app.guardarExistente({
					fechaA: $('#fechaA').val(),
					horaA: $('#horaA').val(),
					tipoA: $('#tipoA').val(),
					idPacienteA: $('#idPacienteA').val(),
					estadoCONA: $('#estadoCONA').val(),
				})
			} else if (opcion == "nuevo") {
				app.guardarNuevo({
					fechaA: $('#fechaA').val(),
					horaA: $('#horaA').val(),
					tipoA: $('#tipoA').val(),
					nombreA: $('#nombreA').val(),
					telefonoA: $('#telefonoA').val(),
					estadoNUA: $('#estadoNUA').val(),
				})
			}


		});
		
				$("#cargaMes").click(function() {
			app.cargaDa($('#meses').val())

		});
				

		/**Cierre modal : Cuando los madales se cierren, ponemos los input a ' ' y quitamos el inalid */
		$('#crearCita').on('hidden.bs.modal', function() {
			$(this).find('input').removeClass('is-invalid');
			$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
			$('#fechaA').val('');
			$('#horaA').val('');
			$('#tipoA').val('');
			$('#nombreA').val('');
			//$('#idPacienteA').val('');
			$('#telefonoA').val('');

		});

		$('#editarCita').on('hidden.bs.modal', function() {
			$(this).find('input').removeClass('is-invalid');
			$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
			$('#nombreE').val('');
			$('#telefonoE').val('');


		});

		/**Fin cierre modal */


		/**Escribir input: cuando el campo input no esta vacío se quita el invalid */



		$('#idPacienteA').on('input', function() {
			$(this).removeClass('is-invalid');
		});

		$('#nombreA').on('input', function() {
			$(this).removeClass('is-invalid');
		});


		$('#tipoA').on('input', function() {
			$(this).removeClass('is-invalid');
		});

		$('#horaA').on('input', function() {
			$(this).removeClass('is-invalid');
		});

		$('#fechaA').on('input', function() {
			$(this).removeClass('is-invalid');
		});

		$('#telefonoA').on('input', function() {
			$(this).removeClass('is-invalid');
		});

		$('#estadoCONA').on('input', function() {
			$(this).removeClass('is-invalid');
		});




		$('#idPacienteE').on('input', function() {
			$(this).removeClass('is-invalid');
		});

		$('#nombreE').on('input', function() {
			$(this).removeClass('is-invalid');
		});


		$('#tipoE').on('input', function() {
			$(this).removeClass('is-invalid');
		});

		$('#horaE').on('input', function() {
			$(this).removeClass('is-invalid');
		});

		$('#fechaE').on('input', function() {
			$(this).removeClass('is-invalid');
		});

		$('#telefonoE').on('input', function() {
			$(this).removeClass('is-invalid');
		});

		$('#estadoE').on('input', function() {
			$(this).removeClass('is-invalid');
		});

		$('#estadoEE').on('input', function() {
			$(this).removeClass('is-invalid');
		});
		/**FIN esctibir */



	},
	initDatatable: function(id) {

		/**Esto se encarfa de crear la tabla cargando el json que recibe de la bbdd 
		 * que lo recibe en formato json, lo retornas a la llamada etc
		 */
		app.table1 = $(id).DataTable({

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
						var buttons = '';
						var dato = row.estado; // obtener el valor de la columna de fecha
						if (dato === 'salvada' || dato === 'terminada') { // agregar una condición para mostrar u ocultar los botones según el valor de la fecha
							buttons = '';
						} else if (dato === 'libre' || dato === 'cancelada') {
							buttons += '<button type="button" class="btn btn-sm btn-outline-primary editar"><i class="bi bi-pencil-fill"></i>' +
								'</button>&nbsp;<button class="btn btn-danger btn-sm2" data-id="' + row.id +
								'"><i class="bi bi-trash-fill"></i></i></button>';
						} else {
							buttons += '<button type="button" class="btn btn-sm btn-outline-primary editar"><i class="bi bi-pencil-fill"></i>' +
								'</button>&nbsp;<button class=" btn btn-outline-warning btn-smCANCEL " data-id="' + row.id +
								'"><i class="bi bi-x-square-fill "></i></i></button>' + '&nbsp;<button class="btn btn-danger btn-sm2" data-id="' + row.id +
								'"><i class="bi bi-trash-fill"></i></i></button>';
						}
						return buttons;
					},
					orderable: false,
					searchable: false
				},
				{ data: "fecha" },
				{ data: "hora" },
				{ data: "tipo" },
				{
					data: "estado",
					render: function(data, type, row) {
						var color = '';
						var borderRadius = 'border-radius: 10px;';
						if (data == 'libre') {
							color = 'background-color: green; color: white;';
						} else if (data == 'pendiente-Confirmar') {
							color = 'background-color: yellow; color: black;';
						} else if (data == 'cancelada') {
							color = 'background-color: red; color: white;';
						} else if (data == 'terminada' || data == 'salvada') {
							color = 'background-color: blue; color: white;';
						} else if (data == 'reservada') {
							color = 'background-color: black; color: white;';
						} else if (data == 'salvada-pendiente' || data == 'confirmada') {
							color = 'background-color: #17A2B8; color: white;';
						}
						return '<span style="' + color + borderRadius + 'padding: 5px; text-align: center;">' + data + '</span>';
					}
				},
				{
					data: "pacientes",
					render: function(data) {

						var nombre = data && data.nombre ? data.nombre : "NA";
						var telefono = data && data.telefono ? data.telefono : "NA";
						return nombre + " - " + telefono;
					}
				},
			
			],
			buttons: [
				{
					text: 'Nueva cita',
					action: function() {
						confirmarTodo();
					}
				},
				
				{
					text: 'Carga automatica de citas',
					action: function() {
						cargaCitas();
					}
				}
			],
			responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.childRowImmediate,
                type: 'none',
                target: ''
            }
        },
        
		});

		// Agregar una acción para el botón Editar
		$('#citas tbody').on('click', '.btn-sm', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table1.row($(this).parents('tr')).data();
			var ce = data.estado;


			if (ce == 'terminada') {
				$("#msg").text('No puedes editar una cita terminada');
				$("#msg").removeClass('alert-primary').addClass('alert-danger');
				$("#msg").show();
			} else {
				app.setDataToModal(data);
				var myModalEl = document.getElementById('editarCita')
				modaledit = new bootstrap.Modal(myModalEl)
				modaledit.show();
			
				$('.select2').select2({
					dropdownParent: $('#editarCita .modal-body')
				});
			}


		});

		// Agregar una acción para el botón eliminar
		$('#citas tbody').on('click', '.btn-sm2', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table1.row($(this).parents('tr')).data();
			var ce = data.estado;
			if (ce == 'terminada') {
				$("#msg").text('No puedes eliminar una cita terminada');
				$("#msg").removeClass('alert-primary').addClass('alert-danger');
				$("#msg").show();
			} else {
				app.eliminar(data.id)
			}


		});
		
			// Agregar una acción para el botón cancelar
		$('#citas tbody').on('click', '.btn-smCANCEL', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table1.row($(this).parents('tr')).data();
			var ce= data.estado;
			var pa= data.pacientes;
			if ((ce !='terminada' && ce !='salvada') && pa!=null ){
					app.cancelar(data.id)
			}else{
			alert("No puedes cancelar una cita terminada o sin paciente");
			}

		});



		/**Para crear registros nuevos */
		function confirmarTodo() {
			$("#msg").text('').removeClass('alert')
			var myModalEl = document.getElementById('crearCita')
			modaladd = new bootstrap.Modal(myModalEl)
			modaladd.show();
			$('.select2').select2({
				dropdownParent: $('#crearCita .modal-body')
			});




		}
		
			function cargaCitas() {
			$("#msg").text('').removeClass('alert')
			var myModalEl = document.getElementById('cargarCitas')
			modalcarga = new bootstrap.Modal(myModalEl)
			modalcarga.show();
	

		}




		/** Esta función de aqui es para hacer la celda seleccionada o no  */
		$('#citas tbody').on('click', 'tr', function() {
			if ($(this).hasClass('table-active')) {
				$(this).removeClass('table-active');
				$(this).css('background-color', null);
			} else {
				app.table1.$('tr.table-active').removeClass('table-active');
				$(this).addClass('table-active');

			}
		});


	},
	setDataToModal(data) {
	

		const select = document.getElementById("estadoEE"); // seleccionar el elemento select
		const cancelada = select.querySelector("option[value='cancelada']"); // seleccionar la opción 'cancelada'
		const libre = select.querySelector("option[value='libre']"); // seleccionar la opción 'cancelada'
		const pendiente = select.querySelector("option[value='pendiente-Confirmar']"); // seleccionar la opción 'cancelada'
		const confirmada = select.querySelector("option[value='confirmada']"); // seleccionar la opción 'cancelada'
		const reservada = select.querySelector("option[value='reservada']"); // seleccionar la opción 'cancelada'
		const terminadaE = select.querySelector("option[value='terminadaEF']"); // seleccionar la opción 'cancelada'
		const terminadaB = select.querySelector("option[value='terminadaBZ']"); // seleccionar la opción 'cancelada' 




		var fechaActual = new Date();
		var fechaActualadd1 = new Date(fechaActual);
		fechaActualadd1.setDate(fechaActual.getDate() + 1);
		var fechaCita = new Date(data.fecha + " " + data.hora)

		if (fechaCita.getTime() < fechaActual.getTime() && data.estado != 'libre') {
			cancelada.style.display = "block"; 
			libre.style.display = "none"; 
			pendiente.style.display = "none"; 
			confirmada.style.display = "none"; 
			reservada.style.display = "none"; 
			terminadaE.style.display = "block"; 
			terminadaB.style.display = "block"; 

		} else if (fechaCita.getTime() < fechaActual.getTime() && data.estado === 'libre') {
			
			cancelada.style.display = "block"; 
			libre.style.display = "block"; 
			pendiente.style.display = "none"; 
			confirmada.style.display = "none"; 
			reservada.style.display = "none"; 
			terminadaE.style.display = "block"; 
			terminadaB.style.display = "block"; 
		} else if (fechaCita.getTime() == fechaActualadd1.getTime() && data.estado != 'libre') {
			cancelada.style.display = "block"; 
			libre.style.display = "none"; 
			pendiente.style.display = "none"; 
			confirmada.style.display = "block"; 
			reservada.style.display = "none"; 
			terminadaE.style.display = "block"; 
			terminadaB.style.display = "block"; 

		}else{
			cancelada.style.display = "block"; 
			libre.style.display = "block"; 
			pendiente.style.display = "block"; 
			confirmada.style.display = "block"; 
			reservada.style.display = "block"; 
			terminadaE.style.display = "block"; 
			terminadaB.style.display = "block";
		}
	
		$('#fechaE').val(data.fecha);
		$('#idE').val(data.id);
		$('#horaE').val(data.hora);
		$('#tipoE').val(data.tipo);
		//$('#estadoE').val(data.estado)

		if (data.estado === null || data.estado === '') {
			$('#estadoEE').val('');
		} else {

			$('#estadoEE').val(data.estado);
		}

		if (data.pacientes === null || data.pacientes === '') {
			$('#idPacienteE').val('nulo');
			$('input[name="pacienteEDIT"][value="sin-pacienteEDIT"]').prop('checked', true);
			$('input[name="pacienteEDIT"][value="sin-pacienteEDIT"]').parent().show();

			mostrarCampos2();

		} else {
			// Buscar la opción que corresponde al paciente de la cita
			var option = $('#idPacienteE').find('option').filter(function() {
				return $(this).text() === data.pacientes.nombre + ' ' + data.pacientes.telefono;
			});
			if (option.length > 0) {

				// Si se encontró la opción, seleccionarla
				$('#idPacienteE').val(option.val());
				$('input[name="pacienteEDIT"][value="existenteEDIT"]').prop('checked', true);
				$('input[name="pacienteEDIT"][value="sin-pacienteEDIT"]').parent().hide();
				mostrarCampos2();


			} else {
				console.log(data.pacientes.nombre)
				// Si no se encontró la opción, seleccionar la opción "Sin paciente"
				$('#idPacienteE').val('nulo');
				$('input[name="pacienteEDIT"][value="sin-pacienteEDIT"]').prop('checked', true);
				$('input[name="pacienteEDIT"][value="sin-pacienteEDIT"]').parent().show();
				mostrarCampos2();
			}
		}




	},
	

	editarSIN: function(data) {
		$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
		if (confirm('¿Estás seguro de que editar está cita?')) {

			app.table1.ajax.reload();
			$.ajax({
				url: app.backend + '/editarSIN',
				data: JSON.stringify(data),
				method: 'POST',
				dataType: 'json',
				contentType: "application/json; charset=utf-8",
				success: function(json) {
					$("#msg").text('Se editó cita correctamente');
					$("#msg").removeClass('alert-danger').addClass('alert-primary');
					$("#msg").show();
					modaledit.hide();
					app.table1.ajax.reload();
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


	editarNUEVO: function(data) {
		$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
		if (confirm('¿Estás seguro de que editar está cita?')) {

			app.table1.ajax.reload();
			$.ajax({
				url: app.backend + '/editarNV',
				data: JSON.stringify(data),
				method: 'POST',
				dataType: 'json',
				contentType: "application/json; charset=utf-8",
				success: function(json) {
					setTimeout(function() {
						location.reload();
					},);
					$("#msg").text('Se editó cita correctamente');
					$("#msg").removeClass('alert-danger').addClass('alert-primary');
					$("#msg").show();
					modaledit.hide();
					app.table1.ajax.reload();
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


	editarExistente: function(data) {
		$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
		if (confirm('¿Estás seguro de que editar está cita?')) {

			app.table1.ajax.reload();
			$.ajax({
				url: app.backend + '/editarEX',
				data: JSON.stringify(data),
				method: 'POST',
				dataType: 'json',
				contentType: "application/json; charset=utf-8",
				success: function(json) {
					$("#msg").text('Se editó cita correctamente');
					$("#msg").removeClass('alert-danger').addClass('alert-primary');
					$("#msg").show();
					modaledit.hide();
					app.table1.ajax.reload();
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







	guardarSIN: function(data) {
		console.log(data)
		app.table1.ajax.reload();
		$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
		$.ajax({
			url: app.backend + '/addSIN',
			data: JSON.stringify(data),
			method: 'POST',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			success: function(json) {
				$('#crearIngreso').modal('hide');
				$("#msg").removeClass('alert-danger').addClass('alert-primary');
				$("#msg").text('cita añadida correctamente');
				$("#msg").show();
				modaladd.hide();
				app.table1.ajax.reload();

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

	guardarNuevo: function(data) {
		console.log(data)
		app.table1.ajax.reload();
		$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
		$.ajax({
			url: app.backend + '/addNUE',
			data: JSON.stringify(data),
			method: 'POST',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			success: function(json) {
				$('#crearIngreso').modal('hide');
				$("#msg").removeClass('alert-danger').addClass('alert-primary');
				$("#msg").text('Cita añadida correctamente');
				$("#msg").show();
				modaladd.hide();
				app.table1.ajax.reload();

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

	guardarExistente: function(data) {
		console.log(data)
		app.table1.ajax.reload();
		$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
		$.ajax({
			url: app.backend + '/addEX',
			data: JSON.stringify(data),
			method: 'POST',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			success: function(json) {
				$('#crearIngreso').modal('hide');
				$("#msg").removeClass('alert-danger').addClass('alert-primary');
				$("#msg").text('Cita añadida correctamente');
				$("#msg").show();
				modaladd.hide();
				app.table1.ajax.reload();

			},
			error: function(xhr, status, error) {
				var response = JSON.parse(xhr.responseText);
				var errores = response;

				for (var campo in errores) {

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
		if (confirm('¿Estás seguro de que eliminar esta cita?')) {
			console.log(id)
			app.table1.ajax.reload();
			$.ajax({
				url: app.backend + '/delete/' + id,
				method: 'DELETE',
				success: function(json) {

					$("#msg").text('Se eliminó correctamente');
					$("#msg").removeClass('alert-danger').addClass('alert-primary');
					$("#msg").show();
					app.table1.ajax.reload();
				},
				error: function(error) {
					$("#msg").text('Error al eliminar la cita');
					$("#msg").removeClass('alert-primary').addClass('alert-danger');
					$("#msg").show();
					app.table1.ajax.reload();
				}
			})
		}
	},
	
	
		cargaDa: function(id) {
		if (confirm('Solo se cargaran las citas presentes y futuras que no se encuentren registradas, o que su hora no esten en tramo de otras citas')) {
			console.log(id)
			app.table1.ajax.reload();
			$.ajax({
				url: app.backend + '/cargaAutomatica/' + id,
				method: 'POST',
				success: function(json) {

					$("#msg").text('Se cargaron las citas correctamente');
					$("#msg").removeClass('alert-danger').addClass('alert-primary');
					$("#msg").show();
					modalcarga.hide();
					app.table1.ajax.reload();
				},
				error: function(xhr, textStatus, errorThrown) {
					var response = JSON.parse(xhr.responseText);
					alert(response.message);
					app.table1.ajax.reload();
				}
			})
		}
	},
	
	
			cancelar: function(id) {
		if (confirm('¿Estás seguro de querer cancelar esta cita? ')) {
				console.log(id)
			app.table1.ajax.reload();
			$.ajax({
				url: '/api-revisar' + '/cancelar/' + id,
				method: 'GET',
				success: function(json) {

					$("#msg").text('Se cancelar correctamente');
					$("#msg").show();
			app.table1.ajax.reload();
	
				},
				error: function(error) {
					$("#msg").text('Error al cancelar la cita');
					$("#msg").removeClass('alert-primary').addClass('alert-danger');
					$("#msg").show();
					app.table1.ajax.reload();
			
						}
			})
		}
	},




}








$(document).ready(function() {
	app.init();
});