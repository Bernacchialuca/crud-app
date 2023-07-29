document.addEventListener('DOMContentLoaded', () => {
  const showModal = (employeeName, employeeLastName, url) => {
    const deleteModal = document.getElementById('deleteModal');
    deleteModal.style.display = 'block';
    document.getElementById('employeeName').textContent = employeeName;
    document.getElementById('employeeLastName').textContent = employeeLastName;

    const acceptButton = deleteModal.querySelector('#acceptButton');
    const cancelButton = deleteModal.querySelector('#cancelButton');
    const xButton = deleteModal.querySelector('#xButton');

    acceptButton.addEventListener('click', () => {
      window.location.href = url;
    });

    const closeModal = () => {
      deleteModal.style.display = 'none';
    };

    cancelButton.addEventListener('click', closeModal);
    xButton.addEventListener('click', closeModal);
  };

  const deleteButtons = document.querySelectorAll('a[name="deleteButton"]');
  deleteButtons.forEach((button) => {
    button.addEventListener('click', (event) => {
      event.preventDefault();
      const url = button.getAttribute('href');
      const employeeName = button.closest('tr').querySelectorAll('.employee-name')[0].textContent;
      const employeeLastName = button.closest('tr').querySelectorAll('.employee-name')[1].textContent;
      showModal(employeeName, employeeLastName, url);
    });
  });

  const deleteModal = document.getElementById('deleteModal');
  deleteModal.addEventListener('click', (event) => {
    if (event.target === deleteModal) {
      deleteModal.style.display = 'none';
    }
  });
});

 function ocultarSuccessDiv() {
    var successDiv = document.getElementById('successDiv');
    if (successDiv.style.display !== 'none') {
      setTimeout(function() {
        successDiv.style.display = 'none';
      }, 3000); // 3000 milisegundos = 3 segundos
    }
  }

  // Llama a la función para que comience a contar el tiempo cuando se cargue la página
  document.addEventListener('DOMContentLoaded', ocultarSuccessDiv);