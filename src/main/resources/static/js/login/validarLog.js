$(document).ready(function() {
	$('#rest').click(function() {
		console.log($('#token').val())
		var email1 = $('#email1').val();
		var token = $('#token').val();
		var psw1 = $('#psw1').val();
		var ps2 = $('#ps2').val();
		var expiration = $('#expiration').val();
		var data = {
			psw1: psw1,
			ps2: ps2,
			email1: email1,
			token: token,
			expiration: expiration
		};
		var invalidInputs = $('.is-invalid');
		invalidInputs.removeClass('is-invalid');
		invalidInputs.next('.invalid-feedback-crear').remove();

		var forDiv = document.getElementById("for");
		var okDiv = document.getElementById("ok");
		var deprecated = document.getElementById("deprecated");

		$.ajax({
			url: '/api-psw/reset-password',
			data: JSON.stringify(data),
			method: 'POST',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			success: function(json) {

				forDiv.style.display = "none";
				okDiv.style.display = "block";
				deprecated.style.display = "none";
					setTimeout(function() {
								window.location.replace("https://jbsolutions.up.railway.app/login");
							}, 5000);


			},
			error: function(xhr, status, error) {
				var response = JSON.parse(xhr.responseText);
				var errores = response;

				for (var campo in errores) {

					if (errores.hasOwnProperty(campo)) {
						if (campo === 'expirado') {
							forDiv.style.display = "none";
							okDiv.style.display = "none";
							deprecated.style.display = "block";
							setTimeout(function() {
								window.location.replace("https://jbsolutions.up.railway.app/rest");
							}, 5000); // 5000 milisegundos = 5 segundos
							
						} else {
							var input = $('#' + campo);
							input.addClass('is-invalid');
							input.after('<div class="invalid-feedback invalid-feedback-crear">' + errores[campo] + '</div>');
							console.log(errores[campo])
						}

					}
				}

			}
		});
	});
});
