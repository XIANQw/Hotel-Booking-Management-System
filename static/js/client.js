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

