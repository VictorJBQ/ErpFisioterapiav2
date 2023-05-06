function mostrarCampos(valor) {
	var pendientes = document.getElementById("tablaPendientes");
	var reserva = document.getElementById("tablaReserva");
	var terminar = document.getElementById("tablaTerminar");
	var hoy = document.getElementById("tablaHoy");
	if (valor == 'pendiente') {
		pendientes.style.display = "block";
		reserva.style.display = "none";
		terminar.style.display = "none";
		hoy.style.display = "none";
	} else if (valor == 'reserva') {
		pendientes.style.display = "none";
		reserva.style.display = "block";
		terminar.style.display = "none";
		hoy.style.display = "none";
	} else if (valor == 'terminar') {
		pendientes.style.display = "none";
		reserva.style.display = "none";
		terminar.style.display = "block";
		hoy.style.display = "none";
	} else if (valor == 'hoy') {
		pendientes.style.display = "none";
		reserva.style.display = "none";
		terminar.style.display = "none";
		hoy.style.display = "block";
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
		console.log(12141)
		nuevo2.style.display = "none";
		existente2.style.display = "block";
	}
}


var modalter;
var modaledit;
var modalTari;
var app = {

	/** creo una variable que contenga la url donde hace la llamada, que lo que hace es iniciar la tabla
	 * la tabla la inicia en la funcion init que es llamada y le pasa el id de la tabla
	 */
	backend: '/api-revisar',
	table1: null,
	table2: null,
	table3: null,
	table4: null,

	init: function() {
		app.initDatatable('#citas');
		app.initDatatable2('#citas2');
		app.initDatatable3('#citas3');
		app.initDatatable4('#citas4');

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

		$("#terminarT").click(function() {
			app.terminar($('#idCita').val(), $('#formaPagoT').val())

		});

		$("#elegirT").click(function() {
			app.addTarifa($('#idCitaT').val(), $('#tarifaE').val())

		});




		$('#editarCita').on('hidden.bs.modal', function() {
			$(this).find('input').removeClass('is-invalid');
			$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
			$('#nombreE').val('');
			$('#telefonoE').val('');

		});

		/**Fin cierre modal */


		/**Escribir input: cuando el campo input no esta vacío se quita el invalid */



	},
	initDatatable: function(id) {

		/**Esto se encarfa de crear la tabla cargando el json que recibe de la bbdd 
		 * que lo recibe en formato json, lo retornas a la llamada etc
		 */
		app.table1 = $(id).DataTable({

			ajax: {
				url: app.backend + '/allRecordar',
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
						var dato = row.pacientes.tarifas; // obtener el valor de la columna de fecha

						return '&nbsp&nbsp;<input type="checkbox" class="form-check-input" value="' + row.id + '" style="max-width: 30px;">';


					},
					orderable: false,
					searchable: false
				},
					{
					// Agregar una columna para el botón Editar
					data: null,
					render: function(data, type, row) {
						return '<button type="button" class="btn btn-sm btn-outline-success editar"><i class="bi bi-check-square-fill"></i>' +
							'</button>&nbsp;<button class=" btn btn-outline-warning btn-smXX " data-id="' + row.id +
							'"><i class="bi bi-x-square-fill "></i></i></button>' + '&nbsp;<button class="btn btn-danger btn-sm2" data-id="' + row.id +
							'"><i class="bi bi-trash-fill"></i></i></button>';
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
				}
			
			
			],
			buttons: [
				{
					text: 'Confirmar seleccionadas',
					action: function() {
						confirmarTodo();
					}
				},
				{
					text: 'Confirmar todas',
					action: function() {
						confirmar2();
					}
				}
			],
					responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.childRowImmediate,
                type: 'none',
                target: ''
            },

        }
		
		});

		// Agregar una acción para el botón confirmar
		$('#citas tbody').on('click', '.btn-sm', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table1.row($(this).parents('tr')).data();
			app.confirmar(data.id);


		});
		// Agregar una acción para el botón cancelar
		$('#citas tbody').on('click', '.btn-smXX', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table1.row($(this).parents('tr')).data();
			var ce = data.estado;
			var pa = data.pacientes;
			if ((ce != 'terminada' && ce != 'salvada') && pa != null) {
				app.cancelar(data.id)
			} else {
				alert("No puedes cancelar una cita terminada o sin paciente");
			}

		});

		// Agregar una acción para el botón eliminar
		$('#citas tbody').on('click', '.btn-sm2', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table1.row($(this).parents('tr')).data();
			app.eliminar(data.id)

		});


		/**Para crear registros nuevos */
		function confirmarTodo() {
			$("#msg").text('').removeClass('alert')
			var ids = [];
			$('input[type="checkbox"]:checked').each(function() {
				ids.push($(this).val());
			});
			if (ids.length === 0) {
				alert("No hay citas seleccionadas");
			} else {
				if (confirm('¿Estás seguro de querer confirmar estas citas? ')) {
					for (var i = 0; i < ids.length; i++) {
						app.confirmarSelc(ids[i]);
					}
					$("#msg").text('Se comfirmo correctamente');
					$("#msg").show();

				}
			}




		}
		
		function confirmar2() {
			$("#msg").text('').removeClass('alert')
			var ids = [];
			app.table1.rows().every(function() {
				var data = this.data();
				ids.push(data.id);
				
			});
			if (ids.length === 0) {
				alert("No hay citas disponibles");
			} else {
				if (confirm('¿Estás seguro de querer confirmar estas citas? ')) {
					for (var i = 0; i < ids.length; i++) {
						app.confirmarSelc(ids[i]);
					}
					$("#msg").text('Se comfirmo correctamente');
					$("#msg").show();

				}
			}




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

	initDatatable2: function(id) {

		/**Esto se encarfa de crear la tabla cargando el json que recibe de la bbdd 
		 * que lo recibe en formato json, lo retornas a la llamada etc
		 */
		app.table2 = $(id).DataTable({

			ajax: {
				url: app.backend + '/allReserva',
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
						var dato = row.pacientes.tarifas; // obtener el valor de la columna de fecha

						return '&nbsp&nbsp;<input type="checkbox" class="form-check-input" value="' + row.id + '" style="max-width: 30px;">';




					},
					orderable: false,
					searchable: false
				},
				{
					// Agregar una columna para el botón Editar
					data: null,
					render: function(data, type, row) {
						return '<button t&nbsp;ype="button" class="btn btn-smre btn-outline-success editar"><i class="bi bi-check-square-fill"></i>' +
							'</button><button class=" btn btn-outline-warning btn-smcare " data-id="' + row.id +
							'"><i class="bi bi-x-square-fill "></i></i></button>' + '&nbsp;<button class="btn btn-danger btn-smre2" data-id="' + row.id +
							'"><i class="bi bi-trash-fill"></i></i></button>';
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
				}
				
				
			],
			buttons: [
				{
					text: 'Actualizar seleccionadas',
					action: function() {
						reserva();
					}
				},
				{
					text: 'Actualizar todas',
					action: function() {
						reserva2();
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

		// Agregar una acción para el botón confirmar
		$('#citas2 tbody').on('click', '.btn-smre', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table2.row($(this).parents('tr')).data();
			app.confirmar(data.id);


		});

		// Agregar una acción para el botón cancelar
		$('#citas2 tbody').on('click', '.btn-smcare', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table2.row($(this).parents('tr')).data();
			var ce = data.estado;
			var pa = data.pacientes;
			if ((ce != 'terminada' && ce != 'salvada') && pa != null) {
				app.cancelar(data.id)
			} else {
				alert("No puedes cancelar una cita terminada o sin paciente");
			}

		});



		// Agregar una acción para el botón eliminar
		$('#citas2 tbody').on('click', '.btn-smre2', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table2.row($(this).parents('tr')).data();
			app.eliminar(data.id)

		});


		/*confirmar reserva seleccionados*/
		function reserva() {
			$("#msg").text('').removeClass('alert')
			var ids = [];
			$('input[type="checkbox"]:checked').each(function() {
				ids.push($(this).val());
			});
			if (ids.length === 0) {
				alert("No hay citas seleccionadas");
			} else {
				if (confirm('¿Estás seguro de querer confirmar estas citas? ')) {
					for (var i = 0; i < ids.length; i++) {
						app.confirmarSelc(ids[i]);
					}
					$("#msg").text('Se comfirmo correctamente');
					$("#msg").show();

				}
			}




		}

		function reserva2() {
			$("#msg").text('').removeClass('alert')
			var ids = [];
			app.table2.rows().every(function() {
				var data = this.data();
				ids.push(data.id);
			
			});
			if (ids.length === 0) {
				alert("No hay citas disponibles");
			} else {
				if (confirm('¿Estás seguro de querer confirmar estas citas? ')) {
					for (var i = 0; i < ids.length; i++) {
						app.confirmarSelc(ids[i]);
					}
					$("#msg").text('Se comfirmo correctamente');
					$("#msg").show();

				}
			}




		}




		/** Esta función de aqui es para hacer la celda seleccionada o no  */
		$('#citas2 tbody').on('click', 'tr', function() {
			if ($(this).hasClass('table-active')) {
				$(this).removeClass('table-active');
				$(this).css('background-color', null);
			} else {
				app.table2.$('tr.table-active').removeClass('table-active');
				$(this).addClass('table-active');

			}
		});


	},


	/**tabla 3 */
	initDatatable3: function(id) {

		/**Esto se encarfa de crear la tabla cargando el json que recibe de la bbdd 
		 * que lo recibe en formato json, lo retornas a la llamada etc
		 */
		app.table3 = $(id).DataTable({

			ajax: {
				url: app.backend + '/allTerminar',
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
        var fechaActual = new Date();
        var fechaCita = new Date(row.fecha + " " + row.hora);
        var dato = row.pacientes.tarifas; // obtener el valor de la columna de fecha
        if (dato != null) { // agregar una condición para mostrar u ocultar los botones según el valor de la fecha
            if (fechaCita.getTime() < fechaActual.getTime()) {
                return '&nbsp;<input type="checkbox" class="form-check-input" value="' + row.id + '" style="max-width: 30px; margin: 0 auto; display: block;">';
            } else {
                return '<span class="mr-2 text-info" style="text-align: center;">Cita pendiente</span>';
            }


        } else {
            return '<button type="button" class="btn btn-warning btn-smTarifa ml-auto" value="' + row.id + '">Elegir tarifa</button>';
        }
        ;
    },
    orderable: false,
    searchable: false
				},
					{
					// Agregar una columna para el botón Editar
					data: null,
					render: function(data, type, row) {

						var fechaActual = new Date();
						var fechaCita = new Date(row.fecha + " " + row.hora);
						var buttons = '';
						if (fechaCita.getTime() < fechaActual.getTime() && row.pacientes.tarifas != null) { // agregar una condición para mostrar u ocultar los botones según el valor de la fecha
							return '<button type="button" class="btn btn-smETC btn-outline-success editar"><i class="bi bi-check-square-fill"></i>' +
								'</button>&nbsp;<button class=" btn btn-outline-warning btn-smCANC " data-id="' + row.id +
								'"><i class="bi bi-x-square-fill "></i></i></button>' + '&nbsp;<button class="btn btn-danger btn-smETB" data-id="' + row.id +
								'"><i class="bi bi-trash-fill"></i></i></button>';
						} else {
							return '<button class=" btn btn-outline-warning btn-smCANC " data-id="' + row.id +
								'"><i class="bi bi-x-square-fill "></i></i></button>' + '&nbsp;<button class="btn btn-danger btn-smETB" data-id="' + row.id +
								'"><i class="bi bi-trash-fill"></i></i></button>';
						}
						
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
				}
			

				
			],
			buttons: [
				{
					text: 'Terminar seleccionadas(forma de pago efectivo)',
					action: function() {
						confirmarTodoEFECTIVO();
					}
				},
				{
					text: 'Terminar seleccionadas(forma de pago Bizum)',
					action: function() {
						confirmarTodoBIZUM();
					}
				},
				{
					text: 'Terminar TODAS(forma de pago efectivo)',
					action: function() {
						confirmarTodoEFECTIVOtodas();
					}
				},
				{
					text: 'Terminar TODAS(forma de pago Bizum)',
					action: function() {
						confirmarTodoBIZUMtodas();
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

		// Agregar una acción para el botón terminar
		$('#citas3 tbody').on('click', '.btn-smETC', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table3.row($(this).parents('tr')).data();
			$('#idCita').val(data.id);
			var myModalEl = document.getElementById('terminarCita')
			modalter = new bootstrap.Modal(myModalEl)
			modalter.show();

		});


		// Agregar una acción para el botón elegir tarifa
		$('#citas3 tbody').on('click', '.btn-smTarifa', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table3.row($(this).parents('tr')).data();
			$('#idCitaT').val(data.pacientes.id);
			var myModalEl = document.getElementById('elegirTarifa')
			modalTari = new bootstrap.Modal(myModalEl)
			modalTari.show();

		});

		// Agregar una acción para el botón eliminar
		$('#citas3 tbody').on('click', '.btn-smETB', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table3.row($(this).parents('tr')).data();
			app.eliminar(data.id)

		});

		// Agregar una acción para el botón cancelar
		$('#citas3 tbody').on('click', '.btn-smCANC', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table3.row($(this).parents('tr')).data();
			var ce = data.estado;
			var pa = data.pacientes;
			if ((ce != 'terminada' && ce != 'salvada') && pa != null) {
				app.cancelar(data.id)
			} else {
				alert("No puedes cancelar una cita terminada o sin paciente");
			}

		});


		/**Para crear registros nuevos */
		function confirmarTodoEFECTIVO() {
			$("#msg").text('').removeClass('alert')
			var ids = [];
			$('input[type="checkbox"]:checked').each(function() {
				ids.push($(this).val());
			});
			if (ids.length === 0) {
				alert("No hay citas disponibles para terminar");
			} else {
				if (confirm('¿Estás seguro de querer terminar estas citas? Una vez terminada no se podra editar ni borrar ')) {
					for (var i = 0; i < ids.length; i++) {
						app.terminarSelect(ids[i], 'Efectivo,al contado');
					}
					$("#msg").text('Se terminaron correctamente');
					$("#msg").show();

				}
			}




		}
		
			/**Para crear registros nuevos */
		function confirmarTodoEFECTIVOtodas() {
			$("#msg").text('').removeClass('alert')
			var ids = [];
			
			app.table3.rows().every(function() {
				var data = this.data();
				var fechaActual = new Date();
				var fechaCita = new Date(data.fecha + " " + data.hora);
				
				if(data.pacientes.tarifas!=null && fechaCita.getTime() < fechaActual.getTime()){
					ids.push(data.id);
				}
				
			
			});
			if (ids.length === 0) {
				alert("No hay citas disponibles para terminar");
			} else {
				if (confirm('¿Estás seguro de querer terminar estas citas? Una vez terminada no se podra editar ni borrar ')) {
					for (var i = 0; i < ids.length; i++) {
						app.terminarSelect(ids[i], 'Efectivo,al contado');
					}
					$("#msg").text('Se terminaron correctamente');
					$("#msg").show();

				}
			}




		}

		function confirmarTodoBIZUM() {
			$("#msg").text('').removeClass('alert')
			var ids = [];
			$('input[type="checkbox"]:checked').each(function() {
				ids.push($(this).val());
			});
			if (ids.length === 0) {
				alert("No hay citas seleccionadas");
			} else {
				if (confirm('¿Estás seguro de querer terminar estas citas? Una vez terminada no se podra editar ni borrar ')) {
					for (var i = 0; i < ids.length; i++) {
						app.terminarSelect(ids[i], 'Bizum');
					}
					$("#msg").text('Se terminaron correctamente');
					$("#msg").show();

				}
			}




		}
		
		function confirmarTodoBIZUMtodas() {
			$("#msg").text('').removeClass('alert')
			var ids = [];
			
			app.table3.rows().every(function() {
				var data = this.data();
				var fechaActual = new Date();
				var fechaCita = new Date(data.fecha + " " + data.hora);
				
				if(data.pacientes.tarifas!=null && fechaCita.getTime() < fechaActual.getTime()){
					ids.push(data.id);
				}
				
			
			});
			if (ids.length === 0) {
				alert("No hay citas seleccionadas");
			} else {
				if (confirm('¿Estás seguro de querer terminar estas citas? Una vez terminada no se podra editar ni borrar ')) {
					for (var i = 0; i < ids.length; i++) {
						app.terminarSelect(ids[i], 'Bizum');
					}
					$("#msg").text('Se terminaron correctamente');
					$("#msg").show();

				}
			}




		}




		/** Esta función de aqui es para hacer la celda seleccionada o no  */
		$('#citas3 tbody').on('click', 'tr', function() {
			if ($(this).hasClass('table-active')) {
				$(this).removeClass('table-active');
				$(this).css('background-color', null);
			} else {
				app.table3.$('tr.table-active').removeClass('table-active');
				$(this).addClass('table-active');

			}
		});


	},

	initDatatable4: function(id) {

		/**Esto se encarfa de crear la tabla cargando el json que recibe de la bbdd 
		 * que lo recibe en formato json, lo retornas a la llamada etc
		 */
		app.table4 = $(id).DataTable({

			ajax: {
				url: app.backend + '/allActuales',
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
							return '<button type="button" class="btn btn-smEdit btn-outline-primary editar"><i class="bi bi-pencil-fill"></i>' +
								'</button>&nbsp;<button class="btn btn-danger btn-sm4" data-id="' + row.id +
								'"><i class="bi bi-trash-fill"></i></i></button>';
						} else {
							return  '<button type="button" class="btn btn-smEdit btn-outline-primary editar"><i class="bi bi-pencil-fill"></i>' +
								'</button>&nbsp;<button class=" btn btn-outline-warning btn-smCANCEL " data-id="' + row.id +
								'"><i class="bi bi-x-square-fill "></i></i></button>' + '&nbsp;<button class="btn btn-danger btn-sm4" data-id="' + row.id +
								'"><i class="bi bi-trash-fill"></i></i></button>';
						}
						
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
				}
				
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
		$('#citas4 tbody').on('click', '.btn-smEdit', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table4.row($(this).parents('tr')).data();
			console.log(data)
			app.setDataToModal(data);
			var myModalEl = document.getElementById('editarCita')
			modaledit = new bootstrap.Modal(myModalEl)
			modaledit.show();
			$('.select2').select2({
				dropdownParent: $('#editarCita .modal-body')
			});

		});

		// Agregar una acción para el botón eliminar
		$('#citas4 tbody').on('click', '.btn-sm4', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table4.row($(this).parents('tr')).data();
			var ce = data.estado;
			if ((ce != 'terminada' && ce != 'salvada') && pa != null) {
				app.cancelar(data.id)
			} else {
				alert("No puedes cancelar una cita terminada o sin paciente");
			}


		});

		// Agregar una acción para el botón cancelar
		$('#citas4 tbody').on('click', '.btn-smCANCEL', function() {
			$("#msg").text('').removeClass('alert')
			var data = app.table4.row($(this).parents('tr')).data();
			var ce = data.estado;
			var pa = data.pacientes;
			if ((ce != 'terminada' && ce != 'salvada') && pa != null) {
				app.cancelar(data.id)
			} else {
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




		/** Esta función de aqui es para hacer la celda seleccionada o no  */
		$('#citas4 tbody').on('click', 'tr', function() {
			if ($(this).hasClass('table-active')) {
				$(this).removeClass('table-active');
				$(this).css('background-color', null);
			} else {
				app.table4.$('tr.table-active').removeClass('table-active');
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

		} else {
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
			console.log(data.estado)
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
			app.table2.ajax.reload();
			app.table3.ajax.reload();
			app.table4.ajax.reload();
			$.ajax({
				url: '/api-ct' + '/editarSIN',
				data: JSON.stringify(data),
				method: 'POST',
				dataType: 'json',
				contentType: "application/json; charset=utf-8",
				success: function(json) {
					$("#msg").text('Se editó cita correctamente');
					$("#msg").show();
					modaledit.hide();
					app.table1.ajax.reload();
					app.table2.ajax.reload();
					app.table3.ajax.reload();
					app.table4.ajax.reload();
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
			app.table2.ajax.reload();
			app.table3.ajax.reload();
			app.table4.ajax.reload();
			$.ajax({
				url: '/api-ct' + '/editarNV',
				data: JSON.stringify(data),
				method: 'POST',
				dataType: 'json',
				contentType: "application/json; charset=utf-8",
				success: function(json) {
					setTimeout(function() {
						location.reload();
					},);
					$("#msg").text('Se editó cita correctamente');
					$("#msg").show();
					modaledit.hide();
					app.table1.ajax.reload();
					app.table2.ajax.reload();
					app.table3.ajax.reload();
					app.table4.ajax.reload();
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
			app.table2.ajax.reload();
			app.table3.ajax.reload();
			app.table4.ajax.reload();
			$.ajax({
				url: '/api-ct' + '/editarEX',
				data: JSON.stringify(data),
				method: 'POST',
				dataType: 'json',
				contentType: "application/json; charset=utf-8",
				success: function(json) {
					$("#msg").text('Se editó cita correctamente');
					$("#msg").show();
					modaledit.hide();
					app.table1.ajax.reload();
					app.table2.ajax.reload();
					app.table3.ajax.reload();
					app.table4.ajax.reload();
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









	eliminar: function(id) {
		if (confirm('¿Estás seguro de que eliminar esta cita?')) {
			console.log(id)
			app.table1.ajax.reload();
			app.table2.ajax.reload();
			app.table3.ajax.reload();
			app.table4.ajax.reload();
			$.ajax({
				url: '/api-ct' + '/delete/' + id,
				method: 'DELETE',
				success: function(json) {

					$("#msg").text('Se eliminó correctamente');
					$("#msg").show();
					app.table1.ajax.reload();
					app.table2.ajax.reload();
					app.table3.ajax.reload();
					app.table4.ajax.reload();
				},
				error: function(error) {
					$("#msg").text('Error al eliminar la cita');
					$("#msg").removeClass('alert-primary').addClass('alert-danger');
					$("#msg").show();
					app.table1.ajax.reload();
					app.table2.ajax.reload();
					app.table3.ajax.reload();
					app.table4.ajax.reload();
				}
			})
		}
	},


	terminar: function(id, pago) {
		if (confirm('¿Estás seguro de querer terminar esta cita? Una vez terminada no se podrá modificar ni borrar')) {
			console.log(id)
			app.table1.ajax.reload();
			app.table2.ajax.reload();
			app.table3.ajax.reload();
			app.table4.ajax.reload();
			$.ajax({
				url: app.backend + '/terminar/' + id,
				method: 'POST',
				data: { fp: pago },
				success: function(json) {
					modalter.hide();
					$("#msg").text('Cita terminada correctamente');
					$("#msg").show();
					app.table1.ajax.reload();
					app.table2.ajax.reload();
					app.table3.ajax.reload();
					app.table4.ajax.reload();
				},
				error: function(xhr, textStatus, errorThrown) {
					var response = JSON.parse(xhr.responseText);
					alert(response.message);
					app.table1.ajax.reload();
					app.table2.ajax.reload();
					app.table3.ajax.reload();
					app.table4.ajax.reload();
				}
			})
		}
	},

	addTarifa: function(id, tarifa) {

		console.log(id)
		app.table1.ajax.reload();
		app.table2.ajax.reload();
		app.table3.ajax.reload();
		app.table4.ajax.reload();
		$.ajax({
			url: app.backend + '/elegir/' + id,
			method: 'POST',
			data: { tf: tarifa },
			success: function(json) {
				modalTari.hide();
				$("#msg").text('Tarifa elegida correctamente');
				$("#msg").show();
				app.table1.ajax.reload();
				app.table2.ajax.reload();
				app.table3.ajax.reload();
				app.table4.ajax.reload();
			},
			error: function(xhr, textStatus, errorThrown) {
				var response = JSON.parse(xhr.responseText);
				alert(response.message);
				app.table1.ajax.reload();
				app.table2.ajax.reload();
				app.table3.ajax.reload();
				app.table4.ajax.reload();
			}
		})

	},

	terminarSelect: function(id, pago) {

		console.log(id)
		app.table1.ajax.reload();
		app.table2.ajax.reload();
		app.table3.ajax.reload();
		app.table4.ajax.reload();
		$.ajax({
			url: app.backend + '/terminar/' + id,
			method: 'POST',
			data: { fp: pago },
			success: function(json) {

				app.table1.ajax.reload();
				app.table2.ajax.reload();
				app.table3.ajax.reload();
				app.table4.ajax.reload();
			},
			error: function(xhr, textStatus, errorThrown) {
				var response = JSON.parse(xhr.responseText);
				alert(response.message);
				app.table1.ajax.reload();
				app.table2.ajax.reload();
				app.table3.ajax.reload();
				app.table4.ajax.reload();
			}
		})

	},


	confirmar: function(id) {
		if (confirm('¿Estás seguro de querer confirmar esta cita? ')) {
			console.log(id)
			app.table1.ajax.reload();
			app.table2.ajax.reload();
			app.table3.ajax.reload();
			app.table4.ajax.reload();
			$.ajax({
				url: app.backend + '/confirmar/' + id,
				method: 'GET',
				success: function(json) {

					$("#msg").text('Se comfirmo correctamente');
					$("#msg").show();
					app.table1.ajax.reload();
					app.table2.ajax.reload();
					app.table3.ajax.reload();
					app.table4.ajax.reload();
				},
				error: function(error) {
					$("#msg").text('Error al confirmar la cita');
					$("#msg").removeClass('alert-primary').addClass('alert-danger');
					$("#msg").show();
					app.table1.ajax.reload();
					app.table2.ajax.reload();
					app.table3.ajax.reload();
					app.table4.ajax.reload();
				}
			})
		}
	},


	confirmarSelc: function(id) {

		app.table1.ajax.reload();
		app.table2.ajax.reload();
		app.table3.ajax.reload();
		app.table4.ajax.reload();
		$.ajax({
			url: app.backend + '/confirmar/' + id,
			method: 'GET',
			success: function(json) {

				app.table1.ajax.reload();
				app.table2.ajax.reload();
				app.table3.ajax.reload();
				app.table4.ajax.reload();
			},
			error: function(error) {
				$("#msg").text('Error al confirmar la cita');
				$("#msg").removeClass('alert-primary').addClass('alert-danger');
				$("#msg").show();
				app.table1.ajax.reload();
				app.table2.ajax.reload();
				app.table3.ajax.reload();
				app.table4.ajax.reload();
			}
		})

	},

	cancelar: function(id) {
		if (confirm('¿Estás seguro de querer cancelar esta cita? ')) {
			console.log(id)
			app.table1.ajax.reload();
			app.table2.ajax.reload();
			app.table3.ajax.reload();
			app.table4.ajax.reload();
			$.ajax({
				url: app.backend + '/cancelar/' + id,
				method: 'GET',
				success: function(json) {

					$("#msg").text('Se cancelar correctamente');
					$("#msg").show();
					app.table1.ajax.reload();
					app.table2.ajax.reload();
					app.table3.ajax.reload();
					app.table4.ajax.reload();
				},
				error: function(error) {
					$("#msg").text('Error al cancelar la cita');
					$("#msg").removeClass('alert-primary').addClass('alert-danger');
					$("#msg").show();
					app.table1.ajax.reload();
					app.table2.ajax.reload();
					app.table3.ajax.reload();
					app.table4.ajax.reload();
				}
			})
		}
	},

	confirmarSelec: function(ids) {
		if (confirm('¿Estás seguro de querer confirmar todas estas citas')) {
			console.log(ids)
			app.table1.ajax.reload();
			app.table2.ajax.reload();
			app.table3.ajax.reload();
			app.table4.ajax.reload();
			$.ajax({
				url: app.backend + '/confirmarSelec',
				method: 'POST',
				data: JSON.stringify({ ids: ids }),
				contentType: 'application/json',
				success: function(json) {
					modalter.hide();
					$("#msg").text('Citas confirmadas correctamente');
					$("#msg").show();
					app.table1.ajax.reload();
					app.table2.ajax.reload();
					app.table3.ajax.reload();
					app.table4.ajax.reload();
				},
				error: function(xhr, textStatus, errorThrown) {
					var response = JSON.parse(xhr.responseText);
					alert(response.message);
					app.table1.ajax.reload();
					app.table2.ajax.reload();
					app.table3.ajax.reload();
					app.table4.ajax.reload();
				}
			})
		}
	},




}








$(document).ready(function() {
	app.init();
});