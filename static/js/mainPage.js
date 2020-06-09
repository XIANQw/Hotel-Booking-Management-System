$(function () {
    //setInterval(getWheather,5000);//5秒刷新一次 5second reflesh
    $("#getWheather").click(getWheather);
});

function getWheather() {
    
    var city = document.getElementById('city').value;
    $.ajax({
        type: "POST",
        data: {dataCity:city},
        url: "Service?method=getWheather",
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            var html = htmlWheather(resp);
            $('#DivWheather').html(html);
            alert(resp);
        }, error: function (res) {
            var str = res;
            alert("error:" + str);
            alert("error=" + res.responseTest)
        }
    });
}

function htmlWheather(WheatherJson) {
    var html =
        '<div class="container bootstrap snippet">\
            <div class="row">\
                <div class="col-sm-10">\
                </div>\
            </div>\
        </div>\
        <div class="col-sm-5">\
            <ul class="list-group">\
                <li class="list-group-item text-muted">Wheather</li>\
                <li class="list-group-item text-right"><span class="pull-left"><strong>City</strong></span>\
                    '+ WheatherJson.request.query + '\
                </li>\
                <li class="list-group-item text-right"><span class="pull-left"><strong>Time</strong></span>\
                    '+ WheatherJson.location.localtime + '\
                </li>\
                <li class="list-group-item text-right"><span class="pull-left"><strong>Temperature</strong></span>\
                    '+ WheatherJson.current.temperature + '\
                </li>\
            </ul>\
        </div>';
    return html;
}



