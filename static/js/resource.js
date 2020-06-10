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


