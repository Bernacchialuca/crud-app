   $(document).ready(function () {
        // Escucha el evento "change" en el select para filtrar empleados.
        $("#filtroSelect").on("change", function () {
            // Obtén los datos del formulario.
            const formData = $("#filtroEmpleadosForm").serialize();

            // Realiza una solicitud AJAX para filtrar empleados.
            $.ajax({
                url: "/empleados/search",
                type: "GET",
                data: formData,
                success: function (response) {
                    // Actualiza el contenido de la lista de empleados en la página con la respuesta AJAX.
                    $("#listaDeEmpleadosContainer").html(response);
                },
                error: function (xhr, status, error) {
                    // Manejar el error si es necesario.
                    console.error(error);
                }
            });
        });


        $("#filtroEmpleadosForm").on("submit", function (event) {
            event.preventDefault();
            const formData = $(this).serialize();

            $.ajax({
                url: "/empleados/search",
                type: "GET",
                data: formData,
                success: function (response) {
                    $("#listaDeEmpleadosContainer").html(response);
                },
                error: function (xhr, status, error) {
                    console.error(error);
                }
            });
        });
    });