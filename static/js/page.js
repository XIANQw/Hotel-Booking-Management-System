$(function () {
    $("#ModeLogin").click(ModeSignIn);
    $("#ModeSignUp").click(ModeSignUp);
    $('#gotoPageHome').click(gotoPageHome);
    $('#ButtonQuitModifyProfile').click(hiddeFromModifyProfile);
    $('#ButtonQuitModifyRes').click(hiddeFormModifyRes);
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
    $('#DivAddRes').css('display', 'block');
    $('#DivResList').css('display', 'none');
}

function quitCreationRessource() {
    $('#DivAddRes').css('display', 'none');
    $('#DivResList').css('display', 'block');
}

function gotoCreationHouse() {
    $('.optionRoom').css('display', 'none');
    $('.optionHouse').css('display', 'block');
    $('#id_num_room').attr('required');
    $('#id_room_type').removeAttr('required');
}

function gotoCreationRoom() {
    $('.optionRoom').css('display', 'block');
    $('.optionHouse').css('display', 'none');
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

function displayFormModifyProfile() {
    $('#DivProfileContent').css("display", "none");
    $('#DivModifyProfile').css("display", "block");
}

function hiddeFromModifyProfile() {
    $('#DivProfileContent').css("display", "block");
    $('#DivModifyProfile').css("display", "none");
}

function gotoPageResDetails() {
    $('#mainDiv').children().css('display', 'none');
    $('#DivResDetails').css('display', 'block');
}

function displayFormModifyRes(){
    $('#DivResContent').css('display', 'none');
    $('#DivModifyRes').css('display', 'block');
}

function hiddeFormModifyRes(){
    $('#DivResContent').css('display', 'block');
    $('#DivModifyRes').css('display', 'none');
}

function gotoPageSendedDemands() {
    $('#mainDiv').children().css('display', 'none');
    $('#DivSendedDemands').css('display', 'block');
}

function gotoPageRecievedDemands() {
    $('#mainDiv').children().css('display', 'none');
    $('#DivRecievedDemands').css('display', 'block');
}


function gotoPageYourHouses() {
    $('#mainDiv').children().css('display', 'none');
    $('#DivYourHouses').css('display', 'block');
}

function emptyMsg() {
    $('#divAlert').html("");
}

function setAlert(str) {
    var html = "<div id=\"alert\" class=\"alert alert-danger\">" + str + ". <ins id=closeMsg class=text-success>close</ins></div>";
    $('#divAlert').html(html);
    $('#closeMsg').click(emptyMsg);
}

function setWarning(str) {
    var html = "<div id=\"alert\" class=\"alert alert-warning\">" + str + ". <ins id=closeMsg class=text-success>close</ins></div>";
    $('#divAlert').html(html);
    $('#closeMsg').click(emptyMsg);
}

function setSucess(str) {
    var html = "<div id=\"alert\" class=\"alert alert-success\">" + str + ". <ins id=closeMsg class=text-success>close</ins></div>";
    $('#divAlert').html(html);
    $('#closeMsg').click(emptyMsg);
}

function fillOutFormModifyProfile(profile) {
    if (profile == []) return;
    $('#id_modify_profile_nom').attr({ "value": profile.nom });
    $('#id_modify_profile_prenom').attr({ "value": profile.prenom });
    $('#id_modify_profile_email').attr({ "value": profile.email });
    $('#id_modify_profile_adresse').attr({ "value": profile.adresse });
    $('#id_modify_profile_telephone').attr({ "value": profile.telephone });
}
