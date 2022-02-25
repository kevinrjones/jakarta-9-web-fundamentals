$(function () {

    console.log("Hello World")

    $('#connect').click(function () {
        $.get('downloadasync').done(function (data) {
            console.log(data);
        })
    });
});