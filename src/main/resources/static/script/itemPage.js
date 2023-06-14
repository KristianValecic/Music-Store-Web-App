const amountInStock = document.getElementById('amountInStock').value;
const amountToCartField = document.getElementById('itemAmount');

$(document).ready(function () {
    amountToCartField.max = amountInStock;
});