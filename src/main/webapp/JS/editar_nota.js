$(document).ready(function () {

    let Url = (window.location).href;
    let Id_Nota = extraerParametro(Url, "Id_Nota");

    $("#finalizar").click(function (event) {
        event.preventDefault();

        let Nuevo_Titulo = document.querySelector("#Nuevo_Titulo").value;
        let Nueva_Nota = document.querySelector("#Nueva_Nota").value;

        $.ajax({
            async: false,
            type: "POST",
            dataType: "json",
            url: "Editar_Notas",
            data: {
                Id_Nota: Id_Nota,
                Nuevo_Titulo: Nuevo_Titulo,
                Nueva_Nota: Nueva_Nota
            }
        }).done(function (data) {
            if (data.respuesta === true) {
                console.log("nota editada");
                window.location.href = "Notasxd.html";
            } else {
                console.log("no se edito");
            }
        }).fail(function (error) {
            console.log("Mi error es: " + error);
        });
    });

});

function extraerParametro(url, param) {
    let regex = new RegExp(param + "=([^&]+)", "i");
    return url.match(regex)[1];
}