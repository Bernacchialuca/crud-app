$(document).ready(function () {
  function loadPage(page) {
    $.ajax({
      url: '/admin/listado?page=' + page,
      type: 'GET',
      success: function (data) {
        $('#listaDeEmpleadosContainer').html($(data).find('#listaDeEmpleadosContainer').html());
        $('#paginationContainer').html($(data).find('#paginationContainer').html());
      },
      error: function (error) {
        console.log('Error fetching data:', error);
      }
    });
  }

  $(document).on('click', '.pagination a', function (event) {
    event.preventDefault();
    var page = $(this).attr('href').split('page=')[1];
    loadPage(page);
  });

  loadPage(1);
});
