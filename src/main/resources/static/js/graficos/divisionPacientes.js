google.charts.load('current', {'packages':['corechart', 'bar']});
google.charts.setOnLoadCallback(comparar);

function comparar() {
  var opcion1 = document.getElementById('opcion1').value;

  if (opcion1 === null || opcion1 === undefined || opcion1 === '') {
    var year = parseInt( new Date().getFullYear()) - 1
    opcion1= year.toString();
  }

  var url = "/api/divison-pacientes?opcion1=" + opcion1;
  fetch(url)
    .then(response => response.json())
    .then(datos => {
      drawChart2(datos)
    });

  function drawChart2(datos) { 
	   
    console.log(datos)
   var data = google.visualization.arrayToDataTable([
          ['meses',"Totales",{ role: 'annotation' },"Comunes",{ role: 'annotation' },"Nuevos" ,{ role: 'annotation' },"Amigos",{ role: 'annotation' }],
        
            ['En', datos.mes1.totales, datos.mes1.totales,  datos.mes1.comunes, datos.mes1.comunes,datos.mes1.nuevos, datos.mes1.nuevos,  datos.mes1.amigos, datos.mes1.amigos],
            ['Feb', datos.mes2.totales, datos.mes2.totales,  datos.mes2.comunes, datos.mes2.comunes,datos.mes2.nuevos, datos.mes2.nuevos,  datos.mes2.amigos, datos.mes2.amigos],
            ['Mar', datos.mes3.totales, datos.mes3.totales,  datos.mes3.comunes, datos.mes3.comunes,datos.mes3.nuevos, datos.mes3.nuevos,  datos.mes3.amigos, datos.mes3.amigos],
            ['Abr', datos.mes4.totales, datos.mes4.totales,  datos.mes4.comunes, datos.mes4.comunes,datos.mes4.nuevos, datos.mes4.nuevos,  datos.mes4.amigos, datos.mes4.amigos],
            ['May', datos.mes5.totales, datos.mes5.totales,  datos.mes5.comunes, datos.mes5.comunes,datos.mes5.nuevos, datos.mes5.nuevos,  datos.mes5.amigos, datos.mes5.amigos],
            ['Jun', datos.mes6.totales, datos.mes6.totales,  datos.mes6.comunes, datos.mes6.comunes,datos.mes6.nuevos, datos.mes6.nuevos,  datos.mes6.amigos, datos.mes6.amigos],
            ['Jul', datos.mes7.totales, datos.mes7.totales,  datos.mes7.comunes, datos.mes7.comunes,datos.mes7.nuevos, datos.mes7.nuevos,  datos.mes7.amigos, datos.mes7.amigos],
            ['Ag', datos.mes8.totales, datos.mes8.totales,  datos.mes8.comunes, datos.mes8.comunes,datos.mes8.nuevos, datos.mes8.nuevos,  datos.mes8.amigos, datos.mes8.amigos],
            ['Sept', datos.mes9.totales, datos.mes9.totales,  datos.mes9.comunes, datos.mes9.comunes,datos.mes9.nuevos, datos.mes9.nuevos,  datos.mes9.amigos, datos.mes9.amigos],
            ['Oct', datos.mes10.totales, datos.mes10.totales,  datos.mes10.comunes, datos.mes10.comunes,datos.mes10.nuevos, datos.mes10.nuevos,  datos.mes10.amigos, datos.mes10.amigos],
            ['Nov', datos.mes11.totales, datos.mes11.totales,  datos.mes11.comunes, datos.mes11.comunes,datos.mes11.nuevos, datos.mes11.nuevos,  datos.mes11.amigos, datos.mes11.amigos],
            ['Dic', datos.mes12.totales, datos.mes12.totales,  datos.mes12.comunes, datos.mes12.comunes,datos.mes12.nuevos, datos.mes12.nuevos,  datos.mes12.amigos, datos.mes12.amigos],
            ['Media', datos.media.totalesm, datos.media.totalesm, datos.media.comunesm, datos.media.comunesm, datos.media.nuevosm, datos.media.nuevosm, datos.media.amigosm, datos.media.amigosm],
          
           
    ]);

          var options = {
  title: 'Distribución de pacientes',
  width: 'auto',
  height: 400,
  chartArea: {
    width: '80%',
    height: '70%',
     overflow: 'hidden'
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
    title: 'Pacientes',
        gridlines: {
      count: 5
    },
    titleTextStyle: {
      color: '#333'
    }
  },
    colors: ['#AA4CA6', '#3DCFCC', '#FFC000', '#61CB7A']
  
  
};

    var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
    chart.draw(data, options);
  }
}
