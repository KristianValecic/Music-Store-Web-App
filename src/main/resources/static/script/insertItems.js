const updateModal = document.getElementById('updateModal')
const updateBtn = document.getElementsByClassName('updateBtn')
const baseURL = document.getElementById('updateItemForm').action
const baseImgSrc = 'data:image/png;base64,'

async function uploadImage(event) {
    var img = event;
    var file = img.files[0];
    var formData = new FormData();
    formData.append('image', file);

    var imageBase64 = await toBase64(file);

    $.ajax({
        url: '/uploadAlbumImage',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function (response) {
            let imageDOM = event.form.querySelector('.tempUploadImageDisplay');
            imageDOM.src = imageBase64
            event.form.querySelector('.errorMessage').innerText = "";
            console.log('Image uploaded successfully');
        },
        error: function (xhr, status, error) {
            console.log('Error uploading image:', error);
            event.form.querySelector('.errorMessage').innerText = "Image file size too big.";
            clearFileInput(document.getElementById(".imageInput"));
        }
    });
}

const toBase64 = file => new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = reject;
});

function disableEmptyInputs(form) {

}

function errorMessage(message) {
    var messageDOM = document.getElementById("errorMessage");
    messageDOM.innerText = message
}

function clearFileInput(fileInput) {
    if (fileInput.nodeName == "INPUT") {
        fileInput.value = ""
    }
}

function alertConfirm(message) {
    if (confirm(message)) {
        console.log(' clicked "Yes"')
    } else {
        event.preventDefault()
    }
}

//TODO: controller update post, promjena slike
function updateModalOpen(event) {
    console.log("update btn event triggered")
    let form = document.getElementById('updateItemForm')
    //sets id to action url
    const itemId = event.getAttribute('data-itemId')

    form.action = baseURL + itemId

    //sets all fileds
    let artistNameUpdateInput = document.getElementById("artistNameUpdateInput");
    artistNameUpdateInput.value = event.getAttribute('data-itemArtistName');

    let ablumNameUpdateInput = document.getElementById("ablumNameUpdateInput");
    ablumNameUpdateInput.value = event.getAttribute('data-itemAlbumName');

    let genreTypeUpdateSelect = document.getElementById("genreTypeUpdateSelect");
    genreTypeUpdateSelect.value = event.getAttribute('data-itemGenre');

    let mediaTypeUpdateSelect = document.getElementById("mediaTypeUpdateSelect");
    mediaTypeUpdateSelect.value = event.getAttribute('data-itemMedia');

    let updateAmountInStock = document.getElementById("updateAmountInStock");
    updateAmountInStock.value = event.getAttribute('data-itemAmountInStock');

    let updatePrice = document.getElementById("updatePrice");
    updatePrice.value = event.getAttribute('data-itemPrice');

    let image = document.getElementById("tempUploadUpdateImage");
    image.src = baseImgSrc + event.getAttribute('data-imageB64');

}

document.getElementById("submitUpdateButton").addEventListener("click", function () {
    let form = document.getElementById("updateItemForm");
    let controls = form.elements;
    let isValid = true;
    for (let i = 0, iLen = controls.length; i < iLen; i++) {
        if(controls[i].value === '' && controls[i].id !== 'imageUpdateInput'){
            controls[i].reportValidity();
            isValid = false;
        }
    }
    if (isValid){
        form.submit();
    }
});

$(document).ready(function () {
    $('#insertedItemsTable').DataTable({
        paging: true,
        pageLength: 7,
        lengthMenu: [7, 10, 25, 50, 100],
        ordering: true,
        order: [[0, 'asc']],
        columnDefs: [
            {orderable: false, targets: [2, 7, 8]}
        ],
    });


});