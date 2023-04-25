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

	const camposAdicionales = document.querySelector('#campos-adicionales');

	const camposAdicionales2 = document.querySelector('#campos-fijo');
	
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
		$('#nomPaciente').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		
		$('#telPaciente').on('input', function () {
   			 $(this).removeClass('is-invalid');
		});
		var idCita;
		var idPaciente;
		var tel;
		var nom;
		if (camposAdicionales.style.display === 'none') {
			// si los campos adicionales están ocultos, obtenemos el valor del select
			idCita = $('#idCita').val();
			idPaciente = $('#idPaciente').val();
			$("#asignar").click(function() {

				asignar({
					idCita: idCita,
					idPaciente: idPaciente,


				});
			});
		} else {
			// si los campos adicionales están visibles, obtenemos los valores de los campos adicionales
			idCita = $('#idCita').val();
			tel = $('#telPaciente').val();
			nom = $('#nomPaciente').val();
			console.log(tel+" dsdfsdfsdfsdfsdfsfs")
			$("#asignar").click(function() {
				console.log(tel)

				asignar2({
					idCita: idCita,
					nomPaciente: nom,
					telPaciente: tel,


				});
			});
		}


		//console.log($('#idPaciente').val() + " eeeee")
	});


	$("#btn-nuevo-paciente").click(function() {



		const btnNuevoPaciente = document.querySelector('#btn-nuevo-paciente');
		btnNuevoPaciente.addEventListener('click', function() {

			if (camposAdicionales.style.display === 'none') {
				camposAdicionales.style.display = 'block';
				camposAdicionales2.style.display = 'none';
				btnNuevoPaciente.textContent = 'Paciente existente';
			} else {

				camposAdicionales.style.display = 'none';
				camposAdicionales2.style.display = 'block';
				btnNuevoPaciente.textContent = 'Nuevo paciente';
				$('#nombre').val('');
			}
		});
	});

	// agregamos un evento al botón "Nuevo paciente" para mostrar/ocultar los campos adicionales



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
				 error: function(error) {
					$("#msg").text('Error, campos vacios');
					$("#msg").removeClass('alert-primary').addClass('alert-danger');
					$('#modal1').modal('hide');
					$("#msg").show();
					 alert("Error, campos cliente nuevo vacío");
					setTimeout(function() {
						location.reload();
					},);
				}
			})
		}
	

});
