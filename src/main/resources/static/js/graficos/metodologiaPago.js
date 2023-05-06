google.charts.load('current', {'packages':['corechart', 'bar']});
google.charts.setOnLoadCallback(comparar);

function comparar() {
  var opcion1 = document.getElementById('opcion1').value;

  if (opcion1 === null || opcion1 === undefined || opcion1 === '') {
    var year = parseInt( new Date().getFullYear()) - 1
    opcion1= year.toString();
  }

  var url = "/api/forma-pagos?opcion1=" + opcion1;
  fetch(url)
    .then(response => response.json())
    .then(datos => {
      drawChart2(datos)
    });

  function drawChart2(datos) { 
	   
    console.log(datos)
   var data = google.visualization.arrayToDataTable([
          ['meses',"Pagos en efectivo",{ role: 'annotation' },"Pagos en bizum",{ role: 'annotation' }],
        
            ['En', datos.mes1.externa,datos.mes1.externa,  datos.mes1.interna,datos.mes1.interna],
            ['Feb', datos.mes2.externa,datos.mes2.externa,  datos.mes2.interna,datos.mes2.interna ],
            ['Mar', datos.mes3.externa,datos.mes3.externa,  datos.mes3.interna,datos.mes3.interna],
            ['Abr', datos.mes4.externa,datos.mes4.externa,  datos.mes4.interna,datos.mes4.interna],
            ['May', datos.mes5.externa, datos.mes5.externa, datos.mes5.interna, datos.mes5.interna],
            ['Jun', datos.mes6.externa, datos.mes6.externa, datos.mes6.interna, datos.mes6.interna],
            ['Jul', datos.mes7.externa, datos.mes7.externa, datos.mes7.interna, datos.mes7.interna],
            ['Ag', datos.mes8.externa, datos.mes8.externa, datos.mes8.interna, datos.mes8.interna],
            ['Sept', datos.mes9.externa, datos.mes9.externa, datos.mes9.interna, datos.mes9.interna],
            ['Oct', datos.mes10.externa, datos.mes10.externa, datos.mes10.interna, datos.mes10.interna],
            ['Nov', datos.mes11.externa, datos.mes11.externa, datos.mes11.interna, datos.mes11.interna],
            ['Dic', datos.mes12.externa, datos.mes12.externa, datos.mes12.interna, datos.mes12.interna],
            ['Media', datos.media.externam,datos.media.externam, datos.media.internam, datos.media.internam],
          
           
    ]);
    
    

          var options = {
  title: 'Formas de pago',
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
	isStacked: true,
  hAxis: {
    title: 'Meses',
    titleTextStyle: {
      color: '#333'
    }
  },

  vAxis: {
    minValue: 0,
    title: 'Pagos',
        gridlines: {
      count: 5
    },
    titleTextStyle: {
      color: '#333'
    }
  },
   colors: ['#3DCFCC', '#EB7321']
  
  
};

    var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
    chart.draw(data, options);
  }
}
