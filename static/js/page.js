$(function(){
   $("#ModeLogin").click(ModeSignIn);
   $("#ModeSignUp").click(ModeSignUp);
   $('#ModeCreation').click(gotoCreationRessource);
   $('#quitCreation').click(quitCreationRessource);
  //  $('#id_room').click(gotoCreationRoom)
  //  $('#id_house').click(gotoCreationHouse);
   $('#gotoRes').click(gotoRes);
   $('#gotoUsers').click(gotoUser);
   $('#gotoCreateDemande').click(gotoCreateDemande);
   $('#gotoInfoDemande').click(gotoInfoDemande);
   $('#gotoDemendes').click(goDemandes);
});


// modify gestionnaire.html
function ModeSignIn(){
  $('#zoneLogin').css('display','block');
  $('#zoneSignUp').css('display','none');
}


function ModeSignUp(){
  $('#zoneSignUp').css('display','block');
  $('#zoneLogin').css('display','none');
}

function goDemandes(){
  $('#zoneRessource').css('display','none');
  $('#infoClient').css('display','none');
  $('#zoneDemande').css('display','block');
}


function gotoCreationRessource() {
  $('#createRessource').css('display','block');
  $('#infoRessource').css('display','none');
}
function quitCreationRessource() {
  $('#createRessource').css('display','none');
  $('#infoRessource').css('display','block');
}

function gotoCreationHouse() {
    $('#optionRoom').css('display','none');
    $('#optionHouse').css('display','block');
}

function gotoCreationRoom() {
    $('#optionRoom').css('display','block');
    $('#optionHouse').css('display','none');
}

function gotoRes() {
  $('#zoneRessource').css('display','block');
  $('#infoClient').css('display','none');
  $('#zoneDemande').css('display','none');
}
function gotoUser() {
  $('#zoneRessource').css('display','none');
  $('#infoClient').css('display','block');
  $('#zoneDemande').css('display','none');
}


// modify mainPage.html
function gotoCreateDemande() {
    $('#infoDemande').css('display', 'none');
    $('#createDemande').css('display', 'block');
}

function gotoInfoDemande(){
    $('#infoDemande').css('display', 'block');
    $('#createDemande').css('display', 'none');
}

