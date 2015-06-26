/**
 * Created by questmac on 6/26/15.
 */


function bajingan() {
    $.getJSON("/json", function (data) {
        $('#body').html ("" +
            "Jadi begini, ada salam <h1\>" +
            data["hello"] + "</h1\> dari " + data["from"]);
    })
}

function main() {
    $('#body').click(function (){
        $(this).hide();
    });
    $('#tombol').click(function () {
       bajingan();
    });
}

main();
