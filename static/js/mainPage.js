$(function () {
    $("#ButtonSearch").click(searchRessource);
    $('#gotoPageProfile').click(getProfile);
    $('#gotoPageSendedDemands').click(getSendedDemands);
});

function getResDetails() {
    var idr = $(this).attr("data-id");
    $.ajax({
        type: "GET",
        url: "Service?method=getResDetailsAjax&id=" + idr,
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            var html = htmlResDetails(resp);
            $('#DivResDetails').html(html);
            gotoPageResDetails();
        }, error: function (res) {
            if (res.responseText == "0") {
                gotoPageHome();
                setAlert("Your amount has not yet a profile");
            }
        }
    });
}

function reserveRes() {
    var idr = $(this).attr("data-id");
    alert(idr);
}

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
                html += "<td><a id=buttonDetails" + resp[i].id + " data-id=" + resp[i].id + " class=\"text-success\">details</a></td>";
                html += "<td><a id=buttonReserve" + resp[i].id + " data-id=" + resp[i].id + " class=\"text-success\">reserve</a></td>";
                html += "</tr>";
            }
            html += "</tbody></table>";
            $("#resultOfSearch").html(html);
            for (var i = 0; i < resp.length; i++) {
                $('#buttonDetails' + resp[i].id).click(getResDetails);
                $('#buttonReserve' + resp[i].id).click(reserveRes);
            }
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

function getSendedDemands() {
    var id = $('#userId').text();
    $.ajax({
        type: "GET",
        url: "Service?method=getSendedDemandsAjax&id="+id,
        success: function (result, status) {
            var resp = JSON.parse(result);
            var html = htmlSendedDemands(resp);
            $('#DivSendedDemands').html(html);
            $(".accesResDetails").click(getResDetails);
            gotoPageSendedDemands();
        }, error: function (res) {
            gotoPageHome();
            setAlert(res.responseText);
        }
    });
}





