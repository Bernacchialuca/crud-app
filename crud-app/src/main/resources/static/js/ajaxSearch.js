function performAjaxRequest(formData) {
  $.ajax({
    url: "/admin/search",
    type: "GET",
    data: formData,
    success: function (response) {
      $("#listaDeEmpleadosContainer").html(response);
      attachDeleteButtonListeners();
    },
    error: function (xhr, status, error) {
      console.error(error);
    },
  });
}

$(document).ready(function () {
  $("#filtroSelect").on("change", function () {
    const formData = $("#filtroEmpleadosForm").serialize();
    performAjaxRequest(formData);
  });

  $("#filtroEmpleadosForm").on("submit", function (event) {
    event.preventDefault();
    const formData = $(this).serialize();
    performAjaxRequest(formData);
  });
});