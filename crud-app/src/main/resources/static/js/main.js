document.addEventListener('DOMContentLoaded', function() {
  // Función para mostrar el modal al hacer clic en el botón de eliminar
  var deleteButtons = document.querySelectorAll('a[name="deleteButton"]');
  deleteButtons.forEach(function(button) {
    button.addEventListener('click', function(event) {
      event.preventDefault(); // Evitar que el enlace realice su acción predeterminada (navegar a la URL)
      var url = this.getAttribute('href'); // Obtener la URL de eliminación del atributo href del enlace
      var deleteModal = document.getElementById('deleteModal');
      var acceptButton = deleteModal.querySelector('.btn-primary');

      deleteModal.style.display = 'block';

      acceptButton.addEventListener('click', function() {
        window.location.href = url;
      });
    });
  });

  var deleteModal = document.getElementById('deleteModal');
  deleteModal.addEventListener('click', function(event) {
    if (event.target === deleteModal || event.target.classList.contains('btn-secondary')) {
      deleteModal.style.display = 'none';
    }
  });
});
