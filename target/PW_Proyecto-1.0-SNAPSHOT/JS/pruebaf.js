var totalNotas = 0;
var cantidadmostrar = 9;
var zapato = 0;

$.ajax({
    async: false,
    data: $(this).serialize(),
    type: "POST",
    dataType: "json",
    url: "busqueda"
}).done(function (data, textEstado, jqXHR) {

    if (data.respuesta == true) {
        console.log("La cantidad de notas es:" + data.cantidad);
        totalNotas = data.cantidad;
        var str = '<li class="page-item"><a class="page-link" href="#">Previous</a></li>';
        var pages = Math.ceil(totalNotas / cantidadmostrar);

        for (let i = 0; i < pages; i++) {
            str += '<li class="page-item" valor="' + i + '"><a class="page-link" href="#">' + i + '</a></li>';
        }

        str += '<li class="page-item"><a class="page-link" href="#">Next</a></li>'
        $("#paginas").html(str);
        zapato = document.getElementById('taquitos').value;
        cargarPaginacion(0, cantidadmostrar, zapato);
    } else
    {
        alert("Este usuario no tiene notas");
    }
}).fail(function (jqXHR, textEstado) {
    console.log("El error es" + textEstado);
});

function cargarPaginacion(indice, cantidad, parametro) {

    $.ajax({
        data: {indice: indice, cantidad: cantidad, parametro: parametro},
        type: "POST",
        dataType: "json",
        url: "Notas_Busqueda"
    }).done(function (data, textEstado, jqXHR) {
        console.log(data);
        var str = "";

        data.notas.forEach(function (valor, indice) {

            str += "<div class='col-4 my-4'>\n\
                        <div class='card'>\n\
                            <div class='card-body'>\n\
                                <h5 class='card-title'>" + valor.Titulo + "</h5>\n\
                                <p class='card-text'>" + valor.Nota + "</p>\n\
                                <ul class='list-group list-group-flush'>\n\
                                <li class='list-group-item'>El id de la nota es: " + valor.Id_Nota + "</li></ul>\n\
                            </div>\n\
                        </div>\n\
                    </div>"
        });

        $("#notasuwu").html(str);

    }).fail(function (jqXHR, textEstado) {
        console.log("errorxd fatal" + textEstado);
    });
}

$(document).ready(function () {

    $("#taquitos").keyup(function (event) {
        event.preventDefault();
        $.ajax({
            async: false,
            data: $(this).serialize(),
            type: "POST",
            dataType: "json",
            url: "busqueda"
        }).done(function (data, textEstado, jqXHR) {

            if (data.respuesta == true) {
                console.log("La cantidad de notas es:" + data.cantidad);
                totalNotas = data.cantidad;
                var str = '<li class="page-item"><a class="page-link" href="#">Previous</a></li>';
                var pages = Math.ceil(totalNotas / cantidadmostrar);

                for (let i = 0; i < pages; i++) {
                    str += '<li class="page-item" valor="' + i + '"><a class="page-link" href="#">' + i + '</a></li>';
                }

                str += '<li class="page-item"><a class="page-link" href="#">Next</a></li>'
                $("#paginas").html(str);
                zapato = document.getElementById('taquitos').value;
                cargarPaginacion(0, cantidadmostrar, zapato);
            } else
            {
                alert("No existen notas con esos parametros");
                window.location.href = "prueba.html";
            }
        }).fail(function (jqXHR, textEstado) {
            console.log("El error es" + textEstado);
        });
    });

    $(".page-item").click(function () {

        //alert("XD");
        $(".page-item").removeClass("active");
        $(this).addClass("active");
        var valor = $(this).attr("valor");
        zapato = document.getElementById('taquitos').value;
        cargarPaginacion(valor * cantidadmostrar, cantidadmostrar, zapato);

    });




//
//    $("page-item").on("click", function () {
//        alert("XD");
//
//    });
});

