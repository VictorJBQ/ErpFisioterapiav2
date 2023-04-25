google.charts.load('current', {'packages':['corechart', 'bar']});
google.charts.setOnLoadCallback(comparar);

function comparar() {
  var opcion1 = document.getElementById('opcion1').value;

  if (opcion1 === null || opcion1 === undefined || opcion1 === '') {
    var year = parseInt( new Date().getFullYear()) - 1
    opcion1= year.toString();
  }

  var url = "/api/distribucion-sesiones?opcion1=" + opcion1;
  fetch(url)
    .then(response => response.json())
    .then(datos => {
		console.log(datos)
      drawChart2(datos)
    });

  function drawChart2(datos) { 
	   
    console.log(datos)
   var data = google.visualization.arrayToDataTable([
          ['meses',"Cubiertas",{ role: 'annotation' },"Fijas",{ role: 'annotation' },"Extras",{ role: 'annotation' },"Libres",{ role: 'annotation' },"Canceladas",{ role: 'annotation' },"Salvadas",{ role: 'annotation' }],
        
            ['En', datos.mes1.cubiertas, datos.mes1.cubiertas, datos.mes1.fijas, datos.mes1.fijas,  datos.mes1.extras, datos.mes1.extras, datos.mes1.libres, datos.mes1.libres, datos.mes1.canceladas, datos.mes1.canceladas, datos.mes1.salvadas,datos.mes1.salvadas],
            ['Feb', datos.mes2.cubiertas, datos.mes2.cubiertas, datos.mes2.fijas, datos.mes2.fijas,  datos.mes2.extras, datos.mes2.extras, datos.mes2.libres, datos.mes2.libres, datos.mes2.canceladas, datos.mes2.canceladas, datos.mes2.salvadas,datos.mes2.salvadas],
            ['Mar', datos.mes3.cubiertas, datos.mes3.cubiertas, datos.mes3.fijas, datos.mes3.fijas,  datos.mes3.extras, datos.mes3.extras, datos.mes3.libres, datos.mes3.libres, datos.mes3.canceladas, datos.mes3.canceladas, datos.mes3.salvadas,datos.mes3.salvadas],
            ['Abr', datos.mes4.cubiertas, datos.mes4.cubiertas, datos.mes4.fijas, datos.mes4.fijas,  datos.mes4.extras, datos.mes4.extras, datos.mes4.libres, datos.mes4.libres, datos.mes4.canceladas, datos.mes4.canceladas, datos.mes4.salvadas,datos.mes4.salvadas],
            ['May', datos.mes5.cubiertas, datos.mes5.cubiertas, datos.mes5.fijas, datos.mes5.fijas,  datos.mes5.extras, datos.mes5.extras, datos.mes5.libres, datos.mes5.libres, datos.mes5.canceladas, datos.mes5.canceladas, datos.mes5.salvadas,datos.mes5.salvadas],
            ['Jun', datos.mes6.cubiertas, datos.mes6.cubiertas, datos.mes6.fijas, datos.mes6.fijas,  datos.mes6.extras, datos.mes6.extras, datos.mes6.libres, datos.mes6.libres, datos.mes6.canceladas, datos.mes6.canceladas, datos.mes6.salvadas,datos.mes6.salvadas],
            ['Jul', datos.mes7.cubiertas, datos.mes7.cubiertas, datos.mes7.fijas, datos.mes7.fijas,  datos.mes7.extras, datos.mes7.extras, datos.mes7.libres, datos.mes7.libres, datos.mes7.canceladas, datos.mes7.canceladas, datos.mes7.salvadas,datos.mes7.salvadas],
            ['Ag', datos.mes8.cubiertas, datos.mes8.cubiertas, datos.mes8.fijas, datos.mes8.fijas,  datos.mes8.extras, datos.mes8.extras, datos.mes8.libres, datos.mes8.libres, datos.mes8.canceladas, datos.mes8.canceladas, datos.mes8.salvadas,datos.mes8.salvadas],
            ['Sept', datos.mes9.cubiertas, datos.mes9.cubiertas, datos.mes9.fijas, datos.mes9.fijas,  datos.mes9.extras, datos.mes9.extras, datos.mes9.libres, datos.mes9.libres, datos.mes9.canceladas, datos.mes9.canceladas, datos.mes9.salvadas,datos.mes9.salvadas],
            ['Oct', datos.mes10.cubiertas, datos.mes10.cubiertas, datos.mes10.fijas, datos.mes10.fijas,  datos.mes10.extras, datos.mes10.extras, datos.mes10.libres, datos.mes10.libres, datos.mes10.canceladas, datos.mes10.canceladas, datos.mes10.salvadas,datos.mes10.salvadas],
            ['Nov', datos.mes11.cubiertas, datos.mes11.cubiertas, datos.mes11.fijas, datos.mes11.fijas,  datos.mes11.extras, datos.mes11.extras, datos.mes11.libres, datos.mes11.libres, datos.mes11.canceladas, datos.mes11.canceladas, datos.mes11.salvadas,datos.mes11.salvadas],
            ['Dic', datos.mes12.cubiertas, datos.mes12.cubiertas, datos.mes12.fijas, datos.mes12.fijas,  datos.mes12.extras, datos.mes12.extras, datos.mes12.libres, datos.mes12.libres, datos.mes12.canceladas, datos.mes12.canceladas, datos.mes12.salvadas,datos.mes12.salvadas],
            ['Media', datos.media.cubiertasm, datos.media.cubiertasm, datos.media.fijasm, datos.media.fijasm,  datos.media.extrasm, datos.media.extrasm, datos.media.libresm, datos.media.libresm, datos.media.canceladas, datos.media.canceladas, datos.media.salvadasm, datos.media.salvadasm],
          
           
    ]);

          var options = {
  title: 'Distribución de sesiones',
  width: 'auto',
  height: 400,
  chartArea: {
    width: '80%',
    height: '70%'
  },
  legend: { position: 'top', maxLines: 3, textStyle: { alignment: 'end' } },
  bar: {
    groupWidth: '70%'
  },
  hAxis: {
    title: 'Meses',
    titleTextStyle: {
      color: '#333'
    }
  },
  vAxis: {
    minValue: 0,
    title: 'sesiones',
    titleTextStyle: {
      color: '#333'
    }
  },
  tooltip: {
    isHtml: true,
    trigger: 'hover',
    text: 'Hola, el valor es: <b>e</b>'
  },
  
  
  
};




    var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
    google.visualization.events.addListener(chart, 'onmouseover', function (e) {
    var value = e.row; // número de la fila seleccionada
    
    var salvada= data.getValue(value,6 );
    var cancelada= data.getValue(value,5 );
    var totalCancelada= salvada +cancelada;
    var libres= data.getValue(value,4 );
    var lib= libres+cancelada;
    var message = '-Total citas Canceladas: ' + totalCancelada+' de las cuales han sido salvadas: '+salvada+
    ' - Total de citas libres contando las canceladas: '+lib; // mensaje personalizado
    var tooltip = document.querySelector('.google-visualization-tooltip'); // seleccione el tooltip del gráfico
    tooltip.innerHTML = message; // inserta el mensaje personalizado en el tooltip
});
    chart.draw(data, options);
  }
  
  
}
