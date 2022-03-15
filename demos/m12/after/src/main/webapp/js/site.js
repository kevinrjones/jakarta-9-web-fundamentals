$(function () {

    $('#connect').click(function () {

        $.ajax({
            url: 'downloadasync',
            type: 'get',
            xhr: function () {
                var xhr = $.ajaxSettings.xhr();
                xhr.onprogress = function (e) {
                    // For downloads
                    if (e.lengthComputable) {
                        console.log(e.loaded / e.total);
                    } else {
                        console.log(e.loaded)
                    }
                };
                return xhr;
            }
        }).done(function (e) {
            console.log("done")
        }).fail(function (e) {
            // Do something
        });

    });
});

