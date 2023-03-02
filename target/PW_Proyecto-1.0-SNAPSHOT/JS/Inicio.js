$.ajax({
    async: false,
    type: "GET",
    dataType: "json",
    url: "RevisarSesion"
}).done(function (data, textEstado, jqXHR) {
    console.log(data);
    if (data.respuesta == true) {
        $("#NombreUsuario").html(data.user.Usuario_Nombre);
        $("#ContraUsuario").html(data.user.Contrasenia);
        $("#fotoUsuario").attr('src', data.user.foto);

    } else
    {
        alert("No cuenta con una sesion aun");
        window.location.href = "index.html";
    }
}).fail(function (jqXHR, textEstado) {
    console.log("errorxd fatal" + textEstado);
});

$(document).ready(function () {
    $("#CerrarSesion").click(function () {
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "CerrarSesion"
        }).done(function (data, textEstado, jqXHR) {
            console.log(data);
            if (data.respuesta == true) {
                alert("Sesion Cerrada");
                window.location.href = "index.html";
            } else
            {
                alert("ups algo salio mal");
            }
        }).fail(function (jqXHR, textEstado) {
            console.log("errorxd fatal" + textEstado);
        });
    });
});