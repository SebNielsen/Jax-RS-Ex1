$('document').ready(function(){
    setRandomQuote();
}); 

//Set new random quote when click on button
$('#random').on('click', function () {
    setRandomQuote();
});

//Fill table with quotes when click on button
$('#quotes').on('click', function () {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/Jax-RS-Ex1/api/quote",
        success: function (data) {
            fillTableWithQuotes(data);
        }
    });
});

//Create new Quote when click on button
$("#create").on('click', function () {
    $.ajax({
        method: "POST",
        url: "http://localhost:8080/Jax-RS-Ex1/api/quote",
        datatype: "json",
        processData: false,
        contentType: 'application/json',
        data: JSON.stringify({id: "2", quote: $('#newQuote').val()})}).done();
});


//Remove quote when click on button
$('body').on('click', '.removebtn', function () {
    if (confirm("Are you sure you want to delete this Quote")) {
        var id = $(this).attr("quoteid");
        var tr = $(this).closest('tr');
        tr.remove();
        $.ajax({
            method: "DELETE",
            url: "http://localhost:8080/Jax-RS-Ex1/api/quote/" + id
        });
    }
});

var id;
var quote;        
//Select the current quote and place the value in editor
$('body').on('click', '.editbtn', function () {
    id = $(this).closest("tr").children().eq(0);
    quote = $(this).closest("tr").children().eq(1);
    $('#selectedId').val(id.text());
    $('#selectedQuote').val(quote.text());
});

$("#update").on('click', function () {
    var editedQuote = $('#selectedQuote').val();
    updateQuote($('#selectedId').val(), editedQuote);
    quote.text(editedQuote);
});

function updateQuote(id, qoute) {
    $.ajax({
        method: "PUT",
        url: "http://localhost:8080/Jax-RS-Ex1/api/quote/" + id,
        datatype: "json",
        processData: false,
        contentType: 'application/json',
        data: JSON.stringify({quote: qoute})}).done();
}

function setRandomQuote() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/Jax-RS-Ex1/api/quote/random",
        processData: false,
        contentType: 'application/json',
        success: function (data) {
            $('#quote').text(data.quote);
        },
        error: function (error) {
            var json = error.responseJSON;
            $("#error").text(json.message);
          
        }
    });
}
;

function fillTableWithQuotes(data) {
    $("#tableBody > tr").remove();
    data.forEach(function (quote) {
        $('#tableBody').append("<tr><td>" + quote.id + "</td>" +
                "<td>" + quote.quote + "</td>" +
                "<td><a id='quotes' data-toggle='collapse' class= 'editbtn btn btn-info btn-sm' data-parent='#accordion' href='#collapseThree'>EDIT</a></td>" +
                "<td><button class='removebtn btn btn-warning btn-sm' quoteid=" + quote.id + ">REMOVE</button></td>" +
                "</tr>");
    });
}
;