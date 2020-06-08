$(function () {
    $("#ButtonSearch").click(searchRessource);
    $('#gotoPageProfile').click(getProfile);
});

function searchRessource() {
    $.ajax({
        type: "POST",
        url: "Service?method=createSearchAjax",
        data: $('#FormSearch').serialize(),
        success: function (result, status) {
            var str = result;
            var resp = $.parseJSON(str);
            var html = '<table class="table table-striped"><thead><tr><th scope="col">id</th><th scope="col">Type</th><th scope="col">Price</th><th scope="col">Persons</th><th scope="col">Adresse</th></tr></thead>';
            html += "<tbody>";
            for (var i = 0; i < resp.length; i++) {
                html += "<tr>";
                html += "<td>" + resp[i].id + "</td>";
                html += "<td>" + resp[i].type + "</td>";
                html += "<td>" + resp[i].price + "</td>";
                html += "<td>" + resp[i].person + "</td>";
                html += "<td>" + resp[i].adresse + "</td>";
                html += "<td><a id=buttonDetails" + resp[i].id + "\" class=\"text-success\">details</a></td>";
                html += "<td><a id=buttonReserve" + resp[i].id + "\" class=\"text-success\">reserve</a></td>";
                html += "</tr>";
            }
            html += "</tbody></table>";
            $("#resultOfSearch").html(html);
        }, error: function (res) {
            alert("error=" + res.responseText);
        }
    });
}


function getProfile() {
    var id = $('#userId').text();
    $.ajax({
        type: "GET",
        url: "Client?method=getProfileAjax&id=" + id,
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            var html = htmlProfile(resp);
            $('#DivProfile').html(html);
        }, error: function (res) {
            if (res.responseText == "0") {
                gotoPageHome();
                setAlert("Your amount has not yet a profile");
            }
        }
    });
}





