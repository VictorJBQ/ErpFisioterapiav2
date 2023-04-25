var app = {
	
	/** creo una variable que contenga la url donde hace la llamada, que lo que hace es iniciar la tabla
	 * la tabla la inicia en la funcion init que es llamada y le pasa el id de la tabla
	 */
  backend: '/api',
  table : null,
    init: function() {
        app.initDatatable('#personas');

        
    },
    initDatatable : function(id) {
		/**Esto se encarfa de crear la tabla cargando el json que recibe de la bbdd 
		 * que lo recibe en formato json, lo retornas a la llamada etc
		 */
        app.table = $(id).DataTable({
			ajax : {
                url : app.backend + '/all',
                dataSrc : function(json) {
                    console.log(json)
                    return json;
                }
            },
             dom: 'Bfrtip',
            columns : [
                {data : "id"},
                {data : "fecha"},
                {data : "hora"},
                {data : "tipo"},
                {data : "estado"},
                {data : "empleado"},
                {data : "pacientes"}
            ],
            buttons: [
                {
                    text : 'Editar',
                    action : function(e, dt, node, config) {
                        /**Lo que vamos hacer en editar es buscar los registros que estan seleccionados
						 * para poder trabajar sobre ellos 
						 */
						var data = dt.rows('.table-active').data()[0];
						$('#personaModal').modal();
                    }
                }
            ]
		});
		
		/** Esta función de aqui es para hacer la celda seleccionada o no  */
		 $('#personas tbody').on('click', 'tr', function(){
            if ($(this).hasClass('table-active')) {
                $(this).removeClass('table-active');
            } else {
                app.table.$('tr.table-active').removeClass('table-active');
                $(this).addClass('table-active');
            }
        });
		
		}
		
		
           
    };
    
    

$(document).ready(function(){
    app.init();
});