/*
    Ce fichier contient les fonctions qui sert à générer dynamiquement les contenus.
*/


function htmlSearchResult(data) {
    var html = '<table class="table table-striped"><thead><tr><th scope="col">id</th><th scope="col">Type</th><th scope="col">Price</th><th scope="col">Persons</th><th scope="col">Adresse</th></tr></thead>';
    html += "<tbody>";
    for (var i = 0; i < data.length; i++) {
        html += "<tr>";
        html += "<td>" + data[i].id + "</td>";
        html += "<td>" + data[i].type + "</td>";
        html += "<td>" + data[i].price + "</td>";
        html += "<td>" + data[i].person + "</td>";
        html += "<td><span>" + data[i].number + ' </span><span>' + data[i].street + ' </span><span>' + data[i].postal + ' </span><span>' + data[i].city + "</span></td>";
        html += "<td><a class=buttonDetails data-id=" + data[i].id + " class=\"text-success\">details</a></td>";
        html += "<td><a class=buttonReserve data-id=" + data[i].id + " class=\"text-success\">reserve</a></td>";
        html += "</tr>";
    }
    html += "</tbody></table>";
    return html;
}


function htmlProfile(resp) {
    var profileJson = resp.data;
    if (profileJson == []) return;
    var user = $('#userId').text();
    var html =
        '<div class="container bootstrap snippet">\
            <div class="row">';
    if (profileJson.id == user) {
        html += '<div class="col-sm-10">\
                    <h1>Hello '+ profileJson.nom + ' ' + profileJson.prenom + '!</h1>\
                </div>';
        html += '<div class="col-sm-10">';
        html += '<span>click </span>';
        html += '<ins id="gotoModifyProfile" class="text-success">here</ins>';
        html += '<span> to modify your information </span></div>';
    } else {
        html += '<div class="col-sm-10">\
                    <h1>' + profileJson.nom + ' ' + profileJson.prenom + '</h1>\
                </div>';
    }
    html += '<div class="col-sm-2">\
                    <a href="" class="pull-right"><img title="profile image" class="img-circle img-responsive"\
                    src="https://bootdey.com/img/Content/avatar/avatar1.png"></a>\
                </div>\
            </div>\
        </div>\
        <div class="col-sm-5">\
            <ul class="list-group">\
                <li class="list-group-item text-muted">Profile</li>\
                <li class="list-group-item text-right"><span class="pull-left"><strong>Email</strong></span>\
                    '+ profileJson.email + '\
                </li>\
                <li class="list-group-item text-right"><span class="pull-left"><strong>Adresse</strong></span>\
                    '+ profileJson.adresse + '\
                </li>\
                <li class="list-group-item text-right"><span class="pull-left"><strong>Telephone</strong></span>\
                    '+ profileJson.telephone + '\
                </li>\
            </ul>\
        </div>';
    return html;
}

function htmlResDetails(json) {
    var user = $('#userId').text();
    var owner = json[0];
    var res = json[1];
    var address = res.number + "," + res.street + "," + res.postal + "," + res.city; 
    var html = "";
    if (user == owner.id) {
        html += '<div class="col-sm-10">';
        html += '<span>click </span>';
        html += '<a id="gotoModifyRessource" data-id=' + res.id + ' class="text-success">here</a>';
        html += '<span> to modify this resource</span></div>';
    }
    html += '<div class=row-fluid>\
            <div class="col-md-4 ">\
            <div id="ressourceInfo">\
                <ul class="list-group">\
                    <li class="list-group-item text-muted">Ressource</li>\
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Numero</strong></span><span>'
        + res.id +
        '</span></li>\
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Type</strong></span><span id="span_type">'
        + res.type +
        '</span></li>\
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Price</strong></span><span id="span_price">'
        + res.price +
        '</span></li>\
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Person</strong></span><span id="span_person">'
        + res.person +
        '</span></li>\
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Smoker</strong></span><span id="span_smoke">'
        + res.smoke +
        '</span></li>\
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Adresse</strong></span><span id="span_num">'
        + res.number + ' </span><span id=span_street>' + res.street + ' </span><span id="span_postal">' + res.postal +
        ' </span><span id="span_city">' + res.city + '</span>' +
        '</li>\
                </ul>\
            </div>\
            <div id=infoOwner>\
                <ul class="list-group">\
                    <li class="list-group-item text-muted">Owner\'s information</li>\
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Owner</strong></span>'
        + owner.prenom + " " + owner.nom +
        '</li>\
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Email</strong></span>'
        + owner.email +
        '</li>\
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Telephone</strong></span>'
        + owner.telephone +
        '</li>\
                </ul>\
            </div>\
        </div>';
        html +='<a  id="city" data-city='+ res.city+ '></a>';//stock city in data-city node
        html += '<a  id="address" data-address=\"' + address + '\"></a>';//stock address in data-address

        html += '<div id="map" style="width:500px;height:380px;" class="col-md-8"></div>'//定义div google map显示区域

        html += '<script async defer\
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDpVUVmmjb1fQP25RB5TCYR20_3WkKlCok&callback=initMap">\
        </script>';//callback google map

        //Wheather area
        html +='<div id="DivWheather"></div>';


        //--call getWheather after charge web page------------------
        html += '<script type=”text/javascript”>\
        $(document).ready(getWheather);\
        </script>';
        //--------------------



    return html;
}

function htmlSendedDemands(resp) {
    var html =
        '<div id="demandList">\
            <legend>Your sended demands</legend>\
            <table class="table table-striped">\
                <thead>\
                <tr>\
                    <th scope="col">Id</th>\
                    <th scope="col">Ressource</th>\
                    <th scope="col">Checkin date</th>\
                    <th scope="col">Checkout date</th>\
                    <th scope="col">Create Time</th>\
                    <th scope="col">Status</th>\
                </tr>\
                </thead>\
                <tbody>';
    for (var i = 0; i < resp.length; i++) {
        var demand = resp[i].demand;
        var res = resp[i].res;
        html += "<tr>";
        html += "<td>" + demand.id + "</td>";
        html += '<td><a class=accesResDetails  class=text-success data-id=' + res.id + '>' + res.type + " " + res.id + "</a></td>";
        html += '<td>' + demand.checkin + '</td>';
        html += '<td>' + demand.checkout + '</td>';
        html += '<td>' + demand.createTime + '</td>';
        html += '<td>' + demand.status + '</td>';
        html += '<td><a class=deleteSendedDemand class=text-success data-sended=1 data-id=' + demand.id + '>Delete</a></td>';
        html += "<tr>";
    }
    html += '</tbody></table></div>';
    return html;
}

function htmlRecievedDemands(resp) {
    var html =
        '<div id="demandList">\
            <legend>Your recieved demands</legend>\
            <table class="table table-striped">\
                <thead>\
                <tr>\
                    <th scope="col">Id</th>\
                    <th scope="col">Ressource</th>\
                    <th scope="col">Demander</th>\
                    <th scope="col">Checkin date</th>\
                    <th scope="col">Checkout date</th>\
                    <th scope="col">Create Time</th>\
                    <th scope="col">Status</th>\
                </tr>\
                </thead>\
                <tbody>';
    for (var i = 0; i < resp.length; i++) {
        var demand = resp[i].demand;
        var demander = resp[i].demander;
        var res = resp[i].res;
        html += "<tr>";
        html += "<td>" + demand.id + "</td>";
        html += '<td><a class=accesResDetails  class=text-success data-id=' + res.id + '>' + res.type + " " + res.id + "</a></td>";
        html += '<td><a class=accesDemander  class=text-success data-id=' + demander.id + '>' + demander.username + "</a></td>";
        html += '<td>' + demand.checkin + '</td>';
        html += '<td>' + demand.checkout + '</td>';
        html += '<td>' + demand.createTime + '</td>';
        html += '<td>' + demand.status + '</td>';
        html += '<td><a class=deleteRecievedDemand class=text-success data-sended=0 data-id=' + demand.id + '>Delete</a></td>';
        html += '<td><a class=acceptDemand class=text-success data-id=' + demand.id + '>Accept</a></td>';
        html += "<tr>";
    }
    html += '</tbody></table></div>';
    return html;
}

function htmlYourHouses(resp) {
    var html =
        '<div id="infoRessource">\
            <fieldset>\
                <legend>Les informations des ressources</legend>\
                <table class="table table-striped">\
                    <thead>\
                    <tr>\
                        <th scope="col">id</th>\
                        <th scope="col">Type</th>\
                        <th scope="col">Price</th>\
                        <th scope="col">Persons</th>\
                        <th scope="col">Adresse</th>\
                        <th scope="col">Smoker</th>\
                    </tr>\
                    </thead>\
                    <tbody>';
    for (var i = 0; i < resp.length; i++) {
        html += '<tr>';
        html += '<td>' + resp[i].id + '</td>';
        html += '<td>' + resp[i].type + '</td>';
        html += '<td>' + resp[i].price + '</td>';
        html += '<td>' + resp[i].person + '</td>';
        html += '<td><span>'
            + resp[i].number + ' </span><span>' + resp[i].street + ' </span><span>' + resp[i].postal + ' </span><span>' + resp[i].city + '</span></td>';
        html += '<td>' + resp[i].smoke + '</td>';
        html += '<td><a class=accesResDetails class="text-success" data-id=' + resp[i].id + '>details</a></td>';
        html += '<td><a class=deleteRes class="text-success" data-id=' + resp[i].id + '>delete</a></td>';
        html += '</tr>';

    }
    html += '<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><a id="RessourceModeCreation" class="text-success">Add a house</a></td></tr>';
    html += '</tbody></table></fieldset></div></div>';
    return html;
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
                <li class="list-group-item text-muted">Real time Wheather</li>\
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

        alert("htmlWheather press");
    return html;
}
