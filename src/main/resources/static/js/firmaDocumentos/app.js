let signaturePad = null;
let canvas = null;

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

window.addEventListener('load', async () => {
  canvas = document.querySelector("#signature-canvas");
  canvas.width = canvas.offsetWidth;
  canvas.height = canvas.offsetHeight;

  signaturePad = new SignaturePad(canvas, {});

  const clearBtn = document.getElementById('clear-signature');
  clearBtn.addEventListener('click', (e) => {
    signaturePad.clear();
  });
});

window.addEventListener('resize', () => {
  canvas.width = canvas.offsetWidth;
  canvas.height = canvas.offsetHeight;
  signaturePad.clear();
});

window.addEventListener('load', async () => {
  const form = document.querySelector('#form');
  form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const dni = document.getElementById('dni').value;
    const nombres = document.getElementById('nombre').value;
    const apellidos = document.getElementById('apellido').value;
    await generatePDF(nombres, dni, apellidos, canvas);
  });
});

async function generatePDF(nombres, dni, apellidos, canvas) {
  const image = await loadImage("/images/datos.jpg");
  const signatureImage = signaturePad.toDataURL();

  const pdf = new jsPDF('p', 'pt', 'letter');

  pdf.addImage(image, 'JPG', 0, 0, 565, 792);
  pdf.setFontSize(12);
  pdf.text(`${nombres} ${apellidos}`, 130, 525);
  pdf.text(dni, 370, 525);

  const isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
  const signatureWidth = isMobile ? canvas.width / 2 : 300;
  const signatureHeight = isMobile ? canvas.height / 4 :60;

  pdf.addImage(signatureImage, 'JPG', 200, 580, signatureWidth, signatureHeight);

  pdf.save(`consentimiento${dni}.pdf`);
}
