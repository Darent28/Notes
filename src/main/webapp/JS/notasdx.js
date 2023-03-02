$(document).ready(function () {
    get_user_data();
    get_notas();

    $("#taquitos").keyup(function (e) {
        event.preventDefault();

        let parametro = document.querySelector("#taquitos").value;

        if (parametro !== "") {
            $.ajax({
                type: "GET",
                dataType: "json",
                url: "busqueda",
                data: {
                    parametro: parametro
                }
            }).done(function (data) {
                console.log(data);

                let str = "";

                data.resultado.forEach(function (Valor) {
                    str += "<div class='col-4 my-4'>\n\
                               <div class='card'>\n\
                                  <div class='card-body'>\n\
                                      <h5 class='card-title'>" + Valor.Titulo + "</h5>\n\
                                          <p class='card-text'>" + Valor.Nota + "</p>\n\
                                              <ul class='list-group list-group-flush'>\n\
                                                  <li class='list-group-item'>El id de la nota es: " + Valor.Id_Nota + "</li></ul>\n\
                                                       <button id_nota=" + Valor.Id_Nota + " class='editar btn btn-lg btn-primary btn-block'>Editar</button>\n\
                                                           <button id_nota=" + Valor.Id_Nota + " class='eliminar btn btn-lg btn-primary btn-block'>Eliminar</button>\n\
                           </div>\n\
                       </div>\n\
                   </div>";
                });
                $("#notasuwu").html(str);

            }).fail(function (error) {
                console.log("Mi error es: " + error);
            });
        } else {
            get_notas();
        }
    });

    $("#Insert_New_Note").submit(function (event) {
        event.preventDefault();

        let titulo_nota = document.querySelector("#Titulo").value;
        let nota_contenido = document.querySelector("#Nota").value;

        $.ajax({
            async: false,
            type: "POST",
            dataType: "json",
            url: "Insert_New_Note",
            data: {
                titulo_nota: titulo_nota,
                nota_contenido: nota_contenido
            }
        }).done(function (data) {
            if (data.respuesta === true) {
                console.log("nota insertada");
                get_notas();
            } else {
                console.log("no se inserto");
            }
        }).fail(function (error) {
            console.log("Mi error es: " + error);
        });
    });

    $(document).on('click', '.editar', function (e) {
        event.preventDefault();

        let Id_Nota = $(this).attr("id_nota");
        let url = "EditarNota.html" + "?Id_Nota=" + Id_Nota;
        window.location.href = url;
    });

    $(document).on('click', '.eliminar', function (e) {
        event.preventDefault();

        let Id_Nota = $(this).attr("id_nota");

        $.ajax({
            async: false,
            type: "POST",
            dataType: "json",
            url: "DeleteNote",
            data: {
                Id_Nota: Id_Nota
            }
        }).done(function (data) {
            if (data.respuesta === true) {
                //console.log("nota eliminada");
                get_notas();
            } else {
                console.log("no se pude eliminar");
            }
        }).fail(function (error) {
            console.log("Mi error es: " + error);
        });
    });
});

function get_notas() {
    $.ajax({
        async: false,
        type: "GET",
        dataType: "json",
        url: "GetNotas"
    }).done(function (data) {

        let str = "";
        data.notas.forEach(function (Valor) {

            str += "<div class='col-4 my-4'>\n\
                   <div class='card'>\n\
                   <div class='card-body'>\n\
                      <h5 class='card-title'>" + Valor.Titulo + "</h5>\n\
                      <p class='card-text'>" + Valor.Nota + "</p>\n\
                      <ul class='list-group list-group-flush'>\n\
                      <li class='list-group-item'>El id de la nota es: " + Valor.Id_Nota + "</li></ul>\n\
                             <button id_nota=" + Valor.Id_Nota + " class='editar btn btn-lg btn-primary btn-block'>Editar</button>\n\
                             <button id_nota=" + Valor.Id_Nota + " class='eliminar btn btn-lg btn-primary btn-block'>Eliminar</button>\n\
                   </div>\n\
                   </div>\n\
                   </div>";
        });
        $("#notasuwu").html(str);
    }).fail(function (error) {
        console.log("Mi error es: " + error);
    });
}

function get_user_data() {
    $.ajax({
        async: false,
        type: "GET",
        dataType: "json",
        url: "get_user_data"
    }).done(function (data) {

        $("#fotoUsuario").attr('src', data.user.foto);
        $("#qwer").attr('src', data.user.foto);
        $("#Nombre").html(data.user.Nombre);
        $("#Apellidos").html(data.user.Apellidos);
        $("#ContraUsuario").html(data.user.Contrasenia);
        $("#Correo").html(data.user.Correo);
        $("#NombreUsuario").html(data.user.Usuario_Nombre);
        $("#rutita").html(data.user.foto);
        $("#FechaNacimiento").html(data.user.Fecha_Nacimiento);
    }).fail(function (error) {
        console.log("Mi error es: " + error);
    });
}
