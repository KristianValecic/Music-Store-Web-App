const DELIMITER = "|";

$(document).ready(function () {
    $('#shoppingCartItemsTable').DataTable({
        paging: true,
        pageLength: 6,
        lengthMenu: [6, 8, 10, 25, 50, 100],
        ordering: true,
        order: [[0, 'asc']],
        columnDefs: [
            {orderable: false, targets: [2, 7]}
        ],
    });
});

function parseItemData(dataString) {
    return dataString.split(DELIMITER);
}

function fillModalWithData() {
    // console.log(window.event.target.value);
    let itemData = window.event.target.value;
    let dataArray = parseItemData(itemData);
    document.getElementById("itemIndexHidden").value = dataArray[0];
    // document.getElementById("itemMaxAmountHidden").value = dataArray[1];
    document.getElementById("amountInput").max = dataArray[1];
}

function removeSelectedAmount() {
    let hiddenField = document.getElementById("itemIndexHidden");
    let amountInput = document.getElementById("amountInput");
    let itemIndex = hiddenField.value;
    let amount = amountInput.value;

    // if empty or blank
    if (amount.trim() === ''){
        amountInput.reportValidity()
    } else {
        let url = '/removeAmountFromCart/' + itemIndex + '/' + amount

        $.ajax({
            url: url,
            type: 'POST',
            processData: false,
            contentType: false,
            success: function(response) {
                console.log('successfully removed items from shopping cart');
                window.location.href = window.location.href;
            },
            error: function(xhr, status, error) {
                console.log('Error removing items from cart:', error);
            }
        });
    }
}
function removeSelectedItem() {
    let hiddenField = document.getElementById("itemIndexHidden");
    let itemIndex = hiddenField.value;

    $.ajax({
        url: '/removeItemFromCart/' + itemIndex,
        type: 'POST',
        processData: false,
        contentType: false,
        success: function(response) {
            console.log('successfully removed items from shopping cart');
            window.location.href = window.location.href;
        },
        error: function(xhr, status, error) {
            console.log('Error removing items from cart:', error);
        }
    });
}
