function loadImage(url) {
    return new Promise(resolve => {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.responseType = "blob";
        xhr.onload = function (e) {
            const reader = new FileReader();
            reader.onload = function(event) {
                const res = event.target.result;
                resolve(res);
            }
            const file = this.response;
            reader.readAsDataURL(file);
        }
        xhr.send();
    });
}

let signaturePad = null;

window.addEventListener('load', async () => {



    const form = document.querySelector('#generaDatos');
    form.addEventListener('submit', (e) => {
        e.preventDefault();

        let nfactura = document.getElementById('numFC').value;
        let fecha = document.getElementById('fechaC').value;
    	let Nombre = document.getElementById('nombreC').value;
    	let ape = document.getElementById('apellidosC').value;
    	let dni = document.getElementById('dniC').value;
    	let domicilio = document.getElementById('domicilioC').value;
    	let pobla = document.getElementById('poblacionC').value;
    	let cp = document.getElementById('codigoC').value;
    	let importe = document.getElementById('precioC').value.replace(".", ",");
    	let pago = document.getElementById('formaPagoC').value;;
        generatePDFdato(nfactura,fecha,Nombre,ape,dni,domicilio,pobla,cp,importe,pago);

    })

});

window.addEventListener('load', async () => {



    const form = document.querySelector('#formTipo');
    form.addEventListener('submit', (e) => {
        e.preventDefault();

        let nfactura = document.getElementById('facT').value;
        let fecha = document.getElementById('fechaT').value;
    	let NyA = document.getElementById('nomapT').value;
    	let dni = document.getElementById('dniT').value;
    	let domicilio = document.getElementById('domicilioT').value;
    	let pobla = document.getElementById('poblacionT').value;
    	let cp = document.getElementById('cpT').value;
    	let importe = document.getElementById('importeT').value.replace(".", ",");;
    	let pago = document.getElementById('pagoT').value;;
    	
    	
     
        generatePDFtipo(nfactura,fecha,NyA,dni,domicilio,pobla,cp,importe,pago);

    })

});

async function generatePDFtipo(fac,fech,nya,nif,domi,pobla,cp,imp,pago) {
	 if (imp.indexOf(".") === -1) {
    // si no tiene coma, agregar coma y cero al final
    imp = imp + ",0";
  }
   const valor = nif;
	const empiezaConLetra = /^[a-zA-Z]/.test(valor);
	var image=null;
	console.log(imp)

	if (empiezaConLetra) {
  		 image = await loadImage("/images/fcnie.jpg");
      } else {
 		  image = await loadImage("/images/fcnif.jpg");
		}

    const pdf = new jsPDF('p', 'pt', 'letter');

    pdf.addImage(image, 'PNG', 0, 0, 565, 792);
    pdf.setFontSize(12);
    pdf.text(fac, 335,94);
    pdf.text(fech, 335,133);
    pdf.text(nya, 205,320);
    pdf.text(nif, 110,338);
    pdf.text(domi, 138,358);
    pdf.text(pobla, 138,377);
    pdf.text(cp, 395,377);
	pdf.text(imp, 418,493);
    pdf.text(imp, 349,493);
    pdf.text(imp, 418,565);
    pdf.text(imp, 418,622);
  	pdf.text(pago, 200,641);
  
    pdf.save("factura("+fac+").pdf");

}


async function generatePDFdato(fac,fech,nb,ap,nif,domi,pobla,cp,imp,pago) {
	
	 if (imp.indexOf(".") === -1) {
    // si no tiene coma, agregar coma y cero al final
    imp = imp + ",0";
  }
	
	const valor = nif;
	const empiezaConLetra = /^[a-zA-Z]/.test(valor);
	var image=null;

	if (empiezaConLetra) {
  		 image = await loadImage("/images/fcnie.jpg");
      } else {
 		  image = await loadImage("/images/fcnif.jpg");
		}

    const pdf = new jsPDF('p', 'pt', 'letter');

    pdf.addImage(image, 'PNG', 0, 0, 565, 792);
     pdf.setFontSize(12);
    pdf.text(fac, 340,94);
    pdf.text(fech, 335,133);
    pdf.text(nb+" "+ap, 205,320);
    pdf.text(nif, 110,338);
    pdf.text(domi, 138,358);
    pdf.text(pobla, 138,377);
    pdf.text(cp, 395,377);
    pdf.text(imp, 418,493);
    pdf.text(imp, 349,493);
    pdf.text(imp, 418,565);
    pdf.text(imp, 418,622);
    pdf.text(pago, 200,641);
  
   pdf.save("factura("+fac+").pdf");

}