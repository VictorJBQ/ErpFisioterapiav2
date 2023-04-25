$(document).ready(function () {
  $("#buscador").on("keyup", function () {
    var filtro = $(this).val();
    $.ajax({
      url: "/buscar-citas?filtro=" + filtro,
      method: "GET",
      success: function (data) {
        console.log(data);
        $("#cuerpoTabla").empty(); 
        data.forEach(function (cita) {
			 
			
         var fila = "<tr>" +
            "<td>" + cita.estado + "</td>" +
            "<td>" + cita.fecha + "</td>" +
            "<td>" + cita.hora + "</td>" +
            "<td>" + cita.tipo + "</td>" +
            "<td>" + cita.empleado+ "</td>" +
            "<td>" +
            '<div class="dropdown">' +
            '<button type="button" class="btn dropdown hide-arrow" data-bs-toggle="dropdown">' +
            '<i class="icon solid fa-ellipsis-vertical"></i>' +
            '</button>' +
            '<div class="dropdown-menu">' +
            '<a class="dropdown-item icon solid fa-pen" href="">Editar</a>' +
            '<a class="dropdown-item icon solid  fa-trash" href="/intranet/citas/citas">Eliminar</a>' +
            '</div>' +
            '</div>' +
            "</td>" +
            "</tr>";
          // Agregamos la fila a la tabla
           $("#cuerpoTabla").append(fila)
        }
       
        );
          
      },
      error: function (error) {
        console.log(error);
      },
    });
  });
});

function borrarCita(idCita) {
    if (confirm("¿Está seguro que desea borrar esta cita?")) {
        fetch("/" + idCita, {
            method: "DELETE"
        }).then(response => {
            if (response.ok) {
                alert("La cita ha sido borrada exitosamente.");
                window.location.reload();
            } else {
                alert("Ha ocurrido un error al intentar borrar la cita.");
            }
        });
    }
}

