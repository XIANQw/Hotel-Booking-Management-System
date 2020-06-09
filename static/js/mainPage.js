$(function () {
    $("#ButtonSearch").click(searchRessource);
    $('#gotoPageProfile').click(getProfile);
    $('#gotoPageSendedDemands').click(getSendedDemands);
    $('#gotoPageRecievedDemands').click(getRecievedDemands);
    $('#gotoPageYourHouses').click(getYourHouses);
    $('#ButtonAddRes').click(addHouse);
    $('#ButtonModifyProfile').click(modifyProfile);
    $('#ButtonModifyRes').click(modifyRes);
    $('#getWheather').click(getWheather);

});

function searchRessource() {
    $.ajax({
        type: "POST",
        url: "Service?method=createSearchAjax",
        data: $('#FormSearch').serialize(),
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            if (resp.status == 1) {
                var html = htmlSearchResult(resp.data);
                $("#resultOfSearch").html(html);
                $('.buttonDetails').click(getResDetails);
                $('.buttonReserve').click(reserveRes);
                setSucess(resp.info);
            } else if (resp.status == 0) {
                var html = htmlSearchResult(resp.data);
                $("#resultOfSearch").html(html);
                setWarning(resp.info);
            } else {
                setAlert(resp.info);
            }
        }, error: function (res) {
            alert("error=" + res.responseText);
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

function getResDetails() {
    var idr = $(this).attr("data-id");
    $.ajax({
        type: "GET",
        url: "Service?method=getResDetailsAjax&id=" + idr,
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            var html = htmlResDetails(resp);
            $('#DivResContent').html(html);
            $('#gotoModifyRessource').click(gotoModifyRes);
            gotoPageResDetails();
        }, error: function (res) {
            if (res.responseText == "0") {
                gotoPageHome();
                setAlert("Your amount has not yet a profile");
            }
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
            gotoPageProfile();
            if (resp.status == 1) {
                var html = htmlProfile(resp);
                $('#DivProfileContent').html(html);
                $('#gotoModifyProfile').click(gotoModifyProfile);
            } else if (resp.status == 0) {
                setWarning(resp.info);
            } else {
                gotoModifyProfile();
                setAlert(resp.info);
            }
        }, error: function (res) {
            gotoPageHome();
            setAlert(res.responseText);
        }
    });
}

function gotoModifyProfile() {
    var id = $('#userId').text();
    $.ajax({
        type: "GET",
        url: "Client?method=getProfileAjax&id=" + id,
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            fillOutFormModifyProfile(resp.data);
            displayFormModifyProfile();
        }, error: function (res) {
            gotoPageHome();
            setAlert(res.responseText);
        }
    });
}

function modifyProfile() {
    $.ajax({
        type: "GET",
        url: "Client?method=modifyProfileAjax",
        data: $('#FormModifyProfile').serialize(),
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            if (resp.status == 1) {
                hiddeFromModifyProfile();
                getProfile();
                setSucess(resp.info);
            } else if (resp.status == 0) {
                setWarning(resp.info);
            } else {
                gotoModifyProfile();
                setAlert(resp.info);
            }
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
            if (resp.status == 1) {
                var html = htmlProfile(resp);
                $('#DivProfileContent').html(html);
                gotoPageProfile();
            } else if (resp.status == 0) {
                setWarning(resp.info);
            } else {
                setAlert(resp.info);
            }
        }, error: function (res) {
            gotoPageHome();
            setAlert(res.responseText);
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

function addHouse() {
    $.ajax({
        type: "POST",
        url: "Service?method=createRessourceAjax",
        data: $('#FormAddRes').serialize(),
        success: function (result, status) {
            var str = result;
            var resp = $.parseJSON(str);
            if (resp.status == 1) {
                getYourHouses();
                quitCreationRessource();
                setSucess(resp.info);
            } else if (resp.status == 0) {
                setWarning(resp.info);
            } else {
                setAlert(resp.info);
            }
        }, error: function (res) {
            alert("error=" + res.responseText);
        }
    });
}


function deleteRes() {
    var id = $(this).attr("data-id");
    $.ajax({
        type: "GET",
        url: "Service?method=deleteResAjax&id=" + id,
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            getYourHouses();
            if (resp.status == 1) {
                setSucess(resp.info);
            } else if (resp.status == 0) {
                setWarning(resp.info);
            } else {
                setAlert(resp.info);
            }
        }, error: function (res) {
            setAlert(res.responseText);
        }
    });
}

function gotoModifyRes() {
    var id = $(this).attr("data-id");
    var type = $('#span_type').text();
    var price = $('#span_price').text();
    var person = $('#span_person').text();
    var smoke = $('#span_smoke').text();
    var num = $('#span_num').text().trim();
    var street = $('#span_street').text();
    var postal = $('#span_postal').text().trim();
    var city = $('#span_city').text();
    displayFormModifyRes();
    $('#m_id_price').attr("value", price);
    $('#m_id_number').attr("value", num);
    $('#m_id_street').attr("value", street);
    $('#m_id_postal').attr("value", postal);
    $('#m_id_city').attr("value", city);
    if (smoke == 'Yes') $('#m_id_smoker').attr("checked", true);
    else $('#m_id_no_smoker').attr("checked", true);
    if (type == "house") {
        $('#m_id_house').attr("checked", true);
        gotoCreationHouse();
        $('#m_id_num_room').attr("value", person);
    } else {
        $('#m_id_room').attr("checked", true);
        gotoCreationRoom();
        $('#m_id_room_type').attr("value", person);
    }
    $('#ButtonModifyRes').attr("data-id", id);
}

function modifyRes() {
    var id = $(this).attr("data-id");
    $.ajax({
        type: "GET",
        url: "Service?method=modifyResAjax&id=" + id,
        data: $('#FormModifyRes').serialize(),
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            if (resp.status == 1) {
                hiddeFormModifyRes();
                getYourHouses();
                setSucess(resp.info);
            } else if (resp.status == 0) {
                setWarning(resp.info);
            } else {
                getResDetails();
                setAlert(resp.info);
            }
        }, error: function (res) {
            gotoPageHome();
            setAlert(res.responseText);
        }
    });
}

function getWheather() {
    //alert("click ok");
    // var city = document.getElementById('city').value;
    var city = $("#city").attr("data-city");
    // alert ("getWheather"+city);
    $.ajax({
        type: "POST",
        data: { dataCity: city },
        url: "Service?method=getWheather",
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            var html = htmlWheather(resp);
            $('#DivWheather').html(html);
        }, error: function (res) {
            var str = res;
            alert("error:" + str);
            alert("error=" + res.responseTest)
        }
    });
}

function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15,
        center: { lat: -34.397, lng: 150.644 }
    });
    var geocoder = new google.maps.Geocoder();
    geocodeAddress(geocoder, map);
}

function geocodeAddress(geocoder, resultsMap) {
    //    var idd = $(this).attr("data-id");

    //var address = document.getElementById('adresse').value;
    var address = $("#address").attr("data-address");
    // alert(address);
    geocoder.geocode({ 'address': address }, function (results, status) {
        if (status === 'OK') {
            resultsMap.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map: resultsMap,
                position: results[0].geometry.location

            });
        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
    });
}
function getAddress() {
    $.ajax({
        type: "GET",
        url: "Service?method=getAddress",
        success: function (result, status) {
            var str = result;
        }, error: function (res) {
            var str = res;
            alert("error:" + str);
            alert("error=" + res.responseTest)
        }
    });
}
