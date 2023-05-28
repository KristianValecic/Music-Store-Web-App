const DELIMITER = "|";

const FUNDING_SOURCES = [
    paypal.FUNDING.PAYPAL
];

// FUNDING_SOURCES.forEach(fundingSource => {
//     paypal.Buttons({
//         fundingSource,
//         style: {
//             layout: 'vertical',
//             shape: 'rect',
//             color: (fundingSource == paypal.FUNDING.PAYLATER) ? 'gold' : '',
//         },
//         createOrder: async (data, actions) => {
//             const response = await fetch("/orders", {
//                 method: "POST",
//             });
//             const details = await response.json();
//             return details.id;
//         },
//
//         onApprove: async (data, actions) => {
//             const response = await fetch(`/orders/${data.orderID}/capture`, {
//                 method: "POST",
//             });
//             const details = await response.json();
//
//             // Three cases to handle:
//             //   (1) Recoverable INSTRUMENT_DECLINED -> call actions.restart()
//             //   (2) Other non-recoverable errors -> Show a failure message
//             //   (3) Successful transaction -> Show confirmation or thank you
//
//             // This example reads a v2/checkout/orders capture response, propagated from the server
//             // You could use a different API or structure for your 'orderData'
//             const errorDetail = Array.isArray(details.details) && details.details[0];
//
//             if (errorDetail && errorDetail.issue === 'INSTRUMENT_DECLINED') {
//                 return actions.restart(); // Recoverable state, per:
//                 // https://developer.paypal.com/docs/checkout/integration-features/funding-failure/
//             }
//
//             if (errorDetail) {
//                 let msg = 'Sorry, your transaction could not be processed.';
//                 if (errorDetail.description) msg += '\n\n' + errorDetail.description;
//                 if (details.debug_id) msg += ' (' + details.debug_id + ')';
//                 return alert(msg); // Show a failure message (try to avoid alerts in production environments)
//             }
//
//             // Successful capture! For demo purposes:
//             console.log('Capture result', details, JSON.stringify(details, null, 2));
//             const transaction = details.purchase_units[0].payments.captures[0];
//             alert('Transaction ' + transaction.status + ': ' + transaction.id + '\n\nSee console for all available details');
//         },
//     })
//         .render("#paypal-button-container");
//
// })

FUNDING_SOURCES.forEach(fundingSource => {
        paypal.Buttons({
            fundingSource,
            style: {
                layout: 'vertical',
                shape: 'rect',
                color: 'gold'
            },
            createOrder: function (data, actions) {
                // Set up the transaction details
                return actions.order.create({
                    purchase_units: [{
                        amount: {
                            value: '10.00'
                        }
                    }]
                });
            },
            onApprove: function (data, actions) {
                // Capture the payment
                return actions.order.capture().then(function (details) {
                    // Handle the payment success
                    console.log('Payment completed');
                    // Redirect or perform further actions
                });
            }
        }).render('#paypal-button-container');
});

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
    if (amount.trim() === '') {
        amountInput.reportValidity()
    } else {
        let url = '/removeAmountFromCart/' + itemIndex + '/' + amount

        $.ajax({
            url: url,
            type: 'POST',
            processData: false,
            contentType: false,
            success: function (response) {
                console.log('successfully removed items from shopping cart');
                window.location.href = window.location.href;
            },
            error: function (xhr, status, error) {
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
        success: function (response) {
            console.log('successfully removed items from shopping cart');
            window.location.href = window.location.href;
        },
        error: function (xhr, status, error) {
            console.log('Error removing items from cart:', error);
        }
    });
}
