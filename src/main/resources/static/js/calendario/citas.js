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

estado: cita.estado,

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

const select = document.getElementById("estadoE"); // seleccionar el elemento select

const confirmada = select.querySelector("option[value='confirmada']"); // seleccionar la opción 'cancelada'

const reservada = select.querySelector("option[value='reservada']"); // seleccionar la opción 'cancelada'

const pendiente = select.querySelector("option[value='pendiente-Confirmar']"); // seleccionar la opción 'cancelada'

var body = document.getElementById("cuerpo");

var borr = document.getElementById("borrar");

var sele = document.getElementById("exampleModalLabel");

var botonAsignar = document.getElementById("asignar");

$('#idCita').val(calEvent.id);

if (calEvent.estado != 'libre') {

body.style.display = "block";

borr.style.display = "none";

sele.style.display = "block";

botonAsignar.style.display = "block";

select.value = 'confirmada';

confirmada.style.display = "block";

reservada.style.display = "none";

pendiente.style.display = "none";

} else {

body.style.display = "block";

borr.style.display = "none";

sele.style.display = "block";

botonAsignar.style.display = "block";

select.value = 'pendiente-Confirmar';

confirmada.style.display = "block";

reservada.style.display = "block";

pendiente.style.display = "block";

}

$('#modal1').modal('show');

$('.select2').select2({

dropdownParent: $('#modal1 .modal-body')

});

}

});

$('#calendario2').fullCalendar({

header: {

left: 'prev,next today',

center: 'title',

right: 'month,basicWeek,basicDay'

},

editable: true,

eventLimit: true, // allow "more" link when too many events

lang: 'es', // idioma del calendario

events: {

url: '/citasPendientes', // URL para obtener los datos de las citas

success: function(data) {

// Aquí recibimos el JSON con los datos de las citas y lo procesamos

var eventos = [];

$.each(data, function(index, cita) {

var color;

var textColor = '#FFFFFF'; // Color de texto negro

if (cita.estado == 'pendiente-Confirmar') {

color = '#FFFF00';

textColor = '#000000';

} else if (cita.estado == 'reservada') {

color = '#000000';

} else if (cita.estado == 'confirmada') {

color = '#17A2B8';

} else if (cita.estado == 'salvada-pendiente') {

color = '#17A2B8';

} else if (cita.estado == 'terminada' || cita.estado == "salvada") {

color = 'blue';

} else if (cita.estado == "cancelada") {

color = 'red';

} else if (cita.estado == "libre") {

color = 'green';

}

var tarifa = cita.pacientes

var idPaciente = cita.pacientes;

if (tarifa != null) {

idPaciente = cita.pacientes.id;

if (cita.pacientes.tarifas === null) {

tarifa = 'NA';

} else {

tarifa = cita.pacientes.tarifas.tipo;

}

} else {

tarifa = 'NA'

idPaciente = 'NA'

}

var titulo = cita.pacientes ? cita.hora + " " + cita.pacientes.nombre + " " + cita.pacientes.apellidos : cita.hora + " libre";

eventos.push({

title: titulo,

start: cita.fecha,

end: cita.fecha,

id: cita.id,

fecha: cita.fecha,

estado: cita.estado,

hora: cita.hora,

tarifa: tarifa,

paciente: idPaciente,

color: color,

textColor: textColor, // Color de texto negro

descripcion: cita.empleado

});

});

// Aquí añadimos los eventos al calendario

$('#calendario2').fullCalendar('removeEvents');

$('#calendario2').fullCalendar('renderEvents', eventos, true);

},

error: function() {

alert('Error al cargar los eventos');

}

},

// Función que se llama cuando se hace click en un evento

eventClick: function(calEvent, jsEvent, view) {

const select = document.getElementById("estadoE"); // seleccionar el elemento select

const confirmada = select.querySelector("option[value='confirmada']"); // seleccionar la opción 'cancelada'

const reservada = select.querySelector("option[value='reservada']"); // seleccionar la opción 'cancelada'

const pendiente = select.querySelector("option[value='pendiente-Confirmar']"); // seleccionar la opción 'cancelada'

var body = document.getElementById("cuerpo");

var borr = document.getElementById("borrar");

var sele = document.getElementById("exampleModalLabel");

var botonAsignar = document.getElementById("asignar");

var fechaActual = new Date();

var fechaComparar = new Date(calEvent.fecha + " " + calEvent.hora);

var tarifa = document.getElementById("tarifaO");

var pago = document.getElementById("pagoO");

var label = document.getElementById("label");

var botonTerminar = document.getElementById("terminarT");

console.log(calEvent.paciente)

if (calEvent.estado == 'terminada' || calEvent.estado == "salvada") {

alert("Cita no editable")

} else if (calEvent.estado == 'pendiente-Confirmar' || calEvent.estado == 'reservada') {

if (fechaComparar < fechaActual) {

if (calEvent.tarifa == 'NA') {

tarifa.style.display = "block";

}

$('#idCitaPago').val(calEvent.id);

$('#tarifaE').val(calEvent.tarifa);

$('#pacienteT').val(calEvent.paciente);

$('#terminar').modal('show');

} else {

$('#idCitaConfi').val(calEvent.id);

$('#confirmar').modal('show');

}

} else if (calEvent.estado == 'confirmada' || calEvent.estado == 'salvada-pendiente') {

if (fechaComparar < fechaActual) {

if (calEvent.tarifa == 'NA') {

tarifa.style.display = "block";

} else {

tarifa.style.display = "none";

pago.style.display = "block";

botonTerminar.style.display = "block";

label.style.display = "none";

}

$('#idCitaPago').val(calEvent.id);

$('#tarifaE').val(calEvent.tarifa);

$('#pacienteT').val(calEvent.paciente);

$('#terminar').modal('show');

} else {

tarifa.style.display = "none";

pago.style.display = "none";

botonTerminar.style.display = "none";

label.style.display = "block";

$('#idCitaPago').val(calEvent.id);

$('#terminar').modal('show');

}

} else if (calEvent.estado == 'cancelada' || calEvent.estado == 'libre') {

if (fechaComparar < fechaActual) {

$('#idCita').val(calEvent.id);

body.style.display = "none";

sele.style.display = "none";

borr.style.display = "block";

botonAsignar.style.display = "none";

$('#modal1').modal('show');

$('.select2').select2({

dropdownParent: $('#modal1 .modal-body')

});

} else {

if(calEvent.estado == 'libre'){

body.style.display = "block";

sele.style.display = "block";

borr.style.display = "none";

$('#idCita').val(calEvent.id);

select.value = 'pendiente-Confirmar';

confirmada.style.display = "block";

reservada.style.display = "block";

pendiente.style.display = "block";

$('#modal1').modal('show');

$('.select2').select2({

dropdownParent: $('#modal1 .modal-body')

});

}else{

body.style.display = "block";

sele.style.display = "block";

borr.style.display = "none";

$('#idCita').val(calEvent.id);

select.value = 'confirmada';

confirmada.style.display = "block";

reservada.style.display = "none";

pendiente.style.display = "none";

$('#modal1').modal('show');

$('.select2').select2({

dropdownParent: $('#modal1 .modal-body')

});

}

}

}

}

});

$('#nomPaciente').on('input', function() {

$(this).removeClass('is-invalid');

});

$('#telPaciente').on('input', function() {

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

if (opcion == 'existenteasg') {

asignar({

idCita: $('#idCita').val(),

idPaciente: $('#idPaciente').val(),

estadoE: $('#estadoE').val(),

});

} else if (opcion == 'nuevoasg') {

asignar2({

idCita: $('#idCita').val(),

nomPaciente: $('#nomPaciente').val(),

telPaciente: $('#telPaciente').val(),

estadoE: $('#estadoE').val(),

});

}

//console.log($('#idPaciente').val() + " eeeee")

});

$("#terminarT").click(function() {

terminar($('#idCitaPago').val(), $('#formaPagoT').val(), $('#tarifaE').val(), $('#pacienteT').val())

});

$("#confirmarC").click(function() {

confirmar($('#idCitaConfi').val())

});

$("#canceConfi").click(function() {

cancelar($('#idCitaConfi').val())

});

$("#canceTermi").click(function() {

cancelar($('#idCitaPago').val())

});

$("#borrarTermi").click(function() {

eliminar($('#idCitaPago').val())

});

$("#borrarConfi").click(function() {

eliminar($('#idCitaConfi').val())

});

$("#borrarLibre").click(function() {

eliminar($('#idCita').val())

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

$('#calendario').fullCalendar('refetchEvents');

$('#calendario2').fullCalendar('refetchEvents');

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

$('#calendario').fullCalendar('refetchEvents');

$('#calendario2').fullCalendar('refetchEvents');

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

function terminar(id, pago, tarifa, paciente) {

if (confirm('¿Estás seguro de querer terminar esta cita? Una vez terminada no se podrá modificar ni borrar')) {

$.ajax({

url: '/api-revisar' + '/terminarCale/' + id,

method: 'POST',

data: {

fp: pago,

tf: tarifa,

pc: paciente

},

success: function(json) {

$("#msg").text('Cita terminada correctamente');

$("#msg").show();

$('#terminar').modal('hide');

$('#calendario2').fullCalendar('refetchEvents');

$('#calendario').fullCalendar('refetchEvents');

},

error: function(xhr, textStatus, errorThrown) {

var response = JSON.parse(xhr.responseText);

alert(response.message);

}

})

}

}

function confirmar(id) {

if (confirm('¿Estás seguro de querer confirmar esta cita? ')) {

console.log(id)

$.ajax({

url: '/api-revisar' + '/confirmar/' + id,

method: 'GET',

success: function(json) {

$("#msg").text('Se comfirmó correctamente');

$("#msg").show();

$('#confirmar').modal('hide');

$('#calendario2').fullCalendar('refetchEvents');

$('#calendario').fullCalendar('refetchEvents');

},

error: function(error) {

$("#msg").text('Error al confirmar la cita');

$("#msg").removeClass('alert-primary').addClass('alert-danger');

$("#msg").show();

}

})

}

}

function cancelar(id) {

if (confirm('¿Estás seguro de querer cancelar esta cita? ')) {

$.ajax({

url: '/api-revisar' + '/cancelar/' + id,

method: 'GET',

success: function(json) {

$("#msg").text('Se canceló correctamente');

$("#msg").show();

$('#confirmar').modal('hide');

$('#terminar').modal('hide');

$('#modal1').modal('hide');

$('#calendario2').fullCalendar('refetchEvents');

$('#calendario').fullCalendar('refetchEvents');

},

error: function(error) {

$("#msg").text('Error al cancelar la cita');

$("#msg").removeClass('alert-primary').addClass('alert-danger');

$("#msg").show();

}

})

}

}

function eliminar(id) {

if (confirm('¿Estás seguro de que eliminar esta cita?')) {

$.ajax({

url: '/api-ct' + '/delete/' + id,

method: 'DELETE',

success: function(json) {

$("#msg").text('Se eliminó correctamente');

$("#msg").show();

$('#confirmar').modal('hide');

$('#terminar').modal('hide');

$('#modal1').modal('hide');

$('#calendario2').fullCalendar('refetchEvents');

$('#calendario').fullCalendar('refetchEvents');

},

error: function(error) {

$("#msg").text('Error al eliminar la cita');

$("#msg").removeClass('alert-primary').addClass('alert-danger');

$("#msg").show();

}

})

}

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

function mostrarCampos(valor) {

var libres = document.getElementById("calendario-container1");

var ocupadas = document.getElementById("calendario-container");

if (valor == 'lib') {

libres.style.display = "block";

ocupadas.style.display = "none";

$('#calendario').fullCalendar('render');

} else if (valor == 'ocu') {

libres.style.display = "none";

ocupadas.style.display = "block";

$('#calendario2').fullCalendar('render');

}

};