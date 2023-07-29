document.addEventListener('DOMContentLoaded', () => {
  const showModal = (employeeName, employeeLastName, url) => {
    const deleteModal = document.getElementById('deleteModal');
    const acceptButton = deleteModal.querySelector('#acceptButton');
    const cancelButton = deleteModal.querySelector('#cancelButton');
    const xButton = deleteModal.querySelector('#xButton');

    document.getElementById('employeeName').textContent = employeeName;
    document.getElementById('employeeLastName').textContent = employeeLastName;

    acceptButton.addEventListener('click', () => {
      fetch(url, {
        method: 'GET'
      })
      .then(response => {
        if (response.ok) {
          closeModal();
          showDeleteAlert();
        } else {
          console.error('Error al eliminar empleado');
        }
      })
      .catch(error => {
        console.error('Error en la petición de eliminación');
      });
    });

    const closeModal = () => {
      deleteModal.style.display = 'none';
    };

    cancelButton.addEventListener('click', closeModal);
    xButton.addEventListener('click', closeModal);

    deleteModal.style.display = 'block';
  };

  const showDeleteAlert = () => {
    const deleteAlert = document.getElementById('deleteAlert');
    deleteAlert.style.display = 'block';

    setTimeout(() => {
      deleteAlert.style.display = 'none';
    }, 3000);
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
