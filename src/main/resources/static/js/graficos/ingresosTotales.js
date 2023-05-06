google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(comparar);

function comparar() {
	 var opcion1 = document.getElementById('opcion1').value;
    var opcion2 = document.getElementById('opcion2').value;
    if (opcion1 === null || opcion1 === undefined || opcion1 === '') {
 		 var year = new Date().getFullYear();
 		 opcion1= year.toString();
	}
	
	 if (opcion2 === null || opcion2 === undefined || opcion2 === '') {
 		 var year = parseInt( new Date().getFullYear()) - 1
 		 opcion2= year.toString();
	}

    var url = "/api/ingresos-totales?opcion1=" + opcion1 + "&opcion2=" + opcion2;
    fetch(url)
    .then(response => response.json())
    .then(datos => {
		drawChart2(datos,opcion1,opcion2)
		});
		
	
	function drawChart2(datos,an1,an2) {	
		
		
		
        var data = google.visualization.arrayToDataTable([
			['Opción', an1,{ role: 'annotation' }, an2,{ role: 'annotation' }],
            ['En', datos.mes1.anyo1, datos.mes1.anyo1, datos.mes1.anyo2, datos.mes1.anyo2],
            ['Feb', datos.mes2.anyo1, datos.mes2.anyo1,datos.mes2.anyo2, datos.mes2.anyo2],
            ['Mar', datos.mes3.anyo1, datos.mes3.anyo1,datos.mes3.anyo2, datos.mes3.anyo2],
            ['Abr', datos.mes4.anyo1, datos.mes4.anyo1,datos.mes4.anyo2, datos.mes4.anyo2],
            ['May', datos.mes5.anyo1, datos.mes5.anyo1, datos.mes5.anyo2, datos.mes5.anyo2],
            ['Jun', datos.mes6.anyo1, datos.mes6.anyo1, datos.mes6.anyo2, datos.mes6.anyo2],
            ['Jul', datos.mes7.anyo1, datos.mes7.anyo1, datos.mes7.anyo2, datos.mes7.anyo2],
            ['Ag', datos.mes8.anyo1, datos.mes8.anyo1, datos.mes8.anyo2, datos.mes8.anyo2],
            ['Sept', datos.mes9.anyo1, datos.mes9.anyo1, datos.mes9.anyo2, datos.mes9.anyo2],
            ['Oct', datos.mes10.anyo1, datos.mes10.anyo1, datos.mes10.anyo2, datos.mes10.anyo2],
            ['Nov', datos.mes11.anyo1, datos.mes11.anyo1, datos.mes11.anyo2, datos.mes11.anyo2],
            ['Dic', datos.mes12.anyo1, datos.mes12.anyo1, datos.mes12.anyo2, datos.mes12.anyo2],
            ['Media', datos.media.anyo1, datos.media.anyo1, datos.media.anyo2, datos.media.anyo2],
            ['Total', datos.total.anyo1, datos.total.anyo1, datos.total.anyo2, datos.total.anyo2],
        ]);

       var options = {
  title: 'Ingresos totales',
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
    title: 'Ingresos',
        gridlines: {
      count: 5
    },
    titleTextStyle: {
      color: '#333'
    }
  },
  colors: ['#3DCFCC', '#AA4CA6']
};



        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    }
}
