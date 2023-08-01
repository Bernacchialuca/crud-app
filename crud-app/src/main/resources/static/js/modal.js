// Function to show the modal
function showModal(employeeName, employeeLastName, url) {
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

  function closeModal() {
    deleteModal.style.display = 'none';
  }

  cancelButton.addEventListener('click', closeModal);
  xButton.addEventListener('click', closeModal);
}

// Function to attach event listeners to delete buttons
function attachDeleteButtonListeners() {
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
}

// Function to hide successDiv after 3 seconds
function ocultarSuccessDiv() {
  const successDiv = document.getElementById('successDiv');
  if (successDiv.style.display !== 'none') {
    setTimeout(function() {
      successDiv.style.display = 'none';
    }, 3000); // 3000 milisegundos = 3 segundos
  }
}

// Function to hide eliminadoDiv after 3 seconds
function ocultarEliminadoDiv() {
  const eliminadoDiv = document.getElementById('eliminadoDiv');
  if (eliminadoDiv.style.display !== 'none') {
    setTimeout(function() {
      eliminadoDiv.style.display = 'none';
    }, 3000); // 3000 milisegundos = 3 segundos
  }
}

// DOMContentLoaded event listener
document.addEventListener('DOMContentLoaded', () => {
  attachDeleteButtonListeners();

  const deleteModal = document.getElementById('deleteModal');
  deleteModal.addEventListener('click', (event) => {
    if (event.target === deleteModal) {
      deleteModal.style.display = 'none';
    }
  });

  ocultarSuccessDiv();
  ocultarEliminadoDiv();
});
