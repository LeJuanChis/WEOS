let busqueda = document.getElementById("inputBusqueda")
const preguntas = document.querySelectorAll('.search a')

var consulta = $("#searchTable").DataTable();
$("#inputBusqueda").keyup(function(){
	consulta.search($(this).val()).draw()
	if ($("#inputBusqueda").val() == ""){
		$("#search").hide()
		
	} else {
		$("#search").fadeIn("fast");
	}

})


function searchBarResult(e){
	busqueda.value = e.target.textContent
}

preguntas.forEach(el =>{
	el.addEventListener('click', searchBarResult)
})




document.getElementById("search").addEventListener("click", ocultar_barra);
function ocultar_barra(){
	search.style.display="none";
}

