var app = {
	
	/** creo una variable que contenga la url donde hace la llamada, que lo que hace es iniciar la tabla
	 * la tabla la inicia en la funcion init que es llamada y le pasa el id de la tabla
	 */
  backend: '/api',
  table : null,
    init: function() {
		console.log("entro en la 2")
        app.initDatatable('#citas');
         $("#save").click(function(){
			 
			app.save({
                id: $('#id').val(),
                fecha : $('#fecha').val(),
                hora: $('#hora').val(),
                estado: $('#estado').val(),
                tipo: $('#tipo').val(),
                paciente: null,
                empleado: null,
                factura: null
            });
        });

        
    },
    initDatatable : function(id) {
		console.log(app)
		/**Esto se encarfa de crear la tabla cargando el json que recibe de la bbdd 
		 * que lo recibe en formato json, lo retornas a la llamada etc
		 */
        app.table = $(id).DataTable({
			
			ajax : {
                url : app.backend + '/allcitas',
                dataSrc : function(json) {
                    console.log(json)
                    return json;
                }
            },
             dom: 'Bfrtip',
    columns : [
        {data : "fecha"},
        {data : "hora"},
        {data : "tipo"},
        {data : "estado"},
       { data: "pacientes.nombre", render: function (data) {  return data || "N/A"; } },
        {
            // Agregar una columna para el botón Editar
            data: null,
            render: function (data, type, row) {
                return '<button type="button" class="btn btn-sm btn-outline-primary editar"><i class="bi bi-pencil-fill"></i>'+
                '</button>&nbsp;<button class="btn btn-danger btn-sm" data-id="' + row.id +
                 '"><i class="bi bi-trash-fill"></i></i></button>';
            },
            orderable: false,
            searchable: false
        }
    ],
    buttons: [
         
    ]
});

// Agregar una acción para el botón Editar
$('#citas tbody').on('click', '.btn-sm', function(){
    var data = app.table.row($(this).parents('tr')).data();
    app.setDataToModal(data);
    $('#personaModal').modal();
});
		
		
		
		
		/** Esta función de aqui es para hacer la celda seleccionada o no  */
		 $('#citas tbody').on('click', 'tr', function(){
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
			if (data.pacientes === null || data.pacientes === undefined || data.pacientes === '') {
				$('#paciente').val(null);
				 console.log("hola");
				}else{
					
					 $('#paciente').val(data.pacientes.nombre);
				}
		$('#usuario').val(data.fecha);
        $('#id').val(data.id);
        $('#hora').val(data.hora);
        $('#tipo').val(data.tipo);
        $('#estado').val(data.estado);
       
    },
    
   save : function(data) {
	   var ob="1";
	    app.table.ajax.reload();
	    $('#personaModal').modal('hide');
        $.ajax({
            url: app.backend + '/guarda/'+ob,
            data : JSON.stringify(data),
            method: 'POST',
            dataType : 'json',
            contentType: "application/json; charset=utf-8",
            success : function(json) {
				 console.log("entro en la 2ffff")
                $("#msg").text('Se guardó la persona correctamente');
                $("#msg").show();
                $('#personaModal').modal('hide');
                app.table.ajax.reload();
            },
            error : function(error) {
				console.log("entro en la error");
            }
        })
    }
};




$(document).ready(function(){
	console.log("consola 1")
    app.init();
});