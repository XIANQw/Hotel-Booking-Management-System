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
