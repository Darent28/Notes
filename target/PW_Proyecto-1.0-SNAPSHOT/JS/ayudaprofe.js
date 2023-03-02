



//
////var form = document.getElementById("frmejemplo");
//var btn = document.getElementById("btn2");
//var algo = document.getElementById("zapato");
//
//form.addEventListener("submit", saludo);
//btn.addEventListener("click", despedida);
//
//function saludo(e){
//    e.preventDefault();
//    var item = document.getElementById("box").value;
//    console.info(item);
//}
//
//function despedida(){
//     var item = document.getElementById("box").value;
//     console.info(algo.textContent);
//     alert(item);
//}


//alert(globalVariable.Id);
//alert(globalVariable.Titulo);
//alert(globalVariable.Nota);
//
////
//document.getElementById("ID").value = globalVariable.Id;
//document.getElementById("Tittle").value = globalVariable.Titulo;
//document.getElementById("Note").value = globalVariable.Nota;

$.ajax({
    async: false,
    type: "GET",
    dataType: "json",
    url: "RevisarSesion"
}).done(function (data, textEstado, jqXHR) {
    console.log(data);
    if (data.respuesta == true) {

    

//        document.getElementById('dato1').value = data.user.Nombre;
//        document.getElementById('dato2').value = data.user.Apellidos;
//        document.getElementById('date').value = data.user.Fecha_Nacimiento;
//        document.getElementById('Email').value = data.user.Correo;
//        document.getElementById('dato3').value = data.user.Usuario_Nombre;
//        document.getElementById('dato4').value = data.user.Contrasenia;
//        document.getElementById('sorpresa').value = data.user.Id_Usuario;
    } else
    {
        alert("No cuenta con una sesion aun");
        window.location.href = "index.html";
    }
}).fail(function (jqXHR, textEstado) {
    console.log("errorxd fatal" + textEstado);
});


$(document).ready(function () {

    var data = window.localStorage;

    document.getElementById('dato1').value = data.getItem('titulo');
    document.getElementById('dato2').value = data.getItem('nota');


    document.getElementById('ID').textContent = data.getItem('id');
    document.getElementById('Tittle').textContent = data.getItem('titulo');
    document.getElementById('Note').textContent = data.getItem('nota');
    //
    document.getElementById('OldTittle').textContent = data.getItem('titulo');
    document.getElementById('OldNote').textContent = data.getItem('nota');
    document.getElementById('oldId').textContent = data.getItem('id');
    //
    document.getElementById('sorpresa').value = textContent = data.getItem('id');
    //
    console.info(data.getItem('id'));
    console.info(data.getItem('titulo'));
    console.info(data.getItem('nota'));

    $("#edit_note").submit(function (event) {
        event.preventDefault();
        $.ajax({
            data: $(this).serialize(),
            type: "POST",
            dataType: "json",
            url: "Editar_Notas"
        }).done(function (data, textEstado, jqXHR) {
            console.log(data);
            if (data.respuesta == true) {
                alert("Nueva nota Editada");
                window.location.href = "Notasxd.html";
            } else
            {
                alert("NO se pudo editar su nota");
            }
        }).fail(function (jqXHR, textEstado) {
            console.log("El error es" + textEstado);
        });
    });













    /*var data=[];
     window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,function(a,name,value){
     console.log(a);
     console.log(name);
     console.log(value);
     })*/



})


function limpiar() {
    var data = window.localStorage;
    data.clear();
}