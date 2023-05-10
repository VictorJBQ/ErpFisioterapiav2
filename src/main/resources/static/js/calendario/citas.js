$(document).ready(function() {
	// Configuración del calendario
	$('#calendario').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,basicWeek,basicDay'
		},

		editable: true,
		eventLimit: true, // allow "more" link when too many events
		lang: 'es', // idioma del calendario
		events: {
			url: '/citas', // URL para obtener los datos de las citas
			success: function(data) {
				// Aquí recibimos el JSON con los datos de las citas y lo procesamos
				var eventos = [];
				$.each(data, function(index, cita) {
					eventos.push({
						title: cita.hora,
						start: cita.fecha,
						end: cita.fecha,
						id: cita.id,
						color: cita.estado == 'libre' ? 'green' : 'red',
						descripcion: cita.empleado
					});
					console.log(eventos);
				});
				// Aquí añadimos los eventos al calendario
				$('#calendario').fullCalendar('removeEvents');
				$('#calendario').fullCalendar('renderEvents', eventos, true);
			},
			error: function() {
				alert('Error al cargar los eventos');
			}
		},
		// Función que se llama cuando se hace click en un evento
		eventClick: function(calEvent, jsEvent, view) {
			console.log(calEvent.id)
			// Aquí debes mostrar el modal y cargar los datos del evento seleccionado
			// Puedes usar la variable calEvent para obtener los datos del evento
			//  alert('Nombre: ' + calEvent.id + '\nEstado: ' + calEvent.descripcion);
			$('#idCita').val(calEvent.id);
			$('#modal1').modal('show');
			$('.select2').select2({
				dropdownParent: $('#modal1 .modal-body')
			});


		}
	});


	
	$('#nomPaciente').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#telPaciente').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		
		$('#modal1').on('hidden.bs.modal', function() {
			$(this).find('input').removeClass('is-invalid');
			$('.invalid-feedback-crear').text(''); // Remueve el contenido del div
				 $('#telPaciente').val(''),
				 $('#nomPaciente').val('');
		});
		
	
		


			$("#asignar").click(function() {
			var opcion = document.querySelector('input[name="pacienteEDIT"]:checked').value;
				
				if(opcion== 'existenteasg'){
						asignar({
					idCita: $('#idCita').val(),
					idPaciente: $('#idPaciente').val(),
					estadoE: $('#estadoE').val(),


				});
				}else if(opcion== 'nuevoasg'){
								asignar2({
					idCita: $('#idCita').val(),
					nomPaciente: $('#nomPaciente').val(),
					telPaciente: $('#telPaciente').val(),
					estadoE: $('#estadoE').val(),


				});
				}

			

		


		//console.log($('#idPaciente').val() + " eeeee")
	});




	function asignar(data) {


			$.ajax({
				url: '/asignarCale',
				data: JSON.stringify(data),
				method: 'POST',
				dataType: 'json',
				contentType: "application/json; charset=utf-8",
				success: function(json) {
					$("#msg").text('Se asigno la cita correctamente');
					$("#msg").show();
					$('#modal1').modal('hide');

					setTimeout(function() {
						location.reload();
					},1000);



				},
				error: function(error) {
					$("#msg").text('Error al asignar cita');
					$("#msg").removeClass('alert-primary').addClass('alert-danger');
					$("#msg").show();

				}
			})
		
	}




	function asignar2(data) {


			$.ajax({
				url: '/asignarCale2',
				data: JSON.stringify(data),
				method: 'POST',
				dataType: 'json',
				contentType: "application/json; charset=utf-8",
				success: function(json) {
					$("#msg").text('Se asigno la cita correctamente');
					$("#msg").show();
					$('#modal1').modal('hide');
					
					setTimeout(function() {
						location.reload();
					},1000);



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
