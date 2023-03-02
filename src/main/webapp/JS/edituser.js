$.ajax({
    async: false,
    type: "GET",
    dataType: "json",
    url: "RevisarSesion"
}).done(function (data, textEstado, jqXHR) {
    console.log(data);
    if (data.respuesta == true) {

        document.getElementById('dato1').value = data.user.Nombre;
        document.getElementById('dato2').value = data.user.Apellidos;
        document.getElementById('date').value = data.user.Fecha_Nacimiento;
        document.getElementById('Email').value = data.user.Correo;
        document.getElementById('dato3').value = data.user.Usuario_Nombre;
        document.getElementById('dato4').value = data.user.Contrasenia;
        document.getElementById('sorpresa').value = data.user.Id_Usuario;
    } else
    {
        alert("No cuenta con una sesion aun");
        window.location.href = "index.html";
    }
}).fail(function (jqXHR, textEstado) {
    console.log("errorxd fatal" + textEstado);
});

$("#RegistroUsuario").submit(function (event) {
    event.preventDefault();
    alert("envio info");
    $.ajax({
        data: new FormData(this),
        type: "POST",
        dataType: "json",
        url: "edituser",
        cache: false,
        contentType: false,
        processData: false
    }).done(function (data, textEstado, jqXHR) {
        console.log(data);
        if (data.respuesta == true) {
            alert("Se Edito Correctamente");
            window.location.href = "Notasxd.html";
        } else
        {
            alert("Formato de Correo/Contraseña Invalido");
        }
    }).fail(function (jqXHR, textEstado) {
        console.log("errorxd fatal" + textEstado);
    });
})