google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(comparar);

function comparar() {
	 var opcion1 = document.getElementById('opcion1').value;
	
	 if (opcion1 === null || opcion1 === undefined || opcion1 === '') {
 		 var year = parseInt( new Date().getFullYear()) - 1
 		 opcion1= year.toString();
	}

    var url = "/api/precio-sesiones?opcion1=" + opcion1;
    fetch(url)
    .then(response => response.json())
    .then(datos => {
		drawChart2(datos)
		});
		
	
	function drawChart2(datos) {	
		
		
		
        var data = google.visualization.arrayToDataTable([
			['Opción', "Precio público genera",{ role: 'annotation' }, "Precio recudido",{ role: 'annotation' }],
            ['En', datos.mes1.ge, datos.mes1.ge, datos.mes1.red, datos.mes1.red],
            ['Feb', datos.mes2.ge, datos.mes2.ge,datos.mes2.red, datos.mes2.red],
            ['Mar', datos.mes3.ge, datos.mes3.ge,datos.mes3.red, datos.mes3.red],
            ['Abr', datos.mes4.ge, datos.mes4.ge,datos.mes4.red, datos.mes4.red],
            ['May', datos.mes5.ge, datos.mes5.ge, datos.mes5.red, datos.mes5.red],
            ['Jun', datos.mes6.ge, datos.mes6.ge, datos.mes6.red, datos.mes6.red],
            ['Jul', datos.mes7.ge, datos.mes7.ge, datos.mes7.red, datos.mes7.red],
            ['Ag', datos.mes8.ge, datos.mes8.ge, datos.mes8.red, datos.mes8.red],
            ['Sept', datos.mes9.ge, datos.mes9.ge, datos.mes9.red, datos.mes9.red],
            ['Oct', datos.mes10.ge, datos.mes10.ge, datos.mes10.red, datos.mes10.red],
            ['Nov', datos.mes11.ge, datos.mes11.ge, datos.mes11.red, datos.mes11.red],
            ['Dic', datos.mes12.ge, datos.mes12.ge, datos.mes12.red, datos.mes12.red],
            ['Media', datos.media.ge, datos.media.ge, datos.media.red, datos.media.red],
        ]);

       var options = {
  title: 'Precios especiales',
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
    title: 'Precios',
    titleTextStyle: {
      color: '#333'
    }
  }
};



        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    }
}
