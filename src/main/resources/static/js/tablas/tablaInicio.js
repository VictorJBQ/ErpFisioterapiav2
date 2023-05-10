function mostrarCampos2() {
	var nuevo2 = document.getElementById("campos-nuevos");
	var existente2 = document.getElementById("campos-fijos");
	if (document.querySelector('input[name="pacienteEDIT"]:checked').value == "nuevoasg") {
		nuevo2.style.display = "block";
		existente2.style.display = "none";
	} else if (document.querySelector('input[name="pacienteEDIT"]:checked').value == "existenteasg") {
		nuevo2.style.display = "none";
		existente2.style.display = "block";
	} 
};

var modalter;
var modalcita;
var modalTari;






var app = {

	/** creo una variable que contenga la url donde hace la llamada, que lo que hace es iniciar la tabla
	 * la tabla la inicia en la funcion init que es llamada y le pasa el id de la tabla
	 */
	backend: '/api-inicio',
	table1: null,
	 

	init: function() {
		app.initDatatable('#citas');
	


		
			$("#elegirT").click(function() {
			app.addTarifa($('#idCitaT').val(), $('#tarifaE').val())

		});
		
		$("#terminarT").click(function() {
			app.terminar($('#idCitater').val(), $('#formaPagoT').val())

		});
		
		
					$("#asignar").click(function() {
			var opcion = document.querySelector('input[name="pacienteEDIT"]:checked').value;
				
				if(opcion== 'existenteasg'){
						app.asignar({
					idCita: $('#idCita').val(),
					idPaciente: $('#idPaciente').val(),


				});
				}else if(opcion== 'nuevoasg'){
								app.asignar2({
					idCita: $('#idCita').val(),
					nomPaciente: $('#nomPaciente').val(),
					telPaciente: $('#telPaciente').val(),


				});
				}

			

		


		//console.log($('#idPaciente').val() + " eeeee")
	});
		
		

	},
	initDatatable: function(id) {

		/**Esto se encarfa de crear la tabla cargando el json que recibe de la bbdd 
		 * que lo recibe en formato json, lo retornas a la llamada etc
		 */
		app.table1 = $(id).DataTable({

			ajax: {
				url: app.backend + '/hoy',
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
						var paciente = row.pacientes; 
						var tarifa=null;
						if(paciente!=null){
							tarifa=row.pacientes.tarifas
						}
						var fechaActual = new Date();
						var fechaCita = new Date(row.fecha + " " + row.hora);
						let tiempoActual = fechaActual.getTime();
						let tiempoSumado = tiempoActual - (49 * 60 * 1000);
						let nuevaFecha = new Date(tiempoSumado);
						var dato = row.estado; // obtener el valor de la columna de fecha
						if (dato === 'salvada' || dato === 'terminada') { // agregar una condición para mostrar u ocultar los botones según el valor de la fecha
							buttons = '';
						} else if (dato === 'libre' || dato === 'cancelada') {
							if(fechaCita.getTime() > fechaActual.getTime()){
									buttons += '<button type="button" class="btn btn-smcitar btn-outline-primary editar"><i class="bi bi-plus-circle"></i></i> </button> &nbsp;' +
							 '<button class="btn btn-danger btn-sm2" data-id="' + row.id +
								'"><i class="bi bi-trash-fill"></i></i></button>';
							}else{
									buttons += 
								'<button class="btn btn-danger btn-sm2" data-id="' + row.id +
								'"><i class="bi bi-trash-fill"></i></i></button>';
							}
						
						} else if(dato === 'salvada-pendiente' || dato === 'confirmada') {
							if(tarifa!=null){
								if(fechaCita.getTime() < nuevaFecha.getTime()){
										buttons +='<button class=" btn btn-outline-warning btn-smcancelar " data-id="' + row.id +'"><i class="bi bi-x-square-fill "></i></i></button>&nbsp'
										+ '<button class="btn btn-danger btn-sm2" data-id="' + row.id +
								'"><i class="bi bi-trash-fill"></i></i></button>&nbsp;'+
								'<button type="button" class="btn btn-success btn-smterminar ml-auto">Terminar</button>';
									}else{
										buttons +='<button class=" btn btn-outline-warning btn-smcancelar " data-id="' + row.id +'"><i class="bi bi-x-square-fill "></i></i></button>&nbsp'
										+ '<button class="btn btn-danger btn-sm2" data-id="' + row.id +
								'"><i class="bi bi-trash-fill"></i></i></button>&nbsp;'+
								'<span class="mr-2 text-info" style="text-align: center;">Cita pendiente</span>';
									}
							}else{
								buttons +='<button class=" btn btn-outline-warning btn-smcancelar " data-id="' + row.id +'"><i class="bi bi-x-square-fill "></i></i></button>&nbsp'
								+ '<button class="btn btn-danger btn-sm2" data-id="' + row.id +
								'"><i class="bi bi-trash-fill"></i></i></button>&nbsp;'+
								'<button type="button" class="btn btn-warning btn-smTarifa ml-auto" value="' + row.id + '">Sin tarifa(elegir)</button>';
							}
							
						}else if(dato==='pendiente-Confirmar'|| dato==='reservada'){
							if(fechaCita.getTime() < fechaActual.getTime()){
								if(tarifa!=null){
									buttons += '<button class=" btn btn-outline-warning btn-smcancelar " data-id="' + row.id +'"><i class="bi bi-x-square-fill "></i></i></button>&nbsp'
									 + '<button class="btn btn-danger btn-sm2" data-id="' + row.id +'"><i class="bi bi-trash-fill"></i></i></button>&nbsp;'
									 +'<button type="button" class="btn btn-success btn-smterminar ml-auto">Terminar</button>';
									}else{
												buttons +='<button class=" btn btn-outline-warning btn-smcancelar " data-id="' + row.id +'"><i class="bi bi-x-square-fill "></i></i></button>&nbsp'
												+ '<button class="btn btn-danger btn-sm2" data-id="' + row.id +
										'"><i class="bi bi-trash-fill"></i></i></button>&nbsp;'+
										'<button type="button" class="btn btn-warning btn-smTarifa ml-auto" value="' + row.id + '">Sin tarifa(elegir)</button>'
									}
							}else{
								buttons += '<button class=" btn btn-outline-warning btn-smcancelar " data-id="' + row.id +
							'"><i class="bi bi-x-square-fill "></i></i></button>&nbsp' + '<button class="btn btn-danger btn-sm2" data-id="' + row.id +
									'"><i class="bi bi-trash-fill"></i></i></button>&nbsp;'+
									'<button type="button" class="btn btn-success btn-smtconfirmar ml-auto">Confirmar</button>';
							}
						}
						return buttons;
					},
					orderable: false,
					searchable: false
				},
				{ data: "hora" },
				
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
			
			],
			responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.childRowImmediate,
                type: 'none',
                target: ''
            }
        }
		});


	// Agregar una acción para el botón confirmar
		$('#citas tbody').on('click', '.btn-smtconfirmar', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table1.row($(this).parents('tr')).data();
			app.confirmar(data.id);


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
		$('#citas tbody').on('click', '.btn-smcancelar', function() {
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
	
	// Agregar una acción para el botón elegir tarifa
		$('#citas tbody').on('click', '.btn-smTarifa', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table1.row($(this).parents('tr')).data();
			$('#idCitaT').val(data.pacientes.id);
			var myModalEl = document.getElementById('elegirTarifa')
			modalTari = new bootstrap.Modal(myModalEl)
			modalTari.show();

		});
		
		// Agregar una acción para el botón elegir citar
		$('#citas tbody').on('click', '.btn-smcitar', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table1.row($(this).parents('tr')).data();
			$('#idCita').val(data.id);
			var myModalEl = document.getElementById('modal1')
			modalcita = new bootstrap.Modal(myModalEl)
			modalcita.show();
				$('.select2').select2({
				dropdownParent: $('#modal1 .modal-body')
			});

		});
		
		// Agregar una acción para el botón terminar
		$('#citas tbody').on('click', '.btn-smterminar', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table1.row($(this).parents('tr')).data();
			$('#idCitater').val(data.id);
			var myModalEl = document.getElementById('terminarCita')
			modalter = new bootstrap.Modal(myModalEl)
			modalter.show();

		});




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

	


	eliminar: function(id) {
		if (confirm('¿Estás seguro de que eliminar esta cita?')) {
			console.log(id)
			app.table1.ajax.reload();
			$.ajax({
				url: '/api-ct' + '/delete/' + id,
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
	
	
			cancelar: function(id) {
		if (confirm('¿Estás seguro de querer cancelar esta cita? ')) {
				console.log(id)
			app.table1.ajax.reload();
			$.ajax({
				url: '/api-revisar' + '/cancelar/' + id,
				method: 'GET',
				success: function(json) {

					$("#msg").text('Se canceló correctamente');
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
	
	addTarifa: function(id, tarifa) {

		app.table1.ajax.reload();

		$.ajax({
			url: '/api-revisar' + '/elegir/' + id,
			method: 'POST',
			data: { tf: tarifa },
			success: function(json) {
				modalTari.hide();
				$("#msg").text('Tarifa elegida correctamente');
				$("#msg").show();
				app.table1.ajax.reload();
		
			},
			error: function(xhr, textStatus, errorThrown) {
				var response = JSON.parse(xhr.responseText);
				alert(response.message);
				app.table1.ajax.reload();
			
			}
		})

	},
	
		terminar: function(id, pago) {
		if (confirm('¿Estás seguro de querer terminar esta cita? Una vez terminada no se podrá modificar ni borrar')) {
			app.table1.ajax.reload();
		
			$.ajax({
				url: '/api-revisar'+ '/terminar/' + id,
				method: 'POST',
				data: { fp: pago },
				success: function(json) {
					modalter.hide();
					$("#msg").text('Cita terminada correctamente');
					$("#msg").show();
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

	confirmar: function(id) {
		if (confirm('¿Estás seguro de querer confirmar esta cita? ')) {
		
			app.table1.ajax.reload();
	
			$.ajax({
				url: '/api-revisar'+ '/confirmar/' + id,
				method: 'GET',
				success: function(json) {

					$("#msg").text('Se comfirmó correctamente');
					$("#msg").show();
					app.table1.ajax.reload();
			
				},
				error: function(error) {
					$("#msg").text('Error al confirmar la cita');
					$("#msg").removeClass('alert-primary').addClass('alert-danger');
					$("#msg").show();
					app.table1.ajax.reload();
		
				}
			})
		}
	},
	
	 asignar: function(data) {
		app.table1.ajax.reload();

			$.ajax({
				url: '/asignarCale',
				data: JSON.stringify(data),
				method: 'POST',
				dataType: 'json',
				contentType: "application/json; charset=utf-8",
				success: function(json) {
					$("#msg").text('Se asigno la cita correctamente');
					$("#msg").show();
					modalcita.hide();
					app.table1.ajax.reload();



				},
				error: function(error) {
					$("#msg").text('Error al asignar cita');
					$("#msg").removeClass('alert-primary').addClass('alert-danger');
					$("#msg").show();

				}
			})
		
	},
	
	 asignar2: function(data) {
	app.table1.ajax.reload();

			$.ajax({
				url: '/asignarCale2',
				data: JSON.stringify(data),
				method: 'POST',
				dataType: 'json',
				contentType: "application/json; charset=utf-8",
				success: function(json) {
					$("#msg").text('Se asignó la cita correctamente');
					$("#msg").show();
					modalcita.hide();
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



}




$(document).ready(function() {
	app.init();
	var modalpsw;
	
	$("#abrirmodalps").click(function() {
  var myModalEl = document.getElementById('psw')
  modalpsw = new bootstrap.Modal(myModalEl)
  modalpsw.show();
 
});

/**Cierre modal : Cuando los madales se cierren, ponemos los input a ' ' y quitamos el inalid */
		$('#psw').on('hidden.bs.modal', function() {
			$(this).find('input').removeClass('is-invalid');
			$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
			$('#vieja').val(''),
			$('#nueva').val(''),
			$('#nuevaConfirmada').val('');
		
		});
		
		$('#vieja').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		$('#nueva').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#nuevaConfirmada').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});


$("#cmpsw").click(function() {
			cambiar({
				vieja: $('#vieja').val(),
				nueva: $('#nueva').val(),
				nuevaConfirmada: $('#nuevaConfirmada').val(),
				idEmpleado: $('#idEmpleado').val(),
			});

		});

function cambiar(data) {

		$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
		$.ajax({
			url: '/api-inicio' + '/campsw',
			data: JSON.stringify(data),
			method: 'POST',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			success: function(json) {
				$("#msg").text('Contraseña cambiada correctamente');
				$("#msg").show();
				modalpsw.hide();
				
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

	}



});