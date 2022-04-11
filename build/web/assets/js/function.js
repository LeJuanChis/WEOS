//actualizar la cantidad de productos en al cotizacion

$(document).ready(function () {
    $("tr #cantidad").click(function () {
        //obtenemos el id del producto y la cantidad
        let idP = $(this).parent().find("#idProd").val();
        let cantidad = $(this).parent().find("#cantidad").val();
        let NITempresa = $(this).parent().find("#NITempresa").val();
        //importar libreria de jquery
        let url = "./ProductosServiciosController?opcion=actualizarCantidad";
        //implementamos un metodo ajax para enviar datos
        $.ajax({
            type: 'POST',
            url: url,
            data: "idProd=" + idP + "&cantidad=" + cantidad + "&NITempresa=" + NITempresa,

            success: function (data, textStatus, jqXHR) {
                location.href = "./ProductosServiciosController?opcion=carrito&NITempresa=" + NITempresa;
            }
        })



    })


})

