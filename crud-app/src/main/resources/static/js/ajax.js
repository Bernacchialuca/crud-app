// Function to perform the AJAX request and update the content
function performAjaxRequest(formData) {
  $.ajax({
    url: "/empleados/search",
    type: "GET",
    data: formData,
    success: function (response) {
      $("#listaDeEmpleadosContainer").html(response);
      // Reattach event listeners to delete buttons after updating content
      attachDeleteButtonListeners();
    },
    error: function (xhr, status, error) {
      console.error(error);
    },
  });
}

$(document).ready(function () {
  // Escucha el evento "change" en el select para filtrar empleados.
  $("#filtroSelect").on("change", function () {
    // Obtén los datos del formulario.
    const formData = $("#filtroEmpleadosForm").serialize();
    performAjaxRequest(formData);
  });

  $("#filtroEmpleadosForm").on("submit", function (event) {
    event.preventDefault();
    const formData = $(this).serialize();
    performAjaxRequest(formData);
  });
});
