function performAjaxRequest(formData) {
  $.ajax({
    url: "/admin/search",
    type: "GET",
    data: formData,
    success: function (response) {
      $("#listaDeEmpleadosContainer").html(response);
    },
    error: function (xhr, status, error) {
      console.error(error);
    },
  });
}

function attachDeleteButtonListeners() {
  // Delegación de eventos para vincular eventos de botones de eliminación
  $("#listaDeEmpleadosContainer").on("click", 'a[name="deleteButton"]', function (event) {
    event.preventDefault();
    const url = $(this).attr('href');
    const employeeName = $(this).closest('tr').find('.employee-name').eq(0).text();
    const employeeLastName = $(this).closest('tr').find('.employee-name').eq(1).text();
    showModal(employeeName, employeeLastName, url);
  });
}

$(document).ready(function () {
  attachDeleteButtonListeners();

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
