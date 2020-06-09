$(function () {
    $("#ButtonSearch").click(searchRessource);
    $('#gotoPageProfile').click(getProfile);
    $('#gotoPageSendedDemands').click(getSendedDemands);
    $('#gotoPageRecievedDemands').click(getRecievedDemands);
    $('#gotoPageYourHouses').click(getYourHouses);
    $('#ButtonAddRes').click(addHouse);
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
    $.ajax({
        type: "GET",
        url: "Service?method=sendDemandAjax&id=" + idr,
        success: function (result, status) {
            var resp = JSON.parse(result);
            gotoPageHome();
            setSucess(resp.info);
        }, error: function (res) {
            if (res.responseText == "0") {
                gotoPageHome();
                setAlert("Your amount has not yet a profile");
            }
        }
    });
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
            gotoPageProfile();
        }, error: function (res) {
            if (res.responseText == "0") {
                gotoPageHome();
                setAlert("Your amount has not yet a profile");
            }
        }
    });
}

function deleteDemands() {
    var idd = $(this).attr("data-id");
    var sended = $(this).attr("data-sended");
    $.ajax({
        type: "GET",
        url: "Service?method=deleteDemandAjax&id=" + idd,
        success: function (result, status) {
            var resp = JSON.parse(result);
            if (sended == '1') getSendedDemands();
            else getRecievedDemands();
            setSucess(resp.info);
        }, error: function (res) {
            gotoPageHome();
            setAlert(res.responseText);
        }
    });
}

function getSendedDemands() {
    var id = $('#userId').text();
    $.ajax({
        type: "GET",
        url: "Service?method=getSendedDemandsAjax&id=" + id,
        success: function (result, status) {
            var resp = JSON.parse(result);
            var html = htmlSendedDemands(resp);
            $('#DivSendedDemands').html(html);
            $(".accesResDetails").click(getResDetails);
            $(".deleteSendedDemand").click(deleteDemands);
            gotoPageSendedDemands();
        }, error: function (res) {
            gotoPageHome();
            setAlert(res.responseText);
        }
    });
}

function accesDemander() {
    var id = $(this).attr("data-id");
    $.ajax({
        type: "GET",
        url: "Client?method=getProfileAjax&id=" + id,
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            var html = htmlProfile(resp);
            $('#DivProfile').html(html);
            gotoPageProfile();
        }, error: function (res) {
            if (res.responseText == "0") {
                gotoPageHome();
                setAlert("This demander has not yet a profile");
            }
        }
    });
}

function getRecievedDemands() {
    var id = $('#userId').text();
    $.ajax({
        type: "GET",
        url: "Service?method=getRecievedDemandsAjax&id=" + id,
        success: function (result, status) {
            var resp = JSON.parse(result);
            var html = htmlRecievedDemands(resp);
            $('#DivRecievedDemands').html(html);
            $(".accesResDetails").click(getResDetails);
            $(".deleteRecievedDemand").click(deleteDemands);
            $(".accesDemander").click(accesDemander);
            $(".acceptDemand").click(acceptDemander);
            gotoPageRecievedDemands();
        }, error: function (res) {
            gotoPageHome();
            setAlert(res.responseText);
        }
    });
}

function acceptDemander() {
    var id = $(this).attr("data-id");
    $.ajax({
        type: "GET",
        url: "Service?method=acceptDemandAjax&id=" + id,
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            if (resp.status == 1) {
                getRecievedDemands();
                setSucess(resp.info);
            } else {
                setAlert(resp.info);
            }
        }, error: function (res) {
            setAlert(res.responseText);
        }
    });
}


function getYourHouses() {
    var id = $('#userId').text();
    $.ajax({
        type: "GET",
        url: "Service?method=getResListAjax&id=" + id,
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            var html = htmlYourHouses(resp);
            $('#DivResList').html(html);
            $('.accesResDetails').click(getResDetails);
            $('#RessourceModeCreation').click(gotoCreationRessource);
            $('#RessourceQuitCreation').click(quitCreationRessource);
            $('.deleteRes').click(deleteRes);
            gotoPageYourHouses();
        }, error: function (res) {
            setAlert(res.responseText);
        }
    });
}

function addHouse(){

}


function deleteRes(){
    var id = $(this).attr("data-id");
    $.ajax({
        type: "GET",
        url: "Service?method=deleteResAjax&id=" + id,
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            getYourHouses();
            setSucess(resp.info);
        }, error: function (res) {
            setAlert(res.responseText);
        }
    });
}

