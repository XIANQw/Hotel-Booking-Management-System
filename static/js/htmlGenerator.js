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
        html += "<td>" + data[i].adresse + "</td>";
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
        html += '<span> Modify your information </span></div>';
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
    var owner = json[0];
    var res = json[1];
    var html =
        '<div class=row-fluid>\
            <div class="col-md-4 ">\
            <div id="ressourceInfo">\
                <ul class="list-group">\
                    <li class="list-group-item text-muted">Ressource</li>\
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Numero</strong></span>'
        + res.id +
        '</li>\
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Type</strong></span>'
        + res.type +
        '</li>\
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Price</strong></span>'
        + res.price +
        '</li>\
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Adresse</strong></span>'
        + res.adresse +
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
        html += '<td>' + resp[i].adresse + '</td>';
        html += '<td>' + resp[i].smoke + '</td>';
        html += '<td><a class=accesResDetails class="text-success" data-id=' + resp[i].id + '>details</a></td>';
        html += '<td><a class=deleteRes class="text-success" data-id=' + resp[i].id + '>delete</a></td>';
        html += '</tr>';

    }
    html += '<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><a id="RessourceModeCreation" class="text-success">Add a house</a></td></tr>';
    html += '</tbody></table></fieldset></div></div>';
    return html;
}