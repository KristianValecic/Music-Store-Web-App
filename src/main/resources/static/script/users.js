$(document).ready(function () {
    $('#usersLoginHistoryTable').DataTable({
        paging: true,
        pageLength: 6,
        lengthMenu: [6, 8, 10, 25, 50, 100],
        ordering: true,
        order: [[0, 'asc']],
    });
    $('#usersPurchaseHistoryTable').DataTable({
        paging: true,
        pageLength: 6,
        lengthMenu: [6, 8, 10, 25, 50, 100],
        ordering: true,
        order: [[0, 'asc']],
    });
});

// function filterTable() {
//     var table = $('#usersPurchaseHistoryTable').DataTable();
//     var fromDate = new Date(document.getElementById('fromDate').value);
//     var toDate = new Date(document.getElementById('toDate').value);
//
//     // table.column(1).search(fromDate + ' to ' + toDate, true, false).draw();
//     let column = table.column(1);
//     table.column(1).data().filter(function (value) {
//
//         let timeStamp = parseTimeStampFromSpan(value)
//         let date = new Date(timeStamp);
//         let goodDate = date >= fromDate && date <= toDate;
//         return goodDate;
//     }).draw();
// }

function parseTimeStampFromSpan(value) {
    var regex = /<span>(.*?)<\/span>/;
    var result = value.replace(regex, "$1");
    return result;
}
function parseTimeStampForDate(value) {
    var regex = /^(\d{4}-\d{2}-\d{2})/;
    var match = value.match(regex);
    var result = match[1];
    return result;
}