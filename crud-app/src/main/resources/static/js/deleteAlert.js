 function ocultarSuccessDiv() {
    const successDiv = document.getElementById('successDiv');
    if (successDiv.style.display !== 'none') {
      setTimeout(function() {
        successDiv.style.display = 'none';
      }, 3000);
    }
  }

   function ocultarEliminadoDiv() {
      const deleteAlert = document.getElementById('deleteAlert');
      if (deleteAlert.style.display !== 'none') {
        setTimeout(function() {
          deleteAlert.style.display = 'none';
        }, 3000);
      }
    }

  document.addEventListener('DOMContentLoaded', ocultarSuccessDiv);
  document.addEventListener('DOMContentLoaded', ocultarEliminadoDiv);