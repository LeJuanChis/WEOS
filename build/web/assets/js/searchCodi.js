function doSearch () {
    var tableReg = document.getElementById('datos');
    var searchText = document.getElementById('searchTerm').value;
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";
    
    //Recorremos todas las filas con contenido de la tabla 
    for(var i=1; i< tableReg.rows.length;i++){
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        //cellsOfRow = tableReg.rows[i].getElementById("id");
        found=false;
        //recorremos todas las celdas
        for(var j=1; j<cellsOfRow.length && !found;j++){
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
            //buscamos el texto en el contrenido de la celda
            if(searchText.length==0 || (compareWith.indexOf(searchText)> -1)){
                found= true;
            }
        }
        if(found){
            tableReg.rows[i].style.display='';
        }else{
            //si no encuentra ninguna coincidencia esconde la fila de la tabla
            tableReg.rows[i].style.display='none';
        }
    }
    
}



