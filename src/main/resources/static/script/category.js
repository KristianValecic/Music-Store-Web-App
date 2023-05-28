$(document).ready(function () {
    $('#categoryTable').DataTable({
        paging: true,
        pageLength: 7,
        lengthMenu: [7, 10, 25, 50, 100],
        ordering: true,
        order: [[0, 'asc']],
        columnDefs: [
            {orderable: false, targets: [1]}
        ],
    });
});