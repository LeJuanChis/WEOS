//Ahora vamos a programar la pagina del detalle
const btnCotizacion = document.querySelector("#btnCotizacion");
const productos = document.querySelector("#products")

btnCotizacion.addEventListener("click", (e) => {
    productos.style.visibility='visible'
})
console.log(btnCotizacion)

