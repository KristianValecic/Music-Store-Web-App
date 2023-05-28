//Threw away time for functionality that wasn't going to be used

// var isMediaSelected = false;
// var isGenreSelected = false;

// $(".genreIconContainer").click(function () {
//     if (isGenreSelected && this.innerText === selectedGenre){
//         $(this).toggleClass("grey");
//         isGenreSelected = false;
//     }else if (!isGenreSelected){
//         selectedGenre = this.innerText;
//         $(this).toggleClass("grey");
//         isGenreSelected = true;
//     }
// });
// $(".mediaIconContainer").click(function () {
//     if (isMediaSelected && this.innerText === selectedMedia){
//         $(this).toggleClass("grey");
//         isMediaSelected = false;
//     }else if (!isMediaSelected){
//         selectedMedia = this.innerText;
//         $(this).toggleClass("grey");
//         isMediaSelected = true;
//     }
// });

var selectedMedia = [];
var selectedGenre = [];

$(".genreIconContainer").click(function () {
    let genreaType = this.innerText;
    $(this).toggleClass("grey");
    if (selectedGenre.includes(genreaType)){
        selectedGenre = selectedGenre.filter(function(item) {
            return item !== genreaType;
        });
    }else{
        selectedGenre.push(genreaType)
    }
    ajaxFilter(/*selectedMedia, selectedGenre*/);

});
$(".mediaIconContainer").click(function () {
    let mediaType = this.innerText;
    $(this).toggleClass("grey");
    if (selectedMedia.includes(mediaType)){
        selectedMedia = selectedMedia.filter(function(item) {
            return item !== mediaType;
        });
    }else{
        selectedMedia.push(mediaType)
    }
    ajaxFilter(/*selectedMedia, selectedGenre*/);
});

function ajaxFilter(/*mediaType, genreType*/){
    // if (!selectedMedia.includes(mediaType)){
    //     selectedMedia.push(mediaType)
    // }
    // if (!selectedGenre.includes(genreType)){
    //     selectedGenre.push(genreType)
    // }
    var jsonMediaType = JSON.stringify(selectedMedia);
    var jsonGenreType = JSON.stringify(selectedGenre);

    $.ajax({
        url: '/filterHome',
        type: 'GET',
        data: {
            mediaType: jsonMediaType,
            genreType: jsonGenreType
        },
        success: function (response) {
            let html = $(response).find('#itemsHomeContainer').html();
            $("#itemsHomeContainer").html(html);
        },
        error: function (xhr, status, error) {
            console.log('Error while filtering home:', error);
        }
    });
}