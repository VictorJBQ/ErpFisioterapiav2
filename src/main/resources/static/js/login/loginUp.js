$(document).ready(function() {
	$('#reseteo').click(function() {
		var email = $('#email').val();
		var data = { email: email };
		var invalidInputs = $('.is-invalid');
		invalidInputs.removeClass('is-invalid');
		invalidInputs.next('.invalid-feedback-crear').remove();

		var forDiv = document.getElementById("for");
		var okDiv = document.getElementById("ok");

		$.ajax({
			url: '/api-psw/rest',
			data: JSON.stringify(data),
			method: 'POST',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			success: function(json) {
			
					forDiv.style.display = "none";
					okDiv.style.display = "block";
				
				

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
		});
	});
});
