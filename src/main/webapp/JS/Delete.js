$(document).ready(function () {

    var data = window.localStorage;
    console.info(data.getItem('id_eliminar'));
    console.info(data.getItem('nota_eliminar'));
    console.info(data.getItem('titulo_eliminar'));

    document.getElementById('OldTittle').textContent = data.getItem('titulo_eliminar');
    document.getElementById('OldNote').textContent = data.getItem('nota_eliminar');
    document.getElementById('oldId').textContent = data.getItem('id_eliminar');
    //
    document.getElementById('sorpresa').value = textContent = data.getItem('id_eliminar');

    $("#delete_note").submit(function (event) {
        event.preventDefault();
        var txt;
        var r = confirm("¿Esta seguro que desea eliminar la nota?");

        if (r == true) {

            $.ajax({
                data: $(this).serialize(),
                type: "GET",
                dataType: "json",
                url: "DeleteNote"
            }).done(function (data, textEstado, jqXHR) {
                console.log(data);
                if (data.respuesta == true) {
                    alert("Nueva nota Eliminada");
                    window.location.href = "Notasxd.html";
                } else
                {
                    alert("NO se pudo eliminar su nota");
                }
            }).fail(function (jqXHR, textEstado) {
                console.log("El error es" + textEstado);
            });
        } else {

        }

    });

})