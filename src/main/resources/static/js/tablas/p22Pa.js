var app = {

	/** creo una variable que contenga la url donde hace la llamada, que lo que hace es iniciar la tabla
	 * la tabla la inicia en la funcion init que es llamada y le pasa el id de la tabla
	 */
	backend: '/api-pct',
	table: null,
	init: function() {
		app.initDatatable('#pacientes');
		

		

	
/**Fin cierre modal */


/**Escribir input: cuando el campo input no esta vacío se quita el invalid */

	
	/**FIN esctibir */	
		


	},
	initDatatable: function(id) {
		/**Esto se encarfa de crear la tabla cargando el json que recibe de la bbdd 
		 * que lo recibe en formato json, lo retornas a la llamada etc
		 */
		console.log(id)
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
				
				{ data: "nombre" },
				{ data: "apellidos", render: function (data) {  return data || "N/A"; } },
				{ data: "dni", render: function (data) {  return data || "N/A"; } },
				{ data: "fechaAlta" },
				{ data: "domicilio", render: function (data) {  return data || "N/A"; } },
				{ data: "codigoPostal", render: function (data) {  return data || "N/A"; } },
				{ data: "poblacion", render: function (data) {  return data || "N/A"; } },
				{ data: "telefono" },
				{ data: "sabeDeMi" },
				{ data: "tarifas.tipo", render: function (data) {  return data || "N/A"; } },
			
				{
					// Agregar una columna para el botón Editar
					data: null,
					render: function(data, type, row) {
						return '<button type="button" class="btn btn-sm btn-outline-primary editar"><i class="bi bi-plus-circle"></i></i>' +
							'</button>&nbsp;<button class="btn btn-danger btn-sm2" data-id="' + row.id +
							'"><i class="bi bi-trash-fill"></i></i></button>';
					},
					orderable: false,
					searchable: false
				},
					{
					// Agregar una columna para el botón Editar
					data: null,
					render: function(data, type, row) {
						return '<button type="button" class="btn btn-sm btn-outline-primary editar"><i class="bi bi-plus-circle"></i></i>' +
							'</button>&nbsp;<button class="btn btn-danger btn-sm2" data-id="' + row.id +
							'"><i class="bi bi-trash-fill"></i></i></button>';
					},
					orderable: false,
					searchable: false
				},
			],
			buttons: [
				{
					text: 'Nuevo paciente',
					action: function() {
						confirmarTodo();
					}
				}
			]
		});

		




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
	

	

	
	
	
	
	
	
}








$(document).ready(function() {
	app.init();
});