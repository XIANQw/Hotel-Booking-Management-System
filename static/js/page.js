$(function () {
    $("#ModeLogin").click(ModeSignIn);
    $("#ModeSignUp").click(ModeSignUp);
    $('#RessourceModeCreation').click(gotoCreationRessource);
    $('#RessourceQuitCreation').click(quitCreationRessource);
    $('#id_room').click(gotoCreationRoom)
    $('#id_house').click(gotoCreationHouse);
    $('#gotoPageHome').click(gotoPageHome);
    $('#gotoPageProfile').click(gotoPageProfile);
});


// modify gestionnaire.html
function ModeSignIn() {
    $('#zoneLogin').css('display', 'block');
    $('#zoneSignUp').css('display', 'none');
}


function ModeSignUp() {
    $('#zoneSignUp').css('display', 'block');
    $('#zoneLogin').css('display', 'none');
}

function gotoCreationRessource() {
    $('#createRessource').css('display', 'block');
    $('#infoRessource').css('display', 'none');
}

function quitCreationRessource() {
    $('#createRessource').css('display', 'none');
    $('#infoRessource').css('display', 'block');
}

function gotoCreationHouse() {
    $('#optionRoom').css('display', 'none');
    $('#optionHouse').css('display', 'block');
    $('#id_num_room').attr('required');
    $('#id_room_type').removeAttr('required');
}

function gotoCreationRoom() {
    $('#optionRoom').css('display', 'block');
    $('#optionHouse').css('display', 'none');
    $('#id_num_room').removeAttr('required');
    $('#id_room_type').attr('required');
}

function gotoPageHome() {
    $('#mainDiv').children().css('display', 'none');
    $('#DivSearch').css('display', 'block');   
}

function gotoPageProfile() {
    $('#mainDiv').children().css('display', 'none');
    $('#DivProfile').css('display', 'block');
}

function setAlert(str) {
    var html = "<div id=\"alert\" class=\"alert alert-danger\">" + str + "</div>";
    $('#divAlert').html(html);
}

function setSucess(str) {
    var html = "<div id=\"alert\" class=\"alert alert-sucess\">" + str + "</div>";
    $('#divAlert').html(html);
}

function htmlProfile(profileJson) {
    var html =
        '<div class="container bootstrap snippet">\
            <div class="row">\
                <div class="col-sm-10">\
                    <h1>Hello '+ profileJson.nom + ' ' + profileJson.prenom + '!</h1>\
                </div>\
                <div class="col-sm-2">\
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


