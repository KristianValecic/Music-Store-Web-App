$(document).ready(function () {
    $('#usersLoginHistoryTable').DataTable({
        paging: true,
        pageLength: 6,
        lengthMenu: [6, 8, 10, 25, 50, 100],
        ordering: true,
        order: [[0, 'asc']],
    });
});